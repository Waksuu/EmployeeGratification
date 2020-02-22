package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

public class AchievementFactory {
    public static Achievement create(AchievementCode achievementCode, AchievementType achievementType) {
        return new Achievement(achievementCode, achievementType);
    }
}
