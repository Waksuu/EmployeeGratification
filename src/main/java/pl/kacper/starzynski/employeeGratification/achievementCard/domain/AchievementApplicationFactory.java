package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.List;

public class AchievementApplicationFactory {
    public static AchievementApplication create(AchievementApplicationId id, AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            List<QuestionnaireAnswer> answers, AchievementConfigurationService achievementConfigurationService) {
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        return new AchievementApplication(id, achievementCode, proposedOutcome, answers);
    }
}
