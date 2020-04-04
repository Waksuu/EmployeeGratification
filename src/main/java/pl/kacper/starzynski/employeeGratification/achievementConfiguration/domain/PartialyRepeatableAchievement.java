package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

public class PartialyRepeatableAchievement extends Achievement {
    public PartialyRepeatableAchievement(AchievementCode achievementCode) {
        super(achievementCode);
    }

    @Override
    boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome) {
        return proposedOutcome.isUnitInterval();
    }

    @Override
    boolean canBeAppliedForMultipleTimes() {
        return true;
    }
}
