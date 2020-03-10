package pl.kacper.starzynski.employeeGratification.achievementCard.domain.state;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementException;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationApplied;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationRemoved;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.QuestionnaireAnswersUpdated;

import java.util.function.Supplier;

class PromotionMeetingAchievementCardState implements AchievementCardState {
    @Override
    public AchievementCardState toDraft() {
        return new DraftAchievementCardState();
    }

    @Override
    public AchievementCardState ToPromotionMeeting() {
        return this;
    }

    @Override
    public AchievementCardState toPublished() {
        return new PublishedAchievementCardState();
    }

    @Override
    public AchievementApplicationApplied applyForAchievement(Supplier<AchievementApplicationApplied> supplier) {
        throw new AchievementException();
    }

    @Override
    public AchievementApplicationRemoved removeAchievementApplication(Supplier<AchievementApplicationRemoved> supplier) {
        throw new AchievementException();
    }

    @Override
    public ProposedOutcomeUpdated updateProposedOutcome(Supplier<ProposedOutcomeUpdated> supplier) {
        throw new AchievementException();
    }

    @Override
    public QuestionnaireAnswersUpdated updateQuestionnaireAnswers(Supplier<QuestionnaireAnswersUpdated> supplier) {
        throw new AchievementException();
    }
}
