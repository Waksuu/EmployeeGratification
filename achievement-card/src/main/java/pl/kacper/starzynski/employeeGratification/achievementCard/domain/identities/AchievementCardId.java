package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Immutable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AchievementCardId {
    private final UUID id;
}
