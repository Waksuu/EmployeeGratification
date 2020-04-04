package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Immutable
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Achievement {
    private final AchievementCode achievementCode;

    abstract boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome);
    abstract boolean canBeAppliedForMultipleTimes();

    boolean isTheSameAchievement(AchievementCode achievementCode) {
        return this.achievementCode.equals(achievementCode);
    }
}
