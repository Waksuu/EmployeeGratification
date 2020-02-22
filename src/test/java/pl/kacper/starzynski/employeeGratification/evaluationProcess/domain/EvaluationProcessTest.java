package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EvaluationProcessTest {
    private EvaluationProcess evaluationProcess;

    @Mock
    private AchievementConfigurationService achievementConfigurationService;

    private static final List<AchievementCode> AVAILABLE_ACHIEVEMENTS = Arrays.asList(
            AchievementCodeFactory.create("MA001"),
            AchievementCodeFactory.create("MA002"),
            AchievementCodeFactory.create("RA001"),
            AchievementCodeFactory.create("RA002")
    );

    @BeforeEach
    void init() {
        evaluationProcess = EvaluationProcessFactory.create(AchievementCardFactory.create(achievementConfigurationService), AVAILABLE_ACHIEVEMENTS, achievementConfigurationService);
    }

    @Test
    void shouldAddAchievementApplication_achievementIsAvailableInCurrentEvaluation() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);

        AchievementCode achievementCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome proposedOutcome = ProposedOutcomeFactory.create();

        // WHEN
        AchievementApplicationApplied event = evaluationProcess.applyForAchievement(achievementCode, proposedOutcome);

        // THEN
        assertEquals(achievementCode, event.getAchievementCode());
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_achievementIsNotAvailableInCurrentEvaluation() {
        // GIVEN
        AchievementCode achievementCode = AchievementCodeFactory.create("MA003");
        ProposedOutcome proposedOutcome = ProposedOutcomeFactory.create();

        // WHEN & Exception
        assertThrows(AchievementException.class, () ->
                evaluationProcess.applyForAchievement(achievementCode, proposedOutcome)
        );
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_sameMaintainableAchievementIsAlreadyAdded() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);
        when(achievementConfigurationService.applicationCannotBeDuplicate(any())).thenReturn(true);

        AchievementCode firstCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome firstProposedOutcome = ProposedOutcomeFactory.create();

        AchievementCode secondCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome secondProposedOutcome = ProposedOutcomeFactory.create();

        // WHEN & Exception
        evaluationProcess.applyForAchievement(firstCode, firstProposedOutcome);
        assertThrows(AchievementException.class, () ->
                evaluationProcess.applyForAchievement(secondCode, secondProposedOutcome)
        );
    }

    @Test
    void shouldAddAchievementApplication_twoDifferentAchievements() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);

        AchievementCode firstCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome firstProposedOutcome = ProposedOutcomeFactory.create();

        AchievementCode secondCode = AchievementCodeFactory.create("MA002");
        ProposedOutcome secondProposedOutcome = ProposedOutcomeFactory.create();

        // WHEN
        AchievementApplicationApplied firstEvent = evaluationProcess.applyForAchievement(firstCode, firstProposedOutcome);
        AchievementApplicationApplied secondEvent = evaluationProcess.applyForAchievement(secondCode, secondProposedOutcome);

        // THEN
        assertEquals(firstCode, firstEvent.getAchievementCode());
        assertEquals(secondCode, secondEvent.getAchievementCode());
    }

    @Test
    void shouldAddAchievementApplication_twoSameRepeatableAchievements() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);
        when(achievementConfigurationService.applicationCannotBeDuplicate(any())).thenReturn(false);

        AchievementCode firstCode = AchievementCodeFactory.create("RA001");
        ProposedOutcome firstProposedOutcome = ProposedOutcomeFactory.create();

        AchievementCode secondCode = AchievementCodeFactory.create("RA001");
        ProposedOutcome secondProposedOutcome = ProposedOutcomeFactory.create();

        // WHEN
        AchievementApplicationApplied firstEvent = evaluationProcess.applyForAchievement(firstCode, firstProposedOutcome);
        AchievementApplicationApplied secondEvent = evaluationProcess.applyForAchievement(secondCode, secondProposedOutcome);

        // THEN
        assertEquals(firstCode, firstEvent.getAchievementCode());
        assertEquals(secondCode, secondEvent.getAchievementCode());
    }

}