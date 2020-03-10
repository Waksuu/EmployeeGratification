package pl.kacper.starzynski.employeeGratification.achievementConfig.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementException;
import pl.kacper.starzynski.employeeGratification.achievementConfig.domain.AchievementConfiguration;
import pl.kacper.starzynski.employeeGratification.achievementConfig.domain.AchievementConfigurationRepository;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Service
@AllArgsConstructor
public class AchievementConfigurationServiceImpl implements AchievementConfigurationService {
    private final AchievementConfigurationRepository achievementConfigurationRepository;

    //TODO: Implement me, maybe api call to external service? or business analyst sending a pigeon
    @Override
    public boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome) {
        return false;
    }

    @Override
    public boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode, AchievementConfigurationId configId) {
        AchievementConfiguration configuration = achievementConfigurationRepository.findById(configId)
                .orElseThrow(AchievementException::new);
        return configuration.isAchievementAvailableInEvaluationProcess(achievementCode);
    }

    @Override
    public boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode) {
        return !achievementCode.getCode().contains("MA");
    }
}
