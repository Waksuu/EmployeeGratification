package pl.kacper.starzynski.employeeGratification.evaluationProcess.readmodel;

import lombok.*;

@Getter
@RequiredArgsConstructor
@Setter
public class AchievementApplicationDTO {
    private final String achievementCode;
    private final String proposedOutcome;
    private final String someJsonWithQuestionsAndAnswers;
}
