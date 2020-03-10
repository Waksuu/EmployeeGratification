package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

//TODO: Make it cachable
public interface AchievementConfigurationService {
    boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome);

    boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode, AchievementConfigurationId configId);

    boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode);
}
