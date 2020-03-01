package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

import com.mongodb.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.sharedKernel.DomainEvent;

@Immutable
@Getter
@AllArgsConstructor
public class AchievementCardMovedToPromoMeeting extends DomainEvent {
    private final UUID id;
}
