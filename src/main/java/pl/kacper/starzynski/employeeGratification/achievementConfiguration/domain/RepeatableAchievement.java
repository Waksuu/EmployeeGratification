package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

public class RepeatableAchievement extends Achievement {
    public RepeatableAchievement(AchievementCode achievementCode) {
        super(achievementCode);
    }

    @Override
    boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome) {
        return proposedOutcome.isPositiveInteger();
    }

    @Override
    boolean canBeAppliedForMultipleTimes() {
        return true;
    }
}
