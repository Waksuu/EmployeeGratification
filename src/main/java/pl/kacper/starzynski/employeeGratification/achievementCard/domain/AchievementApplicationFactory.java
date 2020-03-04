package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCode;

import java.util.List;

public class AchievementApplicationFactory {
    public static AchievementApplication create(AchievementApplicationId id, AchievementCode achievementCode, String proposedOutcome,
            List<QuestionnaireAnswer> answers, AchievementConfigurationService achievementConfigurationService) {
        var outcome = new ProposedOutcome(proposedOutcome);
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, outcome)) {
            throw new AchievementException();
        }

        return new AchievementApplication(id, achievementCode, outcome, answers);
    }
}