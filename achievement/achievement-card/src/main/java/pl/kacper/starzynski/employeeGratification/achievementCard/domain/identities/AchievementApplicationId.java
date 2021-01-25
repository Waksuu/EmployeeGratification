package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;


@Getter
@EqualsAndHashCode
public class AchievementApplicationId {
    private final UUID id = UUID.randomUUID();
}
