package pl.kacper.starzynski.employeeGratification.achievementConfig.infrastructure;

import org.springframework.stereotype.Service;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCode;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.ConfigId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.ProposedOutcome;

@Service
public class AchievementConfigurationServiceImpl implements AchievementConfigurationService {

    //TODO: Implement me, maybe api call to external service? or business analyst sending a pigeon
    @Override
    public boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome) {
        return false;
    }

    @Override
    public boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode, ConfigId configId) {
        return true;
    }

    @Override
    public boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode) {
        return !achievementCode.getCode().contains("MA");
    }
}
