package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.QuestionnaireAnswersUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.List;

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
    private List<QuestionnaireAnswer> answers;

    boolean areAchievementsEqual(AchievementApplication application) {
        return application.achievementCode.equals(achievementCode);
    }

    ProposedOutcomeUpdated updateProposedOutcome(ProposedOutcome proposedOutcome,
            AchievementConfigurationService achievementConfigurationService) {
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome)) {
            throw new AchievementException();
        }

        this.proposedOutcome = proposedOutcome;
        return new ProposedOutcomeUpdated(id, this.proposedOutcome);
    }

    QuestionnaireAnswersUpdated updateAnswers(List<QuestionnaireAnswer> answers) {
        this.answers = answers;
        return new QuestionnaireAnswersUpdated(id, this.answers);
    }
    

    boolean isAchievementAvailableInEvaluationProcess(AchievementConfigurationId configId,
            AchievementConfigurationService achievementConfigurationService) {
        return achievementConfigurationService.isAchievementAvailableInEvaluationProcess(achievementCode, configId);
    }

    boolean canBeAppliedForMultipleTimes(AchievementConfigurationService achievementConfigurationService) {
        return achievementConfigurationService.canBeAppliedForMultipleTimes(achievementCode);
    }

    boolean areAnswersFilled() {
        return answers.stream().allMatch(QuestionnaireAnswer::isAnswerFilled);
    }
}
