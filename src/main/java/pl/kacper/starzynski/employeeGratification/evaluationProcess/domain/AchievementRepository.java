package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AchievementRepository extends MongoRepository<Achievement, AchievementCode> {
}
