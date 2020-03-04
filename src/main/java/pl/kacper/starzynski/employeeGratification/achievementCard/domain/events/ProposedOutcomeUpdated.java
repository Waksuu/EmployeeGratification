package pl.kacper.starzynski.employeeGratification.achievementCard.domain.events;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.ProposedOutcome;

@AllArgsConstructor
@Getter
@Immutable
public class ProposedOutcomeUpdated {
    private final AchievementApplicationId achievementApplicationId;
    private final ProposedOutcome proposedOutcome;
}
