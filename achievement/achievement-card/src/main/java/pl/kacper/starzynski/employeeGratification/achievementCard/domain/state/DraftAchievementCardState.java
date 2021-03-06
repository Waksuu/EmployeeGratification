package pl.kacper.starzynski.employeeGratification.achievementCard.domain.state;

import org.springframework.data.annotation.TypeAlias;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationApplied;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AchievementApplicationRemoved;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AnswersUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;

import java.util.function.Supplier;

@TypeAlias("employeeGratification.achievementCard.DraftAchievementCardState")
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
    public AnswersUpdated updateQuestionnaireAnswers(Supplier<AnswersUpdated> supplier) {
        return supplier.get();
    }

}
