package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
abstract class AchievementApplication {
    @Id
    @Getter
    @EqualsAndHashCode.Include
    //TODO: Think of some natural identity
    private final UUID id;
    private final Achievement achievement;
    private final String proposedOutcome;

    abstract boolean canBeAppliedForMultipleTimes();

    boolean areAchievementsEqual(AchievementApplication application) {
        return areAchievementsEqual(application.achievement.getAchievementCode());
    }

    //TODO: Maybe move it even further down (to achievement?)
    boolean areAchievementsEqual(AchievementCode code) {
        return achievement.getAchievementCode().equals(code);
    }
}
