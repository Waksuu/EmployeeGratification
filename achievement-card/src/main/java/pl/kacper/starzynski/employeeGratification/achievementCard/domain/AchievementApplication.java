package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.QuestionnaireAnswersUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class AchievementApplication {
    @Id
    @Getter
    @EqualsAndHashCode.Include
    //TODO: Think of some natural identity
    private final AchievementApplicationId id = new AchievementApplicationId();
    private final AchievementCode achievementCode;
    private ProposedOutcome proposedOutcome;
    private List<QuestionnaireAnswer> answers = new ArrayList<>();

    AchievementApplication(AchievementCode achievementCode, ProposedOutcome proposedOutcome) {
        this.achievementCode = achievementCode;
        this.proposedOutcome = proposedOutcome;
    }

    boolean areAchievementsEqual(AchievementApplication application) {
        return application.achievementCode.equals(achievementCode);
    }

    ProposedOutcomeUpdated updateProposedOutcome(ProposedOutcome proposedOutcome, AchievementConfigurationId configId,
            AchievementConfigurationService achievementConfigurationService) {
        if (achievementConfigurationService.isProposedOutcomeInvalid(achievementCode, proposedOutcome, configId)) {
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

    boolean canBeAppliedForMultipleTimes(AchievementConfigurationId configId, AchievementConfigurationService achievementConfigurationService) {
        return achievementConfigurationService.canBeAppliedForMultipleTimes(achievementCode, configId);
    }

    boolean isApplicationFilled() {
        return answers.stream().allMatch(QuestionnaireAnswer::isAnswerFilled) && proposedOutcome.isFilled();
    }
}
