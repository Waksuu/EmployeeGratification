package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.UUID;

@Immutable
@Getter
@EqualsAndHashCode
public class AchievementApplicationId {
    private final UUID id;

    public AchievementApplicationId() {
        id = UUID.randomUUID();
    }

    @PersistenceConstructor
    private AchievementApplicationId(UUID id) {
        this.id = id;
    }
}
