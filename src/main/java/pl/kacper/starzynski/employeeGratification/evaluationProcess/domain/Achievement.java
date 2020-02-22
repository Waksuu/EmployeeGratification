package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Immutable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Achievement {
    @EqualsAndHashCode.Include
    private final AchievementCode achievementCode;
    private final AchievementType achievementType;
}
