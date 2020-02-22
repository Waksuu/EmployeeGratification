package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;


//TODO: Make it cachable
public interface AchievementConfigurationService {
    boolean isProposedOutcomeValid(AchievementCode achievementCode, ProposedOutcome proposedOutcome);

    AchievementType getAchievementType(AchievementCode achievementCode);
}
