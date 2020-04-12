package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.RequiredArgsConstructor;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts.AchievementConfigurationService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class QuestionnaireAnswersFactory {
    private final AchievementConfigurationService achievementConfigurationService;

    public QuestionnaireAnswers createQuestionnaireAnswersForAchievement(AchievementCode achievementCode) {
        List<Answer> answers = getQuestionsForCode(achievementCode);
        return new QuestionnaireAnswers(answers);
    }

    private List<Answer> getQuestionsForCode(AchievementCode achievementCode) {
        return achievementConfigurationService.getQuestionsForCode(achievementCode).stream()
                .map(Answer::of)
                .collect(toList());
    }
}
