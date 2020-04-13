package pl.kacper.starzynski.employeeGratification.achievementConfiguration.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain.AchievementConfigurationRepository;
import pl.kacper.starzynski.employeeGratification.achievementConfigurationApi.AchievementService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementConfigurationRepository achievementConfigurationRepository;

    public List<QuestionId> getQuestionsForCode(AchievementConfigurationId configId, AchievementCode achievementCode) {
        var achievementConfiguration = achievementConfigurationRepository.findById(configId).orElseThrow(AchievementException::new);
        return achievementConfiguration.getQuestionIds(achievementCode);
    }
}
