package pl.kacper.starzynski.employeeGratification.achievemenetCard.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class AchievementCard {

    private final List<AchievementCode> availableAchievements;

    public AchievementApplied applyForAchievement(AchievementCode achievementCode,
                                                  ProposedOutcome proposedOutcome,
                                                  AchievementProposedOutcomeService achievementProposedOutcomeService) {
        if (!availableAchievements.contains(achievementCode)) {
            throw new AchievementException();
        }

        if (!achievementProposedOutcomeService.isProposedOutcomeValid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        return new AchievementApplied(achievementCode);
    }
}
