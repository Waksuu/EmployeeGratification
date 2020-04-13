package pl.kacper.starzynski.employeeGratification.achievement.domain;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;

import java.util.Optional;

public interface AchievementRepository {
    Optional<Achievement> findById(AchievementCode achievementCode);
}
