package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class AchievementCard {

    private final List<AchievementCode> requestedAchievements = new ArrayList<>();
    private final AchievementConfigurationService achievementConfigurationService;

    AchievementApplied addAchievement(AchievementCode achievementCode, ProposedOutcome proposedOutcome) {

        //TODO: Should I create separate entity (AchievementApplication) to validate this rule?
        if (!achievementConfigurationService.isProposedOutcomeValid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        if (conflictOfInterest(achievementCode)) {
            throw new AchievementException();
        }

        requestedAchievements.add(achievementCode);
        return new AchievementApplied(achievementCode);
    }

    private boolean conflictOfInterest(AchievementCode achievementCode) {
        return achievementConfigurationService.achievementCannotBeDuplicate(achievementCode) &&
                requestedAchievements.contains(achievementCode);
    }
}
