package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.sharedKernel.DomainEvent;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Immutable
class AchievementApplicationRemoved extends DomainEvent {
    private final UUID achievementApplicationId;
}
