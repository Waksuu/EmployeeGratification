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
        evaluationProcess = EvaluationProcessFactory.create(AchievementCardFactory.create(), AVAILABLE_ACHIEVEMENTS);
    }

    @Test
    void shouldAddAchievementApplication_achievementIsAvailableInCurrentEvaluation() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);
        when(achievementConfigurationService.getAchievementType(any())).thenReturn(AchievementType.MAINTAINABLE);

        AchievementCode achievementCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome proposedOutcome = ProposedOutcomeFactory.create();

        // WHEN
        AchievementApplied event = evaluationProcess.applyForAchievement(achievementCode, proposedOutcome, achievementConfigurationService);

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
                evaluationProcess.applyForAchievement(achievementCode, proposedOutcome, achievementConfigurationService)
        );
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_sameMaintainableAchievementIsAlreadyAdded() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);
        when(achievementConfigurationService.getAchievementType(any())).thenReturn(AchievementType.MAINTAINABLE);

        AchievementCode firstCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome firstProposedOutcome = ProposedOutcomeFactory.create();

        AchievementCode secondCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome secondProposedOutcome = ProposedOutcomeFactory.create();

        // WHEN & Exception
        evaluationProcess.applyForAchievement(firstCode, firstProposedOutcome, achievementConfigurationService);
        assertThrows(AchievementException.class, () ->
                evaluationProcess.applyForAchievement(secondCode, secondProposedOutcome, achievementConfigurationService)
        );
    }

    @Test
    void shouldAddAchievementApplication_twoDifferentAchievements() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);
        when(achievementConfigurationService.getAchievementType(any())).thenReturn(AchievementType.MAINTAINABLE);

        AchievementCode firstCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome firstProposedOutcome = ProposedOutcomeFactory.create();

        AchievementCode secondCode = AchievementCodeFactory.create("MA002");
        ProposedOutcome secondProposedOutcome = ProposedOutcomeFactory.create();

        // WHEN
        AchievementApplied firstEvent = evaluationProcess.applyForAchievement(firstCode, firstProposedOutcome, achievementConfigurationService);
        AchievementApplied secondEvent = evaluationProcess.applyForAchievement(secondCode, secondProposedOutcome, achievementConfigurationService);

        // THEN
        assertEquals(firstCode, firstEvent.getAchievementCode());
        assertEquals(secondCode, secondEvent.getAchievementCode());
    }

    @Test
    void shouldAddAchievementApplication_twoSameRepeatableAchievements() {
        // GIVEN
        when(achievementConfigurationService.isProposedOutcomeValid(any(), any())).thenReturn(true);
        when(achievementConfigurationService.getAchievementType(any())).thenReturn(AchievementType.REPEATABLE);

        AchievementCode firstCode = AchievementCodeFactory.create("RA001");
        ProposedOutcome firstProposedOutcome = ProposedOutcomeFactory.create();

        AchievementCode secondCode = AchievementCodeFactory.create("RA001");
        ProposedOutcome secondProposedOutcome = ProposedOutcomeFactory.create();

        // WHEN
        AchievementApplied firstEvent = evaluationProcess.applyForAchievement(firstCode, firstProposedOutcome, achievementConfigurationService);
        AchievementApplied secondEvent = evaluationProcess.applyForAchievement(secondCode, secondProposedOutcome, achievementConfigurationService);

        // THEN
        assertEquals(firstCode, firstEvent.getAchievementCode());
        assertEquals(secondCode, secondEvent.getAchievementCode());
    }

}