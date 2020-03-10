package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationApplied;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionnaireId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AchievementCardTest {
    private static final ProposedOutcome PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT = new ProposedOutcome("2");
    private static final List<QuestionnaireAnswer> ANSWERS = Arrays.asList(
            new QuestionnaireAnswer(
                    new QuestionnaireId(UUID.randomUUID()),
                    new QuestionId(UUID.randomUUID()),
                    new Answer("Yes")),
            new QuestionnaireAnswer(
                    new QuestionnaireId(UUID.randomUUID()),
                    new QuestionId(UUID.randomUUID()),
                    new Answer("No")),
            new QuestionnaireAnswer(
                    new QuestionnaireId(UUID.randomUUID()),
                    new QuestionId(UUID.randomUUID()),
                    new Answer("He is very promising employee"))
    );

    private AchievementCard achievementCard;

    @Mock
    private AchievementConfigurationService achievementConfigurationServiceMock;

    @BeforeEach
    void init() {
        lenient().when(achievementConfigurationServiceMock.isAchievementAvailableInEvaluationProcess(any(), any())).thenReturn(
                true);
        achievementCard = AchievementCardBuilder.createEmptyAchievementCard(UnaryOperator.identity());
    }

    //TODO: Maybe split into separate tests?
    @Test
    void shouldAddAchievementApplication_achievementIsAvailableInCurrentEvaluation() {
        // GIVEN
        var application = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());

        // WHEN
        var event = achievementCard.applyForAchievement(application, achievementConfigurationServiceMock);

        // THEN
        assertApplicationIsAdded(application, event);
    }

    @Test
    void shouldThrowApplyAchievementApplication_achievementIsNotAvailableInCurrentEvaluation() {
        // GIVEN
        var application = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());
        when(achievementConfigurationServiceMock.isAchievementAvailableInEvaluationProcess(any(), any())).thenReturn(false);

        // WHEN & Exception
        assertApplyingForApplicationFailed(application);
    }

    @Test
    void shouldThrowApplyAchievementApplication_sameMaintainableAchievementIsAlreadyAdded() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());
        var secondApplication = AchievementApplicationBuilder.createMaintainableAchievementApplication(UnaryOperator.identity());

        // WHEN & Exception
        achievementCard.applyForAchievement(firstApplication, achievementConfigurationServiceMock);
        assertApplyingForApplicationFailed(secondApplication);
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
        assertApplicationIsAdded(firstApplication, firstEvent);
        assertApplicationIsAdded(secondApplication, secondEvent);
    }

    @Test
    void shouldAddAchievementApplication_twoSameRepeatableAchievements() {
        // GIVEN
        var firstApplication = AchievementApplicationBuilder.createRepeatableAchievementApplication(UnaryOperator.identity());
        var secondApplication = AchievementApplicationBuilder.createRepeatableAchievementApplication(x -> x.proposedOutcome("3"));
        when(achievementConfigurationServiceMock.canBeAppliedForMultipleTimes(any())).thenReturn(true);

        // WHEN
        var firstEvent = achievementCard.applyForAchievement(firstApplication, achievementConfigurationServiceMock);
        var secondEvent = achievementCard.applyForAchievement(secondApplication, achievementConfigurationServiceMock);

        // THEN
        assertApplicationIsAdded(firstApplication, firstEvent);
        assertApplicationIsAdded(secondApplication, secondEvent);
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
        assertEquals(achievementApplicationId, event.getAchievementApplicationId());
    }

    @Test
    void shouldThrowRemoveNonExistentAchievementApplication() {
        // GIVEN
        var achievementApplicationId = new AchievementApplicationId(UUID.randomUUID());

        // WHEN & Exception
        assertThrows(AchievementException.class, () -> achievementCard.removeAchievementApplication(achievementApplicationId));
    }

    @Test
    void shouldUpdateProposedOutcome() {
        // GIVEN
        var achievementApplicationId = new AchievementApplicationId(UUID.randomUUID());
        achievementCard = AchievementCardBuilder.createAchievementCardWithOneRequestedAchievement(UnaryOperator.identity(),
                x -> x.id(achievementApplicationId));

        // WHEN
        var event = achievementCard.updateProposedOutcome(achievementApplicationId,
                PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT,
                achievementConfigurationServiceMock);

        // THEN
        assertEquals(achievementApplicationId, event.getAchievementApplicationId());
        assertEquals(PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT, event.getProposedOutcome());
    }

    @Test
    void shouldThrowUpdateAchievementApplication_applicationDoesNotExists() {
        // WHEN & Exception
        assertThrows(AchievementException.class,
                () -> achievementCard.updateProposedOutcome(new AchievementApplicationId(UUID.randomUUID()),
                        PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT,
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
                () -> achievementCard.updateProposedOutcome(achievementApplicationId,
                        PROPOSED_OUTCOME_FOR_REPEATABLE_ACHIEVEMENT,
                        achievementConfigurationServiceMock));
    }

    @Test
    void shouldUpdateQuestionnaireAnswers() {
        // GIVEN
        var achievementApplicationId = new AchievementApplicationId(UUID.randomUUID());
        achievementCard = AchievementCardBuilder.createAchievementCardWithOneRequestedAchievement(UnaryOperator.identity(),
                x -> x.id(achievementApplicationId));

        // WHEN
        var event = achievementCard.updateQuestionnaireAnswers(achievementApplicationId, ANSWERS);

        // THEN
        assertEquals(achievementApplicationId, event.getAchievementApplicationId());
        assertEquals(ANSWERS, event.getAnswer());
    }

    @Test
    void shouldThrowUpdateQuestionnaireAnswers_applicationDoesNotExists() {
        // WHEN & Exception
        assertThrows(AchievementException.class,
                () -> achievementCard.updateQuestionnaireAnswers(new AchievementApplicationId(UUID.randomUUID()), ANSWERS));
    }

    private void assertApplicationIsAdded(AchievementApplication application, AchievementApplicationApplied event) {
        assertEquals(application.getId(), event.getAchievementApplicationId());
    }

    private void assertApplyingForApplicationFailed(AchievementApplication application) {
        assertThrows(AchievementException.class, () -> achievementCard.applyForAchievement(application,
                achievementConfigurationServiceMock));
    }
}
