package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;

public class EvaluationProcessFactory {
    public static EvaluationProcess create(long id,
                                           AchievementCard achievementCard,
                                           List<AchievementCode> availableAchievements) {
        return new EvaluationProcess(id, achievementCard, availableAchievements);
    }
}
