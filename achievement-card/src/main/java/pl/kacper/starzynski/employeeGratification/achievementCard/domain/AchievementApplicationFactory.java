package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.ports.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.List;

class AchievementApplicationFactory {
    static AchievementApplication create(AchievementApplicationId id, AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            List<QuestionnaireAnswer> answers, AchievementConfigurationId configId, AchievementConfigurationService achievementConfigurationService) {
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome, configId)) {
            throw new AchievementException();
        }

        return new AchievementApplication(id, achievementCode, proposedOutcome, answers);
    }
}
