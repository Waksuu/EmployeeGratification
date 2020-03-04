package pl.kacper.starzynski.employeeGratification.achievementCard.domain.state;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationApplied;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationRemoved;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.QuestionnaireAnswersUpdated;

import java.util.function.Supplier;

public class DraftAchievementCardState implements AchievementCardState {
    @Override
    public AchievementCardState toDraft() {
        return this;
    }

    @Override
    public AchievementCardState ToPromotionMeeting() {
        return new PromotionMeetingAchievementCardState();
    }

    @Override
    public AchievementCardState toPublished() {
        return this;
    }

    @Override
    public AchievementApplicationApplied applyForAchievement(Supplier<AchievementApplicationApplied> supplier) {
        return supplier.get();
    }

    @Override
    public AchievementApplicationRemoved removeAchievementApplication(Supplier<AchievementApplicationRemoved> supplier) {
        return supplier.get();
    }

    @Override
    public ProposedOutcomeUpdated updateProposedOutcome(Supplier<ProposedOutcomeUpdated> supplier) {
        return supplier.get();
    }

    @Override
    public QuestionnaireAnswersUpdated updateQuestionnaireAnswers(Supplier<QuestionnaireAnswersUpdated> supplier) {
        return supplier.get();
    }

}
