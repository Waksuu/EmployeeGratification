package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kacper.starzynski.employeeGratification.achievementConfigurationApi.AchievementService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;

import java.util.List;

import static java.util.stream.Collectors.toList;

// TODO: Domena ma dostęp do API???

@RequiredArgsConstructor
@Component
public class QuestionnaireAnswersFactory {
    private final AchievementService achievementService;

    public QuestionnaireAnswers createQuestionnaireAnswersForAchievement(AchievementConfigurationId configId,
            AchievementCode achievementCode) {
        List<Answer> answers = getQuestionsForCode(configId, achievementCode);
        return new QuestionnaireAnswers(answers);
    }

    private List<Answer> getQuestionsForCode(AchievementConfigurationId configId, AchievementCode achievementCode) {
        return achievementService.getQuestionsForCode(configId, achievementCode).stream()
                .map(Answer::of)
                .collect(toList());
    }
}
