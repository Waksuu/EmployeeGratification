package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;

import static java.util.stream.Collectors.toList;

public class AchievementCard {
    @Id
    private final AchievementCardId id;
    private final List<AchievementApplication> requestedApplications;
    private final ConfigId configId;
    private AchievementCardState state;

    public AchievementCard(AchievementCardId id, List<AchievementApplication> requestedApplications, ConfigId configId) {
        this.id = id;
        this.requestedApplications = requestedApplications;
        this.configId = configId;
        this.state = new DraftAchievementCardState();
    }

    public AchievementApplicationApplied applyForAchievement(AchievementApplication application,
            AchievementConfigurationService achievementConfigurationService) {
        return state.applyForAchievement(() -> {
            if (!application.isAchievementAvailableInEvaluationProcess(configId, achievementConfigurationService)) {
                throw new AchievementException();
            }

            if (conflictOfInterest(application, achievementConfigurationService)) {
                throw new AchievementException();
            }

            requestedApplications.add(application);
            return new AchievementApplicationApplied(application.getId().getId());
        });
    }

    private boolean conflictOfInterest(AchievementApplication application,
            AchievementConfigurationService achievementConfigurationService) {
        return achievementIsAlreadyRequested(application) &&
                !application.canBeAppliedForMultipleTimes(achievementConfigurationService);
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
            return new AchievementApplicationRemoved(achievementApplicationId.getId());
        });
    }

    private boolean applicationDoesNotExist(AchievementApplicationId achievementApplicationId) {
        return requestedApplications.stream().noneMatch(application -> application.getId().equals(achievementApplicationId));
    }

    public AchievementApplicationUpdated updateAchievementApplication(AchievementApplicationId achievementApplicationId,
            ProposedOutcome proposedOutcome,
            List<Answer> answers,
            AchievementConfigurationService achievementConfigurationService) {
        return this.state.updateAchievementApplication(() -> {
            var applications = requestedApplications.stream()
                    .filter(x -> x.getId().equals(achievementApplicationId))
                    .collect(toList());

            if (applications.size() > 1) {
                throw new AchievementException();
            }

            if (applications.isEmpty()) {
                throw new AchievementException();
            }

            var application = applications.get(0);

            return application.updateAchievementApplication(proposedOutcome, answers, achievementConfigurationService);
        });
    }

    public void moveToPromotionMeeting() {
        validateAllQuestionsAnswered();
        state = state.ToPromotionMeeting();
    }

    private void validateAllQuestionsAnswered() {
        boolean allQuestionsAnswered = requestedApplications.stream().allMatch(AchievementApplication::areAnswersFilled);
        if (!allQuestionsAnswered) {
            throw new AchievementException();
        }
    }
}
