package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

class MaintainableAchievementApplication extends AchievementApplication {
    MaintainableAchievementApplication(UUID id, Achievement achievement, String  proposedOutcome) {
        super(id, achievement, proposedOutcome);
    }

    @Override
    boolean canBeAppliedForMultipleTimes() {
        return false;
    }
}
