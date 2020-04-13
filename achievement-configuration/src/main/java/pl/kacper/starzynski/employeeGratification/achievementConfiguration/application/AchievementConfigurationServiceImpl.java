package pl.kacper.starzynski.employeeGratification.achievementConfiguration.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kacper.starzynski.employeeGratification.achievement.domain.Achievement;
import pl.kacper.starzynski.employeeGratification.achievement.domain.AchievementRepository;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain.AchievementConfiguration;
import pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain.AchievementConfigurationRepository;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Service
@AllArgsConstructor
@Transactional
class AchievementConfigurationServiceImpl implements AchievementConfigurationService {
    private final AchievementConfigurationRepository achievementConfigurationRepository;
    private final AchievementRepository achievementRepository;

    public boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            AchievementConfigurationId configId) {
        var achievement = getAchievement(achievementCode);
        return !achievement.isProposedOutcomeValid(proposedOutcome);
    }

    private Achievement getAchievement(AchievementCode achievementCode) {
        return achievementRepository.findById(achievementCode).orElseThrow(AchievementException::new);
    }

    public boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode,
            AchievementConfigurationId configId) {
        var configuration = getConfiguration(configId);
        return configuration.isAchievementAvailableInEvaluationProcess(achievementCode);
    }

    public boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode, AchievementConfigurationId configId) {
        var achievement = getAchievement(achievementCode);
        return achievement.canBeAppliedForMultipleTimes();
    }

    private AchievementConfiguration getConfiguration(AchievementConfigurationId configId) {
        return achievementConfigurationRepository.findById(configId).orElseThrow(AchievementException::new);
    }

}
