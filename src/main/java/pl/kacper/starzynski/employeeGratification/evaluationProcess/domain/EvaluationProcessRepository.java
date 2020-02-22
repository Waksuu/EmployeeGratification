package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EvaluationProcessRepository extends MongoRepository<EvaluationProcess, UUID> {
}
