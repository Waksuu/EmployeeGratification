package pl.kacper.starzynski.employeeGratification.achievementConfigurationApi;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

public interface AchievementService {
    List<QuestionId> getQuestionsForCode(AchievementCode achievementCode);
}
