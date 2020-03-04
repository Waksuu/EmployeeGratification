package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCode;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.ConfigId;

//TODO: Make it cachable
public interface AchievementConfigurationService {
    boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome);

    boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode, ConfigId configId);

    boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode);
}
