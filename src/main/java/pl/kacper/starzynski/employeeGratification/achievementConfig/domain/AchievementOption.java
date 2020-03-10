package pl.kacper.starzynski.employeeGratification.achievementConfig.domain;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Immutable
@EqualsAndHashCode
@AllArgsConstructor
public class AchievementOption {
    private final String achievementOption;

    public boolean isEqualTo(ProposedOutcome proposedOutcome) {
        return proposedOutcome.isEqualTo(achievementOption);
    }
}
