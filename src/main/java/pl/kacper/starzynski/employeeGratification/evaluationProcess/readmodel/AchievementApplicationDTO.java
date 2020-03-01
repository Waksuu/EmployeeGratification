package pl.kacper.starzynski.employeeGratification.evaluationProcess.readmodel;

import java.io.Serializable;
import java.util.List;

import lombok.*;

@Getter
@AllArgsConstructor
public class AchievementApplicationDTO implements Serializable {
    private final String achievementCode;
    private final String proposedOutcome;
    private final List<AnswerDTO> answers;
}
