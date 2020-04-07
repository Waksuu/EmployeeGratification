package pl.kacper.starzynski.employeeGratification.achievementCard.domain.events;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.QuestionnaireAnswer;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.DomainEvent;

import java.util.List;

@AllArgsConstructor
@Getter
@Immutable
public class QuestionnaireAnswersUpdated extends DomainEvent {
    private final AchievementApplicationId achievementApplicationId;
    private final List<QuestionnaireAnswer> answer;
}
