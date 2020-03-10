package pl.kacper.starzynski.employeeGratification.achievementConfig.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;

public interface AchievementConfigurationRepository extends MongoRepository<AchievementConfiguration, AchievementConfigurationId> {
}
