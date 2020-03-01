package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

//TODO: Make it cachable
public interface AchievementConfigurationService {
    boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome);

    boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode, ConfigId configId);

    boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode);
}
