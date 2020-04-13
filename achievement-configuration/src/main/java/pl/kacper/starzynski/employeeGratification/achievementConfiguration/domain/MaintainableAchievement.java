package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

class MaintainableAchievement extends Achievement {
    private final List<AchievementOption> achievementOptions;

    MaintainableAchievement(AchievementCode achievementCode, List<QuestionId> questionIds,
            List<AchievementOption> achievementOptions) {
        super(achievementCode, questionIds);
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
