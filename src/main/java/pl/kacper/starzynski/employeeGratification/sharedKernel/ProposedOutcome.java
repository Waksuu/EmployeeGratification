package pl.kacper.starzynski.employeeGratification.sharedKernel;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Immutable
@AllArgsConstructor
@EqualsAndHashCode
public class ProposedOutcome {
    private final String proposedOutcome;

    public boolean isEqualTo(String achievementOption) {
        return proposedOutcome.equals(achievementOption);
    }

    public boolean isPositiveInteger() {
        int value;
        try {
            value = Integer.parseInt(proposedOutcome);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return value >= 1;
    }

    public boolean isUnitInterval() {
        float value;
        try {
            value = Float.parseFloat(proposedOutcome);
        }
        catch (NumberFormatException e) {
            return false;
        }

        return value <= 1 && value >= 0;
    }
}
