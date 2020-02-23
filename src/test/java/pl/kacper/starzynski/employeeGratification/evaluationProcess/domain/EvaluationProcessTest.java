package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EvaluationProcessTest {
    private static final List<AchievementCode> AVAILABLE_ACHIEVEMENTS = Arrays.asList(
            AchievementCodeFactory.create("MA001"),
            AchievementCodeFactory.create("MA002"),
            AchievementCodeFactory.create("RA001"),
            AchievementCodeFactory.create("RA002")
    );

    private EvaluationProcess evaluationProcess;

    @BeforeEach
    void init() {
        evaluationProcess = EvaluationProcessFactory.create(1L, AchievementCardFactory.create(), AVAILABLE_ACHIEVEMENTS);
    }

    @Test
    void shouldAddAchievementApplication_achievementIsAvailableInCurrentEvaluation() {
        // GIVEN
        var application = AchievementApplicationBuilder.createAchievementApplication(UnaryOperator.identity());

        // WHEN
        var event = evaluationProcess.applyForAchievement(application);

        // THEN
        assertEquals(application.getId(), event.getAchievementApplicationId());
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_achievementIsNotAvailableInCurrentEvaluation() {
        // GIVEN
        var application = AchievementApplicationBuilder.createAchievementApplication(x -> x.achievementCode("MA003"));

        // WHEN & Exception
        assertThrows(AchievementException.class, () ->
                evaluationProcess.applyForAchievement(application)
        );
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_sameMaintainableAchievementIsAlreadyAdded() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createAchievementApplication(UnaryOperator.identity());
        var secondApplication = AchievementApplicationBuilder.createAchievementApplication(UnaryOperator.identity());

        // WHEN & Exception
        evaluationProcess.applyForAchievement(firstApplication);
        assertThrows(AchievementException.class, () -> evaluationProcess.applyForAchievement(secondApplication));
    }

    @Test
    void shouldAddAchievementApplication_twoDifferentAchievements() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createAchievementApplication(UnaryOperator.identity());
        var secondApplication = AchievementApplicationBuilder.createAchievementApplication(x -> x.achievementCode("MA002"));

        // WHEN
        var firstEvent = evaluationProcess.applyForAchievement(firstApplication);
        var secondEvent = evaluationProcess.applyForAchievement(secondApplication);

        // THEN
        assertEquals(firstApplication.getId(), firstEvent.getAchievementApplicationId());
        assertEquals(secondApplication.getId(), secondEvent.getAchievementApplicationId());
    }

    @Test
    void shouldAddAchievementApplication_twoSameRepeatableAchievements() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createAchievementApplication(x -> x
                .achievementCode("RA001")
                .achievementType(AchievementType.REPEATABLE)
                .proposedOutcome("1"));

        var secondApplication = AchievementApplicationBuilder.createAchievementApplication(x -> x
                .achievementCode("RA001")
                .achievementType(AchievementType.REPEATABLE)
                .proposedOutcome("3"));

        // WHEN
        var firstEvent = evaluationProcess.applyForAchievement(firstApplication);
        var secondEvent = evaluationProcess.applyForAchievement(secondApplication);

        // THEN
        assertEquals(firstApplication.getId(), firstEvent.getAchievementApplicationId());
        assertEquals(secondApplication.getId(), secondEvent.getAchievementApplicationId());
    }

}