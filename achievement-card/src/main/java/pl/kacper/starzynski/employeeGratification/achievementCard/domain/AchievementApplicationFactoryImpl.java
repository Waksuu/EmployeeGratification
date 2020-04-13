package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Service
@AllArgsConstructor
class AchievementApplicationFactoryImpl implements AchievementApplicationFactory {
    private final QuestionnaireAnswersFactory questionnaireAnswersFactory;

    @Override
    public AchievementApplication create(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            AchievementConfigurationId configId, AchievementConfigurationService achievementConfigurationService) {
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome, configId)) {
            throw new AchievementException();
        }

        QuestionnaireAnswers questionnaireAnswers = questionnaireAnswersFactory.createQuestionnaireAnswersForAchievement(configId,
                achievementCode);
        return new AchievementApplication(achievementCode, proposedOutcome, questionnaireAnswers);
    }
}
