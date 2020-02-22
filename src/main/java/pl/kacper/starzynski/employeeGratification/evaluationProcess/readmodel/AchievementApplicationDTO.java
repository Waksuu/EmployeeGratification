package pl.kacper.starzynski.employeeGratification.evaluationProcess.readmodel;

import lombok.Data;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.domain.AchievementCode;

import java.util.UUID;

@Data
public class AchievementApplicationDTO {
    private final UUID evaluationProcessId;
    private final AchievementCode achievementCode;
    private final String proposedOutcome;
    private final String someJsonWithQuestionsAndAnswers;
}
