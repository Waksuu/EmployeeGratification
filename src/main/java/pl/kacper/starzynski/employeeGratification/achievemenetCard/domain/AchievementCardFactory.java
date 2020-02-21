package pl.kacper.starzynski.employeeGratification.achievemenetCard.domain;

import java.util.List;

public class AchievementCardFactory {
    public static AchievementCard create(List<AchievementCode> availableAchievements) {
        return new AchievementCard(availableAchievements);
    }

}
