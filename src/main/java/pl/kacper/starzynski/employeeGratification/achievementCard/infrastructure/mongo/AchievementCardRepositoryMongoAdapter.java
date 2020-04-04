package pl.kacper.starzynski.employeeGratification.achievementCard.infrastructure.mongo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementCard;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementCardRepository;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class AchievementCardRepositoryMongoAdapter implements AchievementCardRepository {
    private final AchievementCardMongoRepository achievementCardRepository;

    @Override
    public Optional<AchievementCard> findById(AchievementCardId achievementCardId) {
        return achievementCardRepository.findById(achievementCardId);
    }

    @Override
    public void save(AchievementCard achievementCard) {
        achievementCardRepository.save(achievementCard);
    }
}
