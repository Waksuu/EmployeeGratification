package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Immutable
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AchievementApplicationId {
    private final UUID id;
}
