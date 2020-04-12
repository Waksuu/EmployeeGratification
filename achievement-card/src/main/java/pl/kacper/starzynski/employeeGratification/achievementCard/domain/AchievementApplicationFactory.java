package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

public interface AchievementApplicationFactory {
    AchievementApplication create(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            QuestionnaireAnswers questionnaireAnswers, AchievementConfigurationId configId,
            AchievementConfigurationService achievementConfigurationService);
}
