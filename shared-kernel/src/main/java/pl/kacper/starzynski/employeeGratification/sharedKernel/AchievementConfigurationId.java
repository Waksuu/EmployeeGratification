package pl.kacper.starzynski.employeeGratification.sharedKernel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AchievementConfigurationId {
    private final UUID id;
}
