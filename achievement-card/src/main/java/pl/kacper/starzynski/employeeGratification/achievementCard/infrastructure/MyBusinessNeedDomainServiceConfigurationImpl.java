package pl.kacper.starzynski.employeeGratification.achievementCard.infrastructure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.MyBusinessNeedDomainService;
import pl.kacper.starzynski.employeeGratification.achievementConfiguration.application.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Service
@AllArgsConstructor
//TODO: Make it cachable
class MyBusinessNeedDomainServiceConfigurationImpl implements MyBusinessNeedDomainService {

    private final AchievementConfigurationService achievementConfigurationService;

    @Override
    public boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            AchievementConfigurationId configId) {
        return achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome, configId);
    }

    @Override
    public boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode,
            AchievementConfigurationId configId) {
        return achievementConfigurationService.isAchievementAvailableInEvaluationProcess(achievementCode, configId);
    }

    @Override
    public boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode, AchievementConfigurationId configId) {
        return achievementConfigurationService.canBeAppliedForMultipleTimes(achievementCode, configId);
    }
}
