package pl.kacper.starzynski.employeeGratification.achievementCard.readmodel;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerDTO implements Serializable {
    private final UUID questionnaireId;
    private final UUID questionId;
    private final String answer;
}
