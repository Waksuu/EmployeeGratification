package pl.kacper.starzynski.employeeGratification.achievement.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kacper.starzynski.employeeGratification.achievement.domain.AchievementRepository;
import pl.kacper.starzynski.employeeGratification.achievementConfigurationApi.AchievementService;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementException;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

@Service
@AllArgsConstructor
public class AchievementServiceImpl implements AchievementService {
    private final AchievementRepository achievementRepository;

    public List<QuestionId> getQuestionsForCode(AchievementCode achievementCode) {
        var achievement = achievementRepository.findById(achievementCode).orElseThrow(AchievementException::new);
        return achievement.getQuestionIds();
    }
}
