package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;

import java.util.Optional;

public interface AchievementConfigurationRepository {
    Optional<AchievementConfiguration> findById(AchievementConfigurationId configId);
}
