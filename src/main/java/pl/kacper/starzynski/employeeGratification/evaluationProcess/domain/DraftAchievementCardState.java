package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

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
    public AchievementApplicationUpdated updateAchievementApplication(Supplier<AchievementApplicationUpdated> supplier) {
        return supplier.get();
    }

}
