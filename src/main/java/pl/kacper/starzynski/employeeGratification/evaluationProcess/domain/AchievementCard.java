package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.ArrayList;
import java.util.List;

class AchievementCard {

    private final List<AchievementCode> requestedAchievements = new ArrayList<>();

    AchievementApplied addAchievement(AchievementCode achievementCode,
                                      ProposedOutcome proposedOutcome,
                                      AchievementConfigurationService achievementConfigurationService) {

        //TODO: Should I create separate entity (AchievementApplication) to validate this rule?
        if (!achievementConfigurationService.isProposedOutcomeValid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        if (conflictOfInterest(achievementCode, achievementConfigurationService)) {
            throw new AchievementException();
        }

        requestedAchievements.add(achievementCode);
        return new AchievementApplied(achievementCode);
    }

    private boolean conflictOfInterest(AchievementCode achievementCode, AchievementConfigurationService achievementConfigurationService) {
        return achievementCannotBeDuplicate(achievementCode, achievementConfigurationService) && requestedAchievements.contains(achievementCode);
    }

    private boolean achievementCannotBeDuplicate(AchievementCode achievementCode, AchievementConfigurationService achievementConfigurationService) {
        AchievementType achievementType = achievementConfigurationService.getAchievementType(achievementCode);
        return achievementType.equals(AchievementType.MAINTAINABLE);
    }
}
