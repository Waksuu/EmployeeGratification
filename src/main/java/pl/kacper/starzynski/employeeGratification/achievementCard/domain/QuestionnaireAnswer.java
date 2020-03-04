package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionnaireId;

@AllArgsConstructor
@EqualsAndHashCode
//TODO: validate if question belongs to questionnaire - maybe?
public class QuestionnaireAnswer {
    private final QuestionnaireId questionnaireId;
    private final QuestionId questionId;
    private final String answer;

    boolean isAnswerFilled() {
        return !answer.isBlank();
    }
}
