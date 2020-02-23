package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
//TODO: Is it good aggregate root?
public class EvaluationProcess {
    @Id
    @EqualsAndHashCode.Include
    //TODO: Think of some natural identity
    private final Long id;
    private final AchievementCard achievementCard;
    private final List<AchievementCode> availableAchievements;

    public AchievementApplicationApplied applyForAchievement(AchievementApplication achievementApplication) {
        if (achievementIsNotAvailableInEvaluationProcess(achievementApplication)) {
            throw new AchievementException();
        }

        return achievementCard.addApplication(achievementApplication);
    }

    private boolean achievementIsNotAvailableInEvaluationProcess(AchievementApplication achievementApplication) {
        return availableAchievements.stream().noneMatch(achievementApplication::areAchievementsEqual);
    }
}
