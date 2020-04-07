package pl.kacper.starzynski.employeeGratification.achievementCard.domain.ports;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

//TODO: Find better domain names
public interface AchievementConfigurationService {
    boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            AchievementConfigurationId configId);

    boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode, AchievementConfigurationId configId);

    boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode, AchievementConfigurationId configId);
}
