package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import com.mongodb.annotations.Immutable;
import lombok.*;

@Immutable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AchievementCode {
    private final String code;
}
