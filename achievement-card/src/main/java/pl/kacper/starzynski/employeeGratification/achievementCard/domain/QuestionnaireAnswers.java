package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.util.Pair;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Immutable
@Getter
public class QuestionnaireAnswers {
    private final List<Answer> answers;

    boolean areAnswersFilled() {
        return answers.stream().allMatch(Answer::isFilled);
    }

    void updateAnswers(List<Pair<QuestionId, String>> answers) {
        answers.forEach(this::updateAnswer);
    }

    private void updateAnswer(Pair<QuestionId, String> answer) {
        var answerToUpdate = getAnswerToUpdate(answer);
        answerToUpdate.updateAnswer(answer.getSecond());
    }

    private Answer getAnswerToUpdate(Pair<QuestionId, String> answer) {
        return this.answers.stream()
                .filter(a -> a.isEqual(answer.getFirst()))
                .findFirst()
                .orElseThrow(AchievementException::new);
    }
}
