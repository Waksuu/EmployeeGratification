package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;

public interface AchievementCardRepository extends MongoRepository<AchievementCard, AchievementCardId> {
}
