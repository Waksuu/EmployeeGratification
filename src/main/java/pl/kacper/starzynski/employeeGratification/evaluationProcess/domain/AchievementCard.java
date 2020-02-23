package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AchievementCard {
    private final List<AchievementApplication> requestedApplications;

    AchievementApplicationApplied addApplication(AchievementApplication application) {
        if (conflictOfInterest(application)) {
            throw new AchievementException();
        }

        requestedApplications.add(application);
        return new AchievementApplicationApplied(application.getId());
    }

    private boolean conflictOfInterest(AchievementApplication application) {
        return achievementIsAlreadyRequested(application) && !application.canBeAppliedForMultipleTimes();
    }

    //TODO: Refactor
    private boolean achievementIsAlreadyRequested(AchievementApplication application) {
        return requestedApplications.stream().anyMatch(requestedApplication ->
                requestedApplication.getAchievementCode().equals(application.getAchievementCode()));
    }
}
