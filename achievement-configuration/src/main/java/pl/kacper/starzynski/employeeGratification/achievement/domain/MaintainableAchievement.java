package pl.kacper.starzynski.employeeGratification.achievement.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

class MaintainableAchievement extends Achievement {
    private final List<AchievementOption> achievementOptions;

    public MaintainableAchievement(AchievementCode achievementCode, List<QuestionId> questionIds,
            List<AchievementOption> achievementOptions) {
        super(achievementCode, questionIds);
        this.achievementOptions = achievementOptions;
    }

    @Override
    public boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome) {
        return achievementOptions.stream().anyMatch(achievementOption -> achievementOption.isEqualTo(proposedOutcome));
    }

    @Override
    public boolean canBeAppliedForMultipleTimes() {
        return false;
    }
}
