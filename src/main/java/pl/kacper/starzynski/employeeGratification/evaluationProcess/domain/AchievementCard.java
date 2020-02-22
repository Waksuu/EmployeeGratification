package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class AchievementCard {

    private final List<AchievementApplication> requestedApplications = new ArrayList<>();
    private final AchievementConfigurationService achievementConfigurationService;

    public AchievementApplicationApplied addApplication(AchievementApplication application) {
        if (conflictOfInterest(application)) {
            throw new AchievementException();
        }

        requestedApplications.add(application);
        return new AchievementApplicationApplied(application.getAchievementCode());
    }

    private boolean conflictOfInterest(AchievementApplication application) {
        return requestedApplications.contains(application) &&
                achievementConfigurationService.applicationCannotBeDuplicate(application);

    }
}
