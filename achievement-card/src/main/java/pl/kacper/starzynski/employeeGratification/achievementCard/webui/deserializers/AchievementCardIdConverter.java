package pl.kacper.starzynski.employeeGratification.achievementCard.webui.deserializers;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;

import java.util.UUID;

@Component
public class AchievementCardIdConverter implements Converter<String, AchievementCardId> {

    @Override
    public AchievementCardId convert(String source) {
        var achievementCardUUID = UUID.fromString(source) ;
        return new AchievementCardId(achievementCardUUID);
    }
}