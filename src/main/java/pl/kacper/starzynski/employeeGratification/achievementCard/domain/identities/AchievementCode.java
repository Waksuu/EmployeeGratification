package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.*;

@Immutable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AchievementCode {
    private final String code;
}
