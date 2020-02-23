package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.ArrayList;

public class AchievementCardFactory {
    public static AchievementCard create() {
        return new AchievementCard(new ArrayList<>());
    }
}
