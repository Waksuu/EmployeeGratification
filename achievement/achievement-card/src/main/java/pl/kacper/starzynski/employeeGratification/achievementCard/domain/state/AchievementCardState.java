package pl.kacper.starzynski.employeeGratification.achievementCard.domain.state;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationApplied;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationRemoved;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AnswersUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;

import java.util.function.Supplier;

public interface AchievementCardState {
    AchievementCardState toDraft();

    AchievementCardState ToPromotionMeeting();

    AchievementCardState toPublished();

    AchievementApplicationApplied applyForAchievement(Supplier<AchievementApplicationApplied> supplier);

    AchievementApplicationRemoved removeAchievementApplication(Supplier<AchievementApplicationRemoved> supplier);

    ProposedOutcomeUpdated updateProposedOutcome(Supplier<ProposedOutcomeUpdated> supplier);

    AnswersUpdated updateQuestionnaireAnswers(Supplier<AnswersUpdated> supplier);
}
