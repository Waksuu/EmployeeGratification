package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.sharedKernel.DomainEvent;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class AchievementApplicationUpdated extends DomainEvent {
    private final UUID achievementApplicationId;
    private final String proposedOutcome;
    private final List<Answer> answers;
}
