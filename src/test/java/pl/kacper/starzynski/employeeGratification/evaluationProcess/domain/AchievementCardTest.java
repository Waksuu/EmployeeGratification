package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AchievementCardTest {
    private static final ProposedOutcome PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT = new ProposedOutcome("2");
    private static final List<Answer> ANSWERS = Arrays.asList(new Answer(UUID.randomUUID(), "Yes"),
            new Answer(UUID.randomUUID(), "No"),
            new Answer(UUID.randomUUID(), "He is very promising employee"));

    private AchievementCard achievementCard;

    @Mock
    private AchievementConfigurationService achievementConfigurationServiceMock;

    @BeforeEach
    void init() {
        lenient().when(achievementConfigurationServiceMock.isAchievementAvailableInEvaluationProcess(any(), any())).thenReturn(true);
        achievementCard = AchievementCardBuilder.createEmptyAchievementCard(UnaryOperator.identity());
    }

    @Test
    void shouldAddAchievementApplication_achievementIsAvailableInCurrentEvaluation() {
        // GIVEN
        var application = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());

        // WHEN
        var event = achievementCard.applyForAchievement(application, achievementConfigurationServiceMock);

        // THEN
        assertEquals(application.getId().getId(), event.getAchievementApplicationId());
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_achievementIsNotAvailableInCurrentEvaluation() {
        // GIVEN
        var application = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());
        when(achievementConfigurationServiceMock.isAchievementAvailableInEvaluationProcess(any(), any())).thenReturn(false);

        // WHEN & Exception
        assertThrows(AchievementException.class, () -> achievementCard.applyForAchievement(application,
                achievementConfigurationServiceMock));
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_sameMaintainableAchievementIsAlreadyAdded() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());
        var secondApplication = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());

        // WHEN & Exception
        achievementCard.applyForAchievement(firstApplication, achievementConfigurationServiceMock);
        assertThrows(AchievementException.class, () -> achievementCard.applyForAchievement(secondApplication,
                achievementConfigurationServiceMock));
    }

    @Test
    void shouldAddAchievementApplication_twoDifferentAchievements() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());
        var secondApplication = AchievementApplicationBuilder.createMaintainableAchievementApplication(x -> x.achievementCode(
                "MA002"));

        // WHEN
        var firstEvent = achievementCard.applyForAchievement(firstApplication, achievementConfigurationServiceMock);
        var secondEvent = achievementCard.applyForAchievement(secondApplication, achievementConfigurationServiceMock);

        // THEN
        assertEquals(firstApplication.getId().getId(), firstEvent.getAchievementApplicationId());
        assertEquals(secondApplication.getId().getId(), secondEvent.getAchievementApplicationId());
    }

    @Test
    void shouldAddAchievementApplication_twoSameRepeatableAchievements() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createRepeatableAchievementApplication(UnaryOperator.identity());
        var secondApplication = AchievementApplicationBuilder.createRepeatableAchievementApplication(x-> x.proposedOutcome("3"));
        when(achievementConfigurationServiceMock.canBeAppliedForMultipleTimes(any())).thenReturn(true);

        // WHEN
        var firstEvent = achievementCard.applyForAchievement(firstApplication, achievementConfigurationServiceMock);
        var secondEvent = achievementCard.applyForAchievement(secondApplication, achievementConfigurationServiceMock);

        // THEN
        assertEquals(firstApplication.getId().getId(), firstEvent.getAchievementApplicationId());
        assertEquals(secondApplication.getId().getId(), secondEvent.getAchievementApplicationId());
    }

    @Test
    void shouldRemoveAchievementApplication() {
        // GIVEN
        var achievementApplicationId = new AchievementApplicationId(UUID.randomUUID());
        achievementCard = AchievementCardBuilder.createAchievementCardWithOneRequestedAchievement(UnaryOperator.identity(),
                x -> x.id(achievementApplicationId));

        // WHEN
        var event = achievementCard.removeAchievementApplication(achievementApplicationId);

        // THEN
        assertEquals(achievementApplicationId.getId(), event.getAchievementApplicationId());
    }

    @Test
    void shouldThrowWhenRemovingNonExistentAchievementApplication() {
        // GIVEN
        var achievementApplicationId = new AchievementApplicationId(UUID.randomUUID());

        // WHEN & Exception
        assertThrows(AchievementException.class, () -> achievementCard.removeAchievementApplication(achievementApplicationId));
    }

    @Test
    void shouldUpdateAchievementApplication() {
        // GIVEN
        var achievementApplicationId = new AchievementApplicationId(UUID.randomUUID());
        achievementCard = AchievementCardBuilder.createAchievementCardWithOneRequestedAchievement(UnaryOperator.identity(),
                x -> x.id(achievementApplicationId));

        // WHEN
        AchievementApplicationUpdated event = achievementCard.updateAchievementApplication(achievementApplicationId,
                PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT,
                ANSWERS,
                achievementConfigurationServiceMock);

        // THEN
        assertEquals(achievementApplicationId.getId(), event.getAchievementApplicationId());
        assertEquals(PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT.getProposedOutcome(), event.getProposedOutcome());
        assertEquals(ANSWERS, event.getAnswers());
    }

    @Test
    void shouldThrowUpdateAchievementApplication_applicationDoesNotExists() {
        // WHEN & Exception
        assertThrows(AchievementException.class,
                () -> achievementCard.updateAchievementApplication(new AchievementApplicationId(UUID.randomUUID()),
                        PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT,
                        ANSWERS,
                        achievementConfigurationServiceMock));
    }

    @Test
    void shouldThrowUpdateAchievementApplication_proposedOutcomeIsInvalidForAchievement() {
        // GIVEN
        var achievementApplicationId = new AchievementApplicationId(UUID.randomUUID());
        achievementCard = AchievementCardBuilder.createAchievementCardWithOneRequestedAchievement(UnaryOperator.identity(),
                x -> x.id(achievementApplicationId));
        when(achievementConfigurationServiceMock.isProposedOutcomeInvalid(any(), any())).thenReturn(true);

        // WHEN & Exception
        assertThrows(AchievementException.class,
                () -> achievementCard.updateAchievementApplication(achievementApplicationId,
                        PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT,
                        ANSWERS,
                        achievementConfigurationServiceMock));
    }
}
