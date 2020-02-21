package pl.kacper.starzynski.employeeGratification.achievemenetCard.domain;

public interface AchievementProposedOutcomeService {
    boolean isProposedOutcomeValid(AchievementCode achievementCode, ProposedOutcome proposedOutcome);
}
