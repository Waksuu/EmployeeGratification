package pl.kacper.starzynski.employeeGratification.achievementCard.domain.events;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.sharedKernel.DomainEvent;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Immutable
public class AchievementApplicationRemoved extends DomainEvent {
    private final UUID achievementApplicationId;
}
