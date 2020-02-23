package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EvaluationProcessRepository extends MongoRepository<EvaluationProcess, Long> {
}
