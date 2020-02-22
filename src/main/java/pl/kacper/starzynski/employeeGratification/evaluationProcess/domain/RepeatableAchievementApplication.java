package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

class RepeatableAchievementApplication extends AchievementApplication {
    RepeatableAchievementApplication(UUID id, Achievement achievement, String proposedOutcome) {
        super(id, achievement, proposedOutcome);
    }

    @Override
    boolean canBeAppliedForMultipleTimes() {
        return true;
    }
}
