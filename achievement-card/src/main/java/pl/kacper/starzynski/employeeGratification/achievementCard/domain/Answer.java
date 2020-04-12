package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionId;

@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Answer {
    @EqualsAndHashCode.Include
    private final QuestionId questionId;
    private String answer = "";

    boolean isFilled() {
        return !answer.isBlank();
    }

    boolean isEqual(QuestionId questionId) {
        return this.questionId.equals(questionId);
    }

    void updateAnswer(String answer) {
        this.answer = answer;
    }
}
