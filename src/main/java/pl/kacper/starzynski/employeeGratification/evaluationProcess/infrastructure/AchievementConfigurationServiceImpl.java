package pl.kacper.starzynski.employeeGratification.evaluationProcess.infrastructure;

import org.springframework.stereotype.Service;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.domain.AchievementCode;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.domain.AchievementConfigurationService;

@Service
public class AchievementConfigurationServiceImpl implements AchievementConfigurationService {

    //TODO: Implement me, maybe api call to external service? or business analyst sending a pigeon
    @Override
    public boolean isProposedOutcomeValid(AchievementCode achievementCode, String proposedOutcome) {
        return true;
    }
}
