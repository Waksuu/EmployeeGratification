package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import java.util.UUID;

public class AchievementApplicationFactory {
    public static AchievementApplication create(UUID id, Achievement achievement, String proposedOutcome, AchievementConfigurationService achievementConfigurationService) {

        //TODO: Fix me I am ugly
        switch (achievement.getAchievementType()) {
            case MAINTAINABLE:
                validateProposedOutcomeAsString(achievement, proposedOutcome, achievementConfigurationService);
                return new MaintainableAchievementApplication(id, achievement, proposedOutcome);
            case REPEATABLE:
                validateProposedOutcomeAsInt(proposedOutcome);
                return new RepeatableAchievementApplication(id, achievement, proposedOutcome);
            case REPEATABLE_PARTIAL:
                validateProposedOutcomeAsFloat(proposedOutcome);
                return new PartialRepeatableAchievementApplication(id, achievement, proposedOutcome);
            default:
                throw new IllegalStateException("Unexpected value: " + achievement.getAchievementType());
        }
    }

    private static void validateProposedOutcomeAsString(Achievement achievement, String proposedOutcome, AchievementConfigurationService achievementConfigurationService) {
        if (!achievementConfigurationService.isProposedOutcomeValid(achievement.getAchievementCode(), proposedOutcome)) {
            throw new AchievementException();
        }
    }

    private static void validateProposedOutcomeAsInt(String proposedOutcome) {
        Integer.parseInt(proposedOutcome);
    }

    private static void validateProposedOutcomeAsFloat(String proposedOutcome) {
        float numericOutcome = Float.parseFloat(proposedOutcome);
        if (numericOutcome > 1 || numericOutcome < 0) {
            throw new AchievementException();
        }
    }
}
