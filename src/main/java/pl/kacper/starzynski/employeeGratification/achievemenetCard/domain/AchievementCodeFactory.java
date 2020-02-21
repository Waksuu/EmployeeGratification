package pl.kacper.starzynski.employeeGratification.achievemenetCard.domain;

public class AchievementCodeFactory {
    public static AchievementCode create(String code) {
        return new AchievementCode(code);
    }
}
