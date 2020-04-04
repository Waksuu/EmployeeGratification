package pl.kacper.starzynski.employeeGratification.achievementCard.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AnswerDTO implements Serializable {
    private final UUID questionnaireId;
    private final UUID questionId;
    private final String answer;
}
