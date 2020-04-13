package pl.kacper.starzynski.employeeGratification.achievement.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

class RepeatableAchievement extends Achievement {
    public RepeatableAchievement(AchievementCode achievementCode, List<QuestionId> questionIds) {
        super(achievementCode, questionIds);
    }

    @Override
    public boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome) {
        return proposedOutcome.isPositiveInteger();
    }

    @Override
    public boolean canBeAppliedForMultipleTimes() {
        return true;
    }
}
