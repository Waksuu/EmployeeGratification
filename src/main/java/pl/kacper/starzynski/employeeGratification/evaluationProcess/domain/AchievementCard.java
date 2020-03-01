package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AchievementCard {
    @Id
    private final AchievementCardId id;
    private final List<AchievementApplication> requestedApplications;
    private final ConfigId configId;

    public AchievementApplicationApplied applyForAchievement(AchievementApplication application,
            AchievementConfigurationService achievementConfigurationService) {
        if (!application.isAchievementAvailableInEvaluationProcess(configId, achievementConfigurationService)) {
            throw new AchievementException();
        }

        if (conflictOfInterest(application, achievementConfigurationService)) {
            throw new AchievementException();
        }

        requestedApplications.add(application);
        return new AchievementApplicationApplied(application.getId().getId());
    }

    private boolean conflictOfInterest(AchievementApplication application, AchievementConfigurationService achievementConfigurationService) {
        return achievementIsAlreadyRequested(application) &&
               !application.canBeAppliedForMultipleTimes(achievementConfigurationService);
    }

    private boolean achievementIsAlreadyRequested(AchievementApplication application) {
        return requestedApplications.stream()
                .anyMatch(requestedApplication -> requestedApplication.areAchievementsEqual(application));
    }

    public AchievementApplicationRemoved removeAchievementApplication(AchievementApplicationId achievementApplicationId) {
        if (applicationDoesNotExist(achievementApplicationId)) {
            throw new AchievementException();
        }

        requestedApplications.removeIf(application -> application.getId().equals(achievementApplicationId));
        return new AchievementApplicationRemoved(achievementApplicationId.getId());
    }

    private boolean applicationDoesNotExist(AchievementApplicationId achievementApplicationId) {
        return requestedApplications.stream().noneMatch(application -> application.getId().equals(achievementApplicationId));
    }

    public AchievementApplicationUpdated updateAchievementApplication(AchievementApplicationId achievementApplicationId,
            ProposedOutcome proposedOutcome, List<Answer> answers,
            AchievementConfigurationService achievementConfigurationService) {
        var application = requestedApplications.stream()
                .filter(x-> x.getId().equals(achievementApplicationId))
                .findAny()
                .orElseThrow(AchievementException::new);

        return application.updateAchievementApplication(proposedOutcome, answers, achievementConfigurationService);
    }

    public AchievementCardMovedToPromoMeeting markAsReadyToPromoMeeting() {

        return new AchievementCardMovedToPromoMeeting(id.getId());
    }
}
