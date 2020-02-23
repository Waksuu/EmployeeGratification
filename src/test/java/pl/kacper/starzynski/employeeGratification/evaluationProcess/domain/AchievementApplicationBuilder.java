package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;
import java.util.function.UnaryOperator;

import static org.mockito.Mockito.*;

public class AchievementApplicationBuilder {

    //TODO: Maybe implement more methods for specific applications?
    static AchievementApplication createAchievementApplication(UnaryOperator<AchievementApplicationFields.AchievementApplicationFieldsBuilder> overridingExpression) {
        var achievementConfigurationServiceMock = mock(AchievementConfigurationService.class);
        lenient().when(achievementConfigurationServiceMock.isProposedOutcomeValid(any(), any())).thenReturn(true);

        var defaultMaintainableApplication = AchievementApplicationFields.builder()
                .id(UUID.randomUUID())
                .achievementCode("MA001")
                .achievementType(AchievementType.MAINTAINABLE)
                .proposedOutcome("LOST")
                .achievementConfigurationService(achievementConfigurationServiceMock);

        var overriddenApplication = overridingExpression.apply(defaultMaintainableApplication).build();

        return AchievementApplicationFactory.create(overriddenApplication.getId(),
                AchievementFactory.create(AchievementCodeFactory.create(overriddenApplication.getAchievementCode()), overriddenApplication.getAchievementType()),
                overriddenApplication.getProposedOutcome(),
                overriddenApplication.getAchievementConfigurationService());

    }
}
