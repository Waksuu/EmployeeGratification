package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Immutable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class AchievementCardId {
    private final UUID id;
}
