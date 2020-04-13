package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import pl.kacper.starzynski.employeeGratification.sharedKernel.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
public class AchievementConfiguration {
    @Id
    private final AchievementConfigurationId id;
    private final List<Achievement> availableAchievements;

    public boolean isAchievementAvailableInEvaluationProcess(AchievementCode achievementCode) {
        return availableAchievements.stream().anyMatch(achievement -> achievement.isEqual(achievementCode));
    }

    public boolean isProposedOutcomeInvalid(AchievementCode achievementCode, ProposedOutcome proposedOutcome) {
        var achievement = getAchievement(achievementCode);
        return achievement.isProposedOutcomeValid(proposedOutcome);
    }

    public boolean canBeAppliedForMultipleTimes(AchievementCode achievementCode) {
        var achievement = getAchievement(achievementCode);
        return achievement.canBeAppliedForMultipleTimes();
    }

    public List<QuestionId> getQuestionIds(AchievementCode achievementCode) {
        var achievement = getAchievement(achievementCode);
        return achievement.getQuestionIds();
    }

    private Achievement getAchievement(AchievementCode achievementCode) {
        var achievements = availableAchievements.stream()
                .filter(achievement -> achievement.isEqual(achievementCode))
                .collect(toList());

        if (achievements.size() > 1) {
            throw new AchievementException();
        }

        if (achievements.isEmpty()) {
            throw new AchievementException();
        }

        return achievements.get(0);
    }
}
