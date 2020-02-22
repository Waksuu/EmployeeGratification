package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
//TODO: Is it good aggregate root?
public class EvaluationProcess {
    private final AchievementCard achievementCard;
    private final List<AchievementCode> availableAchievements;
    private AchievementConfigurationService achievementConfigurationService;

    public AchievementApplicationApplied applyForAchievement(AchievementCode achievementCode, ProposedOutcome proposedOutcome) {
        if (achievementIsNotAvailableInEvaluationProcess(achievementCode)) {
            throw new AchievementException();
        }

        var application = AchievementApplicationFactory.create(achievementCode, proposedOutcome, achievementConfigurationService);
        return achievementCard.addApplication(application);
    }

    private boolean achievementIsNotAvailableInEvaluationProcess(AchievementCode achievementCode) {
        return !availableAchievements.contains(achievementCode);
    }

//    public AchievementApplicationRemoved removeAchievementApplication() {
//        return achievementCard.removeApplication
//    }
}
