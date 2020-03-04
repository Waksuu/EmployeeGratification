package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Answer {
    private final UUID questionId;
    private final String answer;

    boolean isAnswerFilled() {
        return !answer.isBlank();
    }
}
