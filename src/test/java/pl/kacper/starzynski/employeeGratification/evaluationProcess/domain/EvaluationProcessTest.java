package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//TODO: Refactor tests
@ExtendWith(MockitoExtension.class)
class EvaluationProcessTest {
    private static final List<AchievementCode> AVAILABLE_ACHIEVEMENTS = Arrays.asList(
            AchievementCodeFactory.create("MA001"),
            AchievementCodeFactory.create("MA002"),
            AchievementCodeFactory.create("RA001"),
            AchievementCodeFactory.create("RA002")
    );
    private static final String LOST = "LOST";
    private static final String MAINTAINED = "MAINTAINED";
    private static final UUID ACHIEVEMENT_APPLICATION_ID = UUID.randomUUID();

    private EvaluationProcess evaluationProcess;

    @Mock
    private AchievementConfigurationService achievementConfigurationService;

    @BeforeEach
    void init() {
        evaluationProcess = EvaluationProcessFactory.create(UUID.randomUUID(),
                AchievementCardFactory.create(),
                AVAILABLE_ACHIEVEMENTS);
        lenient().when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);
    }

    @Test
    void shouldAddAchievementApplication_achievementIsAvailableInCurrentEvaluation() {
        // GIVEN
        var achievementCode = AchievementCodeFactory.create("MA001");
        var achievement = AchievementFactory.create(achievementCode, AchievementType.MAINTAINABLE);
        var application = AchievementApplicationFactory.create(ACHIEVEMENT_APPLICATION_ID,
                achievement,
                LOST,
                achievementConfigurationService);

        // WHEN
        var event = evaluationProcess.applyForAchievement(application);

        // THEN
        assertEquals(ACHIEVEMENT_APPLICATION_ID, event.getAchievementApplicationId());
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_achievementIsNotAvailableInCurrentEvaluation() {
        // GIVEN
        var achievementCode = AchievementCodeFactory.create("MA003");
        var achievement = AchievementFactory.create(achievementCode, AchievementType.MAINTAINABLE);
        var application = AchievementApplicationFactory.create(ACHIEVEMENT_APPLICATION_ID,
                achievement,
                LOST,
                achievementConfigurationService);

        // WHEN & Exception
        assertThrows(AchievementException.class, () ->
                evaluationProcess.applyForAchievement(application)
        );
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_sameMaintainableAchievementIsAlreadyAdded() {
        // GIVEN
        var achievementCode = AchievementCodeFactory.create("MA001");
        var achievement = AchievementFactory.create(achievementCode, AchievementType.MAINTAINABLE);

        var firstApplication = AchievementApplicationFactory.create(ACHIEVEMENT_APPLICATION_ID,
                achievement,
                LOST,
                achievementConfigurationService);

        var secondApplication = AchievementApplicationFactory.create(UUID.randomUUID(),
                achievement,
                MAINTAINED,
                achievementConfigurationService);

        // WHEN & Exception
        evaluationProcess.applyForAchievement(firstApplication);
        assertThrows(AchievementException.class, () -> evaluationProcess.applyForAchievement(secondApplication));
    }

    @Test
    void shouldAddAchievementApplication_twoDifferentAchievements() {
        // GIVEN
        var firstCode = AchievementCodeFactory.create("MA001");
        var firstAchievement = AchievementFactory.create(firstCode, AchievementType.MAINTAINABLE);
        var firstApplication = AchievementApplicationFactory.create(ACHIEVEMENT_APPLICATION_ID,
                firstAchievement,
                LOST,
                achievementConfigurationService);

        var secondAchievementId = UUID.randomUUID();
        var secondCode = AchievementCodeFactory.create("MA002");
        var secondAchievement = AchievementFactory.create(secondCode, AchievementType.MAINTAINABLE);
        var secondApplication = AchievementApplicationFactory.create(secondAchievementId,
                secondAchievement,
                MAINTAINED,
                achievementConfigurationService);
        // WHEN
        var firstEvent = evaluationProcess.applyForAchievement(firstApplication);
        var secondEvent = evaluationProcess.applyForAchievement(secondApplication);

        // THEN
        assertEquals(ACHIEVEMENT_APPLICATION_ID, firstEvent.getAchievementApplicationId());
        assertEquals(secondAchievementId, secondEvent.getAchievementApplicationId());
    }

    @Test
    void shouldAddAchievementApplication_twoSameRepeatableAchievements() {
        // GIVEN
        var achievementCode = AchievementCodeFactory.create("RA001");
        var achievement = AchievementFactory.create(achievementCode, AchievementType.REPEATABLE);
        var firstApplication = AchievementApplicationFactory.create(ACHIEVEMENT_APPLICATION_ID,
                achievement,
                "1",
                achievementConfigurationService);

        var secondAchievementId = UUID.randomUUID();
        var secondApplication = AchievementApplicationFactory.create(secondAchievementId,
                achievement,
                "3",
                achievementConfigurationService);

        // WHEN
        var firstEvent = evaluationProcess.applyForAchievement(firstApplication);
        var secondEvent = evaluationProcess.applyForAchievement(secondApplication);

        // THEN
        assertEquals(ACHIEVEMENT_APPLICATION_ID, firstEvent.getAchievementApplicationId());
        assertEquals(secondAchievementId, secondEvent.getAchievementApplicationId());
    }

}