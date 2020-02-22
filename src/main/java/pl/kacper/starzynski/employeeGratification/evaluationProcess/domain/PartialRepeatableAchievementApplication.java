package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

class PartialRepeatableAchievementApplication extends AchievementApplication {
    PartialRepeatableAchievementApplication(UUID id, Achievement achievement, String proposedOutcome) {
        super(id, achievement, proposedOutcome);
    }

    @Override
    boolean canBeAppliedForMultipleTimes() {
        return true;
    }
}
