package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.ConfigId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.UnaryOperator;

public class AchievementCardBuilder {
    static AchievementCard createEmptyAchievementCard(
            UnaryOperator<AchievementCardFields.AchievementCardFieldsBuilder> overridingExpression) {
        var defaultAchievementCard = AchievementCardFields.builder()
                .id(new AchievementCardId(UUID.randomUUID()))
                .requestedAchievements(new ArrayList<>())
                .configId(new ConfigId(UUID.randomUUID()));

        var overriddenCard = overridingExpression.apply(defaultAchievementCard).build();
        return new AchievementCard(overriddenCard.getId(), overriddenCard.getRequestedAchievements(),
                overriddenCard.getConfigId());
    }

    static AchievementCard createAchievementCardWithOneRequestedAchievement(
            UnaryOperator<AchievementCardFields.AchievementCardFieldsBuilder> overridingCardExpression,
            UnaryOperator<AchievementApplicationFields.AchievementApplicationFieldsBuilder> overridingApplicationExpression) {
        var maintainableApplication = AchievementApplicationBuilder.createMaintainableAchievementApplication(overridingApplicationExpression);
        var defaultAchievementCard = AchievementCardFields.builder()
                .id(new AchievementCardId(UUID.randomUUID()))
                .requestedAchievements(new ArrayList<>(Arrays.asList(maintainableApplication)))
                .configId(new ConfigId(UUID.randomUUID()));

        var overriddenCard = overridingCardExpression.apply(defaultAchievementCard).build();
        return new AchievementCard(overriddenCard.getId(), overriddenCard.getRequestedAchievements(),
                overriddenCard.getConfigId());
    }
}
