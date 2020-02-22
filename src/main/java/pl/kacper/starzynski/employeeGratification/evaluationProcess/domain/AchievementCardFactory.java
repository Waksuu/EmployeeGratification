package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

public class AchievementCardFactory {
    public static AchievementCard create(AchievementConfigurationService achievementConfigurationService) {
        return new AchievementCard(achievementConfigurationService);
    }
}
