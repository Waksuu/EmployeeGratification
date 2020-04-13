package pl.kacper.starzynski.employeeGratification.achievementConfigurationApi;

import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

public interface AchievementService {
    List<QuestionId> getQuestionsForCode(AchievementConfigurationId configId, AchievementCode achievementCode);
}
