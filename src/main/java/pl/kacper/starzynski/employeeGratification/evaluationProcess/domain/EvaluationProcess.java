package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class EvaluationProcess {
    private final AchievementCard achievementCard;
    private final List<AchievementCode> availableAchievements;

    public AchievementApplied applyForAchievement(AchievementCode achievementCode,
                                                  ProposedOutcome proposedOutcome,
                                                  AchievementConfigurationService achievementConfigurationService) {
        if (achievementIsNotAvailableInEvaluationProcess(achievementCode)) {
            throw new AchievementException();
        }

        return achievementCard.addAchievement(achievementCode, proposedOutcome, achievementConfigurationService);
    }

    private boolean achievementIsNotAvailableInEvaluationProcess(AchievementCode achievementCode) {
        return !availableAchievements.contains(achievementCode);
    }

}
