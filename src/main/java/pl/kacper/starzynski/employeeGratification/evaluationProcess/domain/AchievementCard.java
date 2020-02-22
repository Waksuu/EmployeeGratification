package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.ArrayList;
import java.util.List;

class AchievementCard {
    private final List<AchievementApplication> requestedApplications = new ArrayList<>();

    public AchievementApplicationApplied addApplication(AchievementApplication application) {
        if (conflictOfInterest(application)) {
            throw new AchievementException();
        }

        requestedApplications.add(application);
        return new AchievementApplicationApplied(application.getId());
    }

    private boolean conflictOfInterest(AchievementApplication application) {
        return requestedApplications.contains(application) && application.isMaintainableAchievement();

    }
}
