package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

import com.mongodb.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Immutable
@AllArgsConstructor
public class ConfigId {
    private final UUID configId;
}
