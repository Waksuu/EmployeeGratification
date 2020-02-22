package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Immutable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Achievement {
    @EqualsAndHashCode.Include
    @Getter
    private final AchievementCode achievementCode;
    private final AchievementType achievementType;

    boolean isMaintainableAchievement() {
        return achievementType.equals(AchievementType.MAINTAINABLE);
    }
}
