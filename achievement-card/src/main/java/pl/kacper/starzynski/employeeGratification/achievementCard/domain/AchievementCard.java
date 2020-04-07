package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import org.springframework.data.annotation.Id;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationApplied;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationRemoved;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.QuestionnaireAnswersUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.ports.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.state.AchievementCardState;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.state.DraftAchievementCardState;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

//TODO: Add version
public class AchievementCard {
    @Id
    private final AchievementCardId id;
    private final List<AchievementApplication> requestedApplications;
    private final AchievementConfigurationId configId;
    private AchievementCardState state;

    public AchievementCard(AchievementCardId id, List<AchievementApplication> requestedApplications, AchievementConfigurationId configId) {
        this.id = id;
        this.requestedApplications = requestedApplications;
        this.configId = configId;
        this.state = new DraftAchievementCardState();
    }

    public AchievementApplicationApplied applyForAchievement(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            AchievementConfigurationService achievementConfigurationService) {
        return state.applyForAchievement(() -> {
            var application = AchievementApplicationFactory.create(new AchievementApplicationId(UUID.randomUUID()),
                    achievementCode,
                    proposedOutcome,
                    Collections.emptyList(),
                    configId,
                    achievementConfigurationService);

            if (!application.isAchievementAvailableInEvaluationProcess(configId, achievementConfigurationService)) {
                throw new AchievementException();
            }

            if (conflictOfInterest(application, achievementConfigurationService)) {
                throw new AchievementException();
            }

            requestedApplications.add(application);
            return new AchievementApplicationApplied(application.getId());
        });
    }

    private boolean conflictOfInterest(AchievementApplication application,
            AchievementConfigurationService achievementConfigurationService) {
        return achievementIsAlreadyRequested(application) &&
                !application.canBeAppliedForMultipleTimes(configId, achievementConfigurationService);
    }

    private boolean achievementIsAlreadyRequested(AchievementApplication application) {
        return requestedApplications.stream()
                .anyMatch(requestedApplication -> requestedApplication.areAchievementsEqual(application));
    }

    public AchievementApplicationRemoved removeAchievementApplication(AchievementApplicationId achievementApplicationId) {
        return this.state.removeAchievementApplication(() -> {
            if (applicationDoesNotExist(achievementApplicationId)) {
                throw new AchievementException();
            }

            requestedApplications.removeIf(application -> application.getId().equals(achievementApplicationId));
            return new AchievementApplicationRemoved(achievementApplicationId);
        });
    }

    private boolean applicationDoesNotExist(AchievementApplicationId achievementApplicationId) {
        return requestedApplications.stream().noneMatch(application -> application.getId().equals(achievementApplicationId));
    }

    public ProposedOutcomeUpdated updateProposedOutcome(AchievementApplicationId achievementApplicationId,
            ProposedOutcome proposedOutcome, AchievementConfigurationService achievementConfigurationService) {
        return this.state.updateProposedOutcome(() -> {
            var application = getAchievementApplication(achievementApplicationId);
            return application.updateProposedOutcome(proposedOutcome, configId, achievementConfigurationService);
        });
    }

    public QuestionnaireAnswersUpdated updateQuestionnaireAnswers(AchievementApplicationId achievementApplicationId,
            List<QuestionnaireAnswer> answers) {
        return this.state.updateQuestionnaireAnswers(() -> {
            var application = getAchievementApplication(achievementApplicationId);
            return application.updateAnswers(answers);
        });
    }

    private AchievementApplication getAchievementApplication(AchievementApplicationId achievementApplicationId) {
        var applications = requestedApplications.stream()
                .filter(x -> x.getId().equals(achievementApplicationId))
                .collect(toList());

        if (applications.size() > 1) {
            throw new AchievementException();
        }

        if (applications.isEmpty()) {
            throw new AchievementException();
        }

        return applications.get(0);
    }

    public void moveToPromotionMeeting() {
        validateAllApplicationsFilled();
        state = state.ToPromotionMeeting();
    }

    private void validateAllApplicationsFilled() {
        boolean areAllApplicationsFilled = requestedApplications.stream().allMatch(AchievementApplication::isApplicationFilled);
        if (!areAllApplicationsFilled) {
            throw new AchievementException();
        }
    }
}
