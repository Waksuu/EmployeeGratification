package pl.kacper.starzynski.employeeGratification.achievement.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

class PartialyRepeatableAchievement extends Achievement {
    public PartialyRepeatableAchievement(AchievementCode achievementCode, List<QuestionId> questionIds) {
        super(achievementCode, questionIds);
    }

    @Override
    public boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome) {
        return proposedOutcome.isUnitInterval();
    }

    @Override
    public boolean canBeAppliedForMultipleTimes() {
        return true;
    }
}
