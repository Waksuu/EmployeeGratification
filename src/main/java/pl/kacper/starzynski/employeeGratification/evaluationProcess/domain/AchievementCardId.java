package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

import com.mongodb.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Immutable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AchievementCardId {
    private final UUID id;
}
