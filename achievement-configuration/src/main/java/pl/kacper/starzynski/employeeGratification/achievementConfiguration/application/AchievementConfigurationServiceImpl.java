package pl.kacper.starzynski.employeeGratification.achievementConfiguration.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.ports.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain.AchievementConfigurationRepository;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Service
@AllArgsConstructor
@Transactional
public class AchievementConfigurationServiceImpl implements AchievementConfigurationService {
    private final AchievementConfigurationRepository achievementConfigurationRepository;

    public boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            AchievementConfigurationId configId) {
        var configuration = achievementConfigurationRepository.findById(configId)
                .orElseThrow(AchievementException::new);
        return configuration.isProposedOutcomeInvalid(achievementCode, proposedOutcome);
    }

    public boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode, AchievementConfigurationId configId) {
        var configuration = achievementConfigurationRepository.findById(configId)
                .orElseThrow(AchievementException::new);
        return configuration.isAchievementAvailableInEvaluationProcess(achievementCode);
    }

    public boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode, AchievementConfigurationId configId) {
        var configuration = achievementConfigurationRepository.findById(configId)
                .orElseThrow(AchievementException::new);
        return configuration.canBeAppliedForMultipleTimes(achievementCode);
    }

}
