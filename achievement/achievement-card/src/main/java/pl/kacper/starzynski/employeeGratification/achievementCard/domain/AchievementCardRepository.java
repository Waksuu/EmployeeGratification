package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;

import java.util.Optional;

public interface AchievementCardRepository {
    Optional<AchievementCard> findById(AchievementCardId achievementCardId);

    void save(AchievementCard achievementCard);
}
