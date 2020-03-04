package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.ConfigId;

@Getter
@Builder
public class AchievementCardFields {
    private AchievementCardId id;
    private List<AchievementApplication> requestedAchievements;
    private ConfigId configId;
}
