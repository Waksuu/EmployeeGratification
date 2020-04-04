package pl.kacper.starzynski.employeeGratification.achievementCard.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class AchievementApplicationDTO implements Serializable {
    private final String achievementCode;
    private final String proposedOutcome;
    private final List<AnswerDTO> answers;
}
