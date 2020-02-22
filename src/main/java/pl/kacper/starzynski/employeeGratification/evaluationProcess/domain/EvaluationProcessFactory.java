package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;
import java.util.UUID;

public class EvaluationProcessFactory {
    public static EvaluationProcess create(UUID id,
                                           AchievementCard achievementCard,
                                           List<AchievementCode> availableAchievements) {
        return new EvaluationProcess(id, achievementCard, availableAchievements);
    }
}
