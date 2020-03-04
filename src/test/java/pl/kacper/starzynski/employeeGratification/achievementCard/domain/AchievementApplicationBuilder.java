package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCode;

import java.util.Collections;
import java.util.UUID;
import java.util.function.UnaryOperator;

import static org.mockito.Mockito.*;

public class AchievementApplicationBuilder {

    //TODO: Maybe implement more methods for specific applications?
    //TODO: Refactor
    static AchievementApplication createMaintainableAchievementApplication(
            UnaryOperator<AchievementApplicationFields.AchievementApplicationFieldsBuilder> overridingExpression) {
        var achievementConfigurationServiceMock = mock(AchievementConfigurationService.class);
        lenient().when(achievementConfigurationServiceMock.isProposedOutcomeInvalid(any(), any())).thenReturn(false);
        lenient().when(achievementConfigurationServiceMock.canBeAppliedForMultipleTimes(any())).thenReturn(false);

        var defaultMaintainableApplication = AchievementApplicationFields.builder()
                .id(new AchievementApplicationId(UUID.randomUUID()))
                .achievementCode("MA001")
                .proposedOutcome("LOST")
                .achievementConfigurationService(achievementConfigurationServiceMock)
                .answers(Collections.emptyList());

        var overriddenApplication = overridingExpression.apply(defaultMaintainableApplication).build();
        return getAchievementApplication(overriddenApplication);

    }

    static AchievementApplication createRepeatableAchievementApplication(
            UnaryOperator<AchievementApplicationFields.AchievementApplicationFieldsBuilder> overridingExpression) {
        var achievementConfigurationServiceMock = mock(AchievementConfigurationService.class);
        lenient().when(achievementConfigurationServiceMock.isProposedOutcomeInvalid(any(), any())).thenReturn(false);
        lenient().when(achievementConfigurationServiceMock.canBeAppliedForMultipleTimes(any())).thenReturn(true);

        var defaultMaintainableApplication = AchievementApplicationFields.builder()
                .id(new AchievementApplicationId(UUID.randomUUID()))
                .achievementCode("RA001")
                .proposedOutcome("1")
                .achievementConfigurationService(achievementConfigurationServiceMock)
                .answers(Collections.emptyList());

        var overriddenApplication = overridingExpression.apply(defaultMaintainableApplication).build();
        return getAchievementApplication(overriddenApplication);

    }

    static AchievementApplication createPartialRepeatableAchievementApplication(
            UnaryOperator<AchievementApplicationFields.AchievementApplicationFieldsBuilder> overridingExpression) {
        var achievementConfigurationServiceMock = mock(AchievementConfigurationService.class);
        lenient().when(achievementConfigurationServiceMock.isProposedOutcomeInvalid(any(), any())).thenReturn(false);
        lenient().when(achievementConfigurationServiceMock.canBeAppliedForMultipleTimes(any())).thenReturn(true);

        var defaultMaintainableApplication = AchievementApplicationFields.builder()
                .id(new AchievementApplicationId(UUID.randomUUID()))
                .achievementCode("RP001")
                .proposedOutcome("0.25")
                .achievementConfigurationService(achievementConfigurationServiceMock)
                .answers(Collections.emptyList());

        var overriddenApplication = overridingExpression.apply(defaultMaintainableApplication).build();
        return getAchievementApplication(overriddenApplication);

    }

    private static AchievementApplication getAchievementApplication(AchievementApplicationFields overriddenApplication) {
        return AchievementApplicationFactory.create(overriddenApplication.getId(),
                new AchievementCode(overriddenApplication.getAchievementCode()),
                new ProposedOutcome(overriddenApplication.getProposedOutcome()),
                overriddenApplication.getAnswers(),
                overriddenApplication.getAchievementConfigurationService());
    }
}
