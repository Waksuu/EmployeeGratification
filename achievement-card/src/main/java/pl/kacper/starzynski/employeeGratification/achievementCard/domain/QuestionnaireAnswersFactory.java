package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.RequiredArgsConstructor;
import pl.kacper.starzynski.employeeGratification.achievementConfigurationApi.AchievementService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class QuestionnaireAnswersFactory {
    private final AchievementService achievementService;

    public QuestionnaireAnswers createQuestionnaireAnswersForAchievement(AchievementCode achievementCode) {
        List<Answer> answers = getQuestionsForCode(achievementCode);
        return new QuestionnaireAnswers(answers);
    }

    private List<Answer> getQuestionsForCode(AchievementCode achievementCode) {
        return achievementService.getQuestionsForCode(achievementCode).stream()
                .map(Answer::of)
                .collect(toList());
    }
}
