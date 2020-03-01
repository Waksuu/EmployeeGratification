package pl.kacper.starzynski.employeeGratification.evaluationProcess.application;

import static java.util.stream.Collectors.toList;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.kacper.starzynski.employeeGratification.evaluationProcess.domain.*;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.readmodel.AchievementApplicationDTO;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementCardService {
    private final AchievementCardRepository achievementCardRepository;
    private final AchievementConfigurationService achievementConfigurationService;

    public void applyForAchievement(UUID achievementCardId, AchievementApplicationDTO dto) {
        var application = AchievementApplicationFactory.create(new AchievementApplicationId(UUID.randomUUID()),
                new AchievementCode(dto.getAchievementCode()),
                dto.getProposedOutcome(),
                Collections.emptyList(),
                achievementConfigurationService);

        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);

        achievementCard.applyForAchievement(application, achievementConfigurationService);
        achievementCardRepository.save(achievementCard);
    }

    public void removeAchievementApplication(UUID achievementCardId, UUID achievementApplicationId) {
        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);
        achievementCard.removeAchievementApplication(new AchievementApplicationId(achievementApplicationId));
        achievementCardRepository.save(achievementCard);
    }

    public void updateAchievementApplication(UUID achievementCardId, UUID achievementApplicationId, AchievementApplicationDTO dto) {
        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);

        achievementCard.updateAchievementApplication(new AchievementApplicationId(achievementApplicationId),
                new ProposedOutcome(dto.getProposedOutcome()),
                getAnswers(dto),
                achievementConfigurationService);

        achievementCardRepository.save(achievementCard);
    }

    private List<Answer> getAnswers(AchievementApplicationDTO dto) {
        return dto.getAnswers().stream().map(x -> new Answer(x.getQuestionId(), x.getAnswer())).collect(toList());
    }

    public void markAsReadToPromoMeeting(UUID achievementCardId) {
        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);

        achievementCard.markAsReadyToPromoMeeting();
    }
}
