package pl.kacper.starzynski.employeeGratification.achievementCard.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementCard;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;

public interface AchievementCardMongoRepository extends MongoRepository<AchievementCard, AchievementCardId> {
}
