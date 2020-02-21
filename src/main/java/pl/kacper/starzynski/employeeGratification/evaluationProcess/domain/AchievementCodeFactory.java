package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

public class AchievementCodeFactory {
    public static AchievementCode create(String code) {
        return new AchievementCode(code);
    }
}
