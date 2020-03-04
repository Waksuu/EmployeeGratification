package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionnaireId;

@AllArgsConstructor
@EqualsAndHashCode
@Immutable
//TODO: validate if question belongs to questionnaire - maybe?
public class QuestionnaireAnswer {
    private final QuestionnaireId questionnaireId;
    private final QuestionId questionId;
    private final Answer answer;

    boolean isAnswerFilled() {
        return !answer.isBlank();
    }
}
