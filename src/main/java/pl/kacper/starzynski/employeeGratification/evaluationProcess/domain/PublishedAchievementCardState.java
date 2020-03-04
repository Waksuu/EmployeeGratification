package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;
import java.util.function.Supplier;

public class PublishedAchievementCardState implements AchievementCardState {
    @Override
    public AchievementCardState toDraft() {
        return this;
    }

    @Override
    public AchievementCardState ToPromotionMeeting() {
        return this;
    }

    @Override
    public AchievementCardState toPublished() {
        return this;
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
    public AchievementApplicationUpdated updateAchievementApplication(Supplier<AchievementApplicationUpdated> supplier) {
        throw new AchievementException();
    }
}
