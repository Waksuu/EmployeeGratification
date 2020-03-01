package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.List;
import java.util.UUID;

public class AchievementApplicationFactory {
    public static AchievementApplication create(AchievementApplicationId id, AchievementCode achievementCode, String proposedOutcome,
            List<Answer> answers, AchievementConfigurationService achievementConfigurationService) {
        var outcome = new ProposedOutcome(proposedOutcome);
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, outcome)) {
            throw new AchievementException();
        }

        return new AchievementApplication(id, achievementCode, outcome, answers);
    }
}
