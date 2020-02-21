package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.ArrayList;
import java.util.List;

class AchievementCard {

    private final List<AchievementCode> requestedAchievements = new ArrayList<>();

    AchievementApplied addAchievement(AchievementCode achievementCode,
                                      ProposedOutcome proposedOutcome,
                                      AchievementConfigurationService achievementConfigurationService) {

        if (!achievementConfigurationService.isProposedOutcomeValid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        if (requestedMaintainableAchievementWasAlreadyRequested(achievementCode, achievementConfigurationService)) {
            throw new AchievementException();
        }

        requestedAchievements.add(achievementCode);
        return new AchievementApplied(achievementCode);
    }

    private boolean requestedMaintainableAchievementWasAlreadyRequested(AchievementCode achievementCode,
                                                                        AchievementConfigurationService achievementConfigurationService) {
        AchievementType achievementType = achievementConfigurationService.getAchievementType(achievementCode);
        return achievementType.equals(AchievementType.MAINTAINABLE) && requestedAchievements.contains(achievementCode);
    }
}
