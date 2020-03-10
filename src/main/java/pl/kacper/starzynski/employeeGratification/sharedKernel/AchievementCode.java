package pl.kacper.starzynski.employeeGratification.sharedKernel;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Immutable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AchievementCode {
    private final String code;
}
