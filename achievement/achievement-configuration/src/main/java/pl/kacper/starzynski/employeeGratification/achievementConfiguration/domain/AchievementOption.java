package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Immutable
@EqualsAndHashCode
@AllArgsConstructor
class AchievementOption {
    private final String achievementOption;

    boolean isEqualTo(ProposedOutcome proposedOutcome) {
        return proposedOutcome.isEqualTo(achievementOption);
    }
}
