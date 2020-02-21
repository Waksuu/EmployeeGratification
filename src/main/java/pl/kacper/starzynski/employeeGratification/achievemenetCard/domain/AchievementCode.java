package pl.kacper.starzynski.employeeGratification.achievemenetCard.domain;

import com.mongodb.annotations.Immutable;
import lombok.*;

@Immutable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@ToString
@EqualsAndHashCode
public class AchievementCode {
    private final String code;
}
