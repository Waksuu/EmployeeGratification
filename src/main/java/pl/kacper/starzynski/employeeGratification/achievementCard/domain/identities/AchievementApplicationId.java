package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import java.util.UUID;

import com.mongodb.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Immutable
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AchievementApplicationId {
    private final UUID id;
}
