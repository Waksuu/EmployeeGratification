package pl.kacper.starzynski.employeeGratification.achievementCard.domain.events;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.DomainEvent;

@AllArgsConstructor
@Getter
@Immutable
public class AchievementApplicationRemoved extends DomainEvent {
    private final AchievementApplicationId achievementApplicationId;
}
