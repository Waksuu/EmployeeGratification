package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Immutable
@Getter
@EqualsAndHashCode
public class AchievementApplicationId {
    private final UUID id = UUID.randomUUID();
}
