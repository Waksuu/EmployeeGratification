package pl.kacper.starzynski.employeeGratification.achievementConfiguration.infrastructure.mongo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain.AchievementConfiguration;
import pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain.AchievementConfigurationRepository;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;

import java.util.Optional;

@AllArgsConstructor
@Repository
class AchievementConfigurationRepositoryMongoAdapter implements AchievementConfigurationRepository {
    private final AchievementConfigurationMongoRepository achievementConfigurationMongoRepository;

    @Override
    public Optional<AchievementConfiguration> findById(AchievementConfigurationId configId) {
        return achievementConfigurationMongoRepository.findById(configId);
    }
}
