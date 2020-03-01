package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AchievementCardFields {
    private AchievementCardId id;
    private List<AchievementApplication> requestedAchievements;
    private ConfigId configId;
}
