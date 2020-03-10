package pl.kacper.starzynski.employeeGratification.sharedKernel;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Immutable
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class ProposedOutcome {
    private final String proposedOutcome;
}
