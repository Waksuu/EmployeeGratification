package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import org.springframework.stereotype.Service;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

@Service
class AchievementApplicationFactoryImpl implements AchievementApplicationFactory {

    @Override
    public AchievementApplication create(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            QuestionnaireAnswers questionnaireAnswers, AchievementConfigurationId configId,
            AchievementConfigurationService achievementConfigurationService) {
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome, configId)) {
            throw new AchievementException();
        }

        return new AchievementApplication(achievementCode, proposedOutcome, questionnaireAnswers);
    }
}
