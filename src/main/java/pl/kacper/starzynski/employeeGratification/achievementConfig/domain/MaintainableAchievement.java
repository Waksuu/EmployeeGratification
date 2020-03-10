package pl.kacper.starzynski.employeeGratification.achievementConfig.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.List;

public class MaintainableAchievement extends Achievement {
    private final List<AchievementOption> achievementOptions;

    public MaintainableAchievement(AchievementCode achievementCode,
            List<AchievementOption> achievementOptions) {
        super(achievementCode);
        this.achievementOptions = achievementOptions;
    }

    @Override
    boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome) {
        return achievementOptions.stream().anyMatch(achievementOption -> achievementOption.isEqualTo(proposedOutcome));
    }

    @Override
    boolean canBeAppliedForMultipleTimes() {
        return false;
    }
}
