package pl.kacper.starzynski.employeeGratification.achievemenetCard.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AchievementCardTest {

    private static final List<AchievementCode> AVAILABLE_ACHIEVEMENTS = Arrays.asList(
            AchievementCodeFactory.create("MA001"),
            AchievementCodeFactory.create("MA002"),
            AchievementCodeFactory.create("RA001"),
            AchievementCodeFactory.create("RA002")
    );

    @Test
    void shouldAddAchievementApplication_achievementIsAvailableInCurrentEvaluation() {
        // GIVEN
        AchievementCard card = AchievementCardFactory.create(AVAILABLE_ACHIEVEMENTS);
        AchievementCode achievementCode = AchievementCodeFactory.create("MA001");
        ProposedOutcome proposedOutcome = new ProposedOutcome();
        AchievementProposedOutcomeService availabilityServiceMock = mock(AchievementProposedOutcomeService.class);
        when(availabilityServiceMock.isProposedOutcomeValid(any(), any())).thenReturn(true);

        // WHEN
        AchievementApplied event = card.applyForAchievement(achievementCode, proposedOutcome, availabilityServiceMock);

        // THEN
        assertEquals(achievementCode, event.getAchievementCode());
    }

    @Test
    void shouldThrowWhenAddingAchievementApplication_achievementIsNotAvailableInCurrentEvaluation() {
        // GIVEN
        AchievementCard card = AchievementCardFactory.create(AVAILABLE_ACHIEVEMENTS);
        AchievementCode achievementCode = AchievementCodeFactory.create("MA003");
        ProposedOutcome proposedOutcome = new ProposedOutcome();

        // WHEN & Exception
        assertThrows(AchievementException.class, () ->
                card.applyForAchievement(achievementCode, proposedOutcome, mock(AchievementProposedOutcomeService.class)));

    }
}