package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;

import java.util.List;

@AllArgsConstructor
public class AchievementConfiguration {
    @Id
    private final AchievementConfigurationId id;
    private final List<AchievementCode> availableAchievements;

    public boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode) {
        return availableAchievements.stream().anyMatch(achievement -> achievement.equals(achievementCode));
    }
}
