package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

public class AchievementApplicationFactory {
    public static AchievementApplication create(AchievementCode achievementCode,
                                                ProposedOutcome proposedOutcome,
                                                AchievementConfigurationService achievementConfigurationService) {
        var achievementType = achievementConfigurationService.getAchievementType(achievementCode);

        //TODO: Maybe factory of ProposedOutcome can validate this?
        if (!achievementConfigurationService.isProposedOutcomeValid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        return new AchievementApplication(UUID.randomUUID(),
                AchievementFactory.create(achievementCode, achievementType),
                proposedOutcome);
    }
}
