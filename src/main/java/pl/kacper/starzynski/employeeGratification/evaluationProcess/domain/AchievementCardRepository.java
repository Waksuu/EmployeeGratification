package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AchievementCardRepository extends MongoRepository<AchievementCard, AchievementCardId> {
}
