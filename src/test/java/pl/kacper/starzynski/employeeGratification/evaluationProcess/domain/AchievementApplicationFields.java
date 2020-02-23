package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class AchievementApplicationFields {
    private UUID id;
    private String achievementCode;
    private AchievementType achievementType;
    private String proposedOutcome;
    private AchievementConfigurationService achievementConfigurationService;
}
