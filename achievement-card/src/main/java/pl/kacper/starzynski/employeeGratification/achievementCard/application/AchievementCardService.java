package pl.kacper.starzynski.employeeGratification.achievementCard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.*;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;
import pl.kacper.starzynski.employeeGratification.achievementCard.readmodel.AchievementApplicationDTO;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementCardService {
    private final AchievementCardRepository achievementCardRepository;
    private final AchievementConfigurationService achievementConfigurationService;
    private final AchievementApplicationFactory achievementApplicationFactory;
    private final QuestionnaireAnswersFactory questionnaireAnswersFactory;

    public void applyForAchievement(AchievementCardId achievementCardId, AchievementApplicationDTO dto) {
        var achievementCard = getAchievementCard(achievementCardId);
        // TODO: ???
        QuestionnaireAnswers questionnaireAnswers = questionnaireAnswersFactory.createQuestionnaireAnswersForAchievement(dto.getAchievementCode());
        achievementCard.applyForAchievement(dto.getAchievementCode(), dto.getProposedOutcome(), questionnaireAnswers,
                achievementConfigurationService, achievementApplicationFactory);
    }

    public void removeAchievementApplication(AchievementCardId achievementCardId, AchievementApplicationId achievementApplicationId) {
        var achievementCard = getAchievementCard(achievementCardId);
        achievementCard.removeAchievementApplication(achievementApplicationId);
    }

    public void updateAchievementApplication(AchievementCardId achievementCardId, AchievementApplicationId achievementApplicationId, AchievementApplicationDTO dto) {
        var achievementCard = getAchievementCard(achievementCardId);
        achievementCard.updateProposedOutcome(achievementApplicationId, dto.getProposedOutcome(), achievementConfigurationService);
        achievementCard.updateQuestionnaireAnswers(achievementApplicationId, dto.getAnswers());
    }

    public void markAsReadToPromoMeeting(AchievementCardId achievementCardId) {
        var achievementCard = getAchievementCard(achievementCardId);
        achievementCard.moveToPromotionMeeting();
    }

    private AchievementCard getAchievementCard(AchievementCardId achievementCardId) {
        return achievementCardRepository.findById(achievementCardId).orElseThrow(AchievementException::new);
    }
}
