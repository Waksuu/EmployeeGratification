package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

public class AchievementApplicationFactory {
    public static AchievementApplication create(AchievementCode achievementCode,
                                                ProposedOutcome proposedOutcome,
                                                AchievementConfigurationService achievementConfigurationService) {
        if (!achievementConfigurationService.isProposedOutcomeValid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        return new AchievementApplication(achievementCode, proposedOutcome);
    }
}
