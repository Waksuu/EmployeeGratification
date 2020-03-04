package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.function.Supplier;

public interface AchievementCardState {
    AchievementCardState toDraft();

    AchievementCardState ToPromotionMeeting();

    AchievementCardState toPublished();

    AchievementApplicationApplied applyForAchievement(Supplier<AchievementApplicationApplied> supplier);

    AchievementApplicationRemoved removeAchievementApplication(Supplier<AchievementApplicationRemoved> supplier);

    AchievementApplicationUpdated updateAchievementApplication(Supplier<AchievementApplicationUpdated> supplier);
}
