package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.util.Pair;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.AnswersUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.events.ProposedOutcomeUpdated;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementApplicationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.*;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class AchievementApplication {
    @Id
    @Getter
    @EqualsAndHashCode.Include
    //TODO: Think of some natural identity
    private final AchievementApplicationId id;
    private final AchievementCode achievementCode;
    private ProposedOutcome proposedOutcome;
    private final QuestionnaireAnswers questionnaireAnswers;

    AchievementApplication(AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            QuestionnaireAnswers questionnaireAnswers) {
        id = new AchievementApplicationId();
        this.achievementCode = achievementCode;
        this.proposedOutcome = proposedOutcome;
        this.questionnaireAnswers = questionnaireAnswers;
    }

    @PersistenceConstructor
    private AchievementApplication(AchievementApplicationId id, AchievementCode achievementCode, ProposedOutcome proposedOutcome,
            QuestionnaireAnswers questionnaireAnswers) {
        this.achievementCode = achievementCode;
        this.proposedOutcome = proposedOutcome;
        this.questionnaireAnswers = questionnaireAnswers;
        this.id = id;
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

    AnswersUpdated updateAnswers(List<Pair<QuestionId, String>> answers) {
        questionnaireAnswers.updateAnswers(answers);
        return new AnswersUpdated(id, questionnaireAnswers.getAnswers());
    }
    

    boolean isAchievementAvailableInEvaluationProcess(AchievementConfigurationId configId,
            AchievementConfigurationService achievementConfigurationService) {
        return achievementConfigurationService.isAchievementAvailableInEvaluationProcess(achievementCode, configId);
    }

    boolean canBeAppliedForMultipleTimes(AchievementConfigurationId configId, AchievementConfigurationService achievementConfigurationService) {
        return achievementConfigurationService.canBeAppliedForMultipleTimes(achievementCode, configId);
    }

    boolean isApplicationFilled() {
        return questionnaireAnswers.areAnswersFilled() && proposedOutcome.isFilled();
    }
}
