package pl.kacper.starzynski.employeeGratification.achievementCard.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementCardRepository;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.Answer;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.MyBusinessNeedDomainService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.QuestionnaireAnswer;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionnaireId;
import pl.kacper.starzynski.employeeGratification.achievementCard.readmodel.AchievementApplicationDTO;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementCardService {
    private final AchievementCardRepository achievementCardRepository;
    private final MyBusinessNeedDomainService myBusinessNeedDomainService;

    public void applyForAchievement(UUID achievementCardId, AchievementApplicationDTO dto) {
        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);

        achievementCard.applyForAchievement(new AchievementCode(dto.getAchievementCode()),
                new ProposedOutcome(dto.getProposedOutcome()), myBusinessNeedDomainService);
        achievementCardRepository.save(achievementCard);
    }

    public void removeAchievementApplication(UUID achievementCardId, UUID achievementApplicationId) {
        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);
        achievementCard.removeAchievementApplication(new AchievementApplicationId(achievementApplicationId));
        achievementCardRepository.save(achievementCard);
    }

    public void updateAchievementApplication(UUID achievementCardId, UUID achievementApplicationId,
            AchievementApplicationDTO dto) {
        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);

        AchievementApplicationId applicationId = new AchievementApplicationId(achievementApplicationId);
        achievementCard.updateProposedOutcome(applicationId, new ProposedOutcome(dto.getProposedOutcome()),
                myBusinessNeedDomainService);
        achievementCard.updateQuestionnaireAnswers(applicationId, getAnswers(dto));

        achievementCardRepository.save(achievementCard);
    }

    private List<QuestionnaireAnswer> getAnswers(AchievementApplicationDTO dto) {
        return dto.getAnswers().stream().map(
                x -> new QuestionnaireAnswer(
                        new QuestionnaireId(x.getQuestionnaireId()),
                        new QuestionId(x.getQuestionId()),
                        new Answer(x.getAnswer())))
                .collect(toList());
    }

    public void markAsReadToPromoMeeting(UUID achievementCardId) {
        var achievementCard = achievementCardRepository.findById(new AchievementCardId(achievementCardId))
                .orElseThrow(AchievementException::new);

        achievementCard.moveToPromotionMeeting();
    }
}
