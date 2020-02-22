package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

public class AchievementApplicationFactory {
    public static AchievementApplication create(UUID id, Achievement achievement, String proposedOutcome, AchievementConfigurationService achievementConfigurationService) {

        //TODO: Fix me I am ugly
        switch (achievement.getAchievementType()) {
            case MAINTAINABLE:
                if (!achievementConfigurationService.isProposedOutcomeValid(achievement.getAchievementCode(), proposedOutcome)) {
                    throw new AchievementException();
                }
                return new MaintainableAchievementApplication(id, achievement, proposedOutcome);
            case REPEATABLE:
                Integer.parseInt(proposedOutcome);
                return new RepeatableAchievementApplication(id, achievement, proposedOutcome);
            case REPEATABLE_PARTIAL:
                float numericOutcome = Float.parseFloat(proposedOutcome);
                if (numericOutcome > 1 || numericOutcome < 0) {
                    throw new AchievementException();
                }
                return new PartialRepeatableAchievementApplication(id, achievement, proposedOutcome);
            default:
                throw new IllegalStateException("Unexpected value: " + achievement.getAchievementType());
        }
    }
}
