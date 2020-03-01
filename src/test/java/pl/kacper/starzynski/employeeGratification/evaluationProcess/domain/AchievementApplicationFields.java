package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class AchievementApplicationFields {
    private AchievementApplicationId id;
    private String achievementCode;
    private String proposedOutcome;
    private AchievementConfigurationService achievementConfigurationService;
    private List<Answer> answers;
}
