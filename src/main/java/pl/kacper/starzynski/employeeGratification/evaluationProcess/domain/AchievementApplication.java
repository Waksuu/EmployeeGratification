package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class AchievementApplication {
    @Id
    @Getter
    @EqualsAndHashCode.Include
    //TODO: Think of some natural identity
    private final AchievementApplicationId id;
    private final AchievementCode achievementCode;
    private ProposedOutcome proposedOutcome;
    private List<Answer> answers;

    boolean areAchievementsEqual(AchievementApplication application) {
        return application.achievementCode.equals(achievementCode);
    }

    AchievementApplicationUpdated updateAchievementApplication(ProposedOutcome proposedOutcome,
            List<Answer> answers, AchievementConfigurationService achievementConfigurationService) {
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        this.proposedOutcome = proposedOutcome;
        this.answers = answers;
        return new AchievementApplicationUpdated(this.id.getId(), this.proposedOutcome.getProposedOutcome(), this.answers);
    }

    boolean isAchievementAvailableInEvaluationProcess(ConfigId configId,
            AchievementConfigurationService achievementConfigurationService) {
        return achievementConfigurationService.isAchievementAvailableInEvaluationProcess(achievementCode, configId);
    }

    boolean canBeAppliedForMultipleTimes(AchievementConfigurationService achievementConfigurationService) {
        return achievementConfigurationService.canBeAppliedForMultipleTimes(achievementCode);
    }
}
