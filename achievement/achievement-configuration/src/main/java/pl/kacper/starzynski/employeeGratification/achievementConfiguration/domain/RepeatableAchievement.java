package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

class RepeatableAchievement extends Achievement {
    RepeatableAchievement(AchievementCode achievementCode, List<QuestionId> questionIds) {
        super(achievementCode, questionIds);
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
