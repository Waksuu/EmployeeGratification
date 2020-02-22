package pl.kacper.starzynski.employeeGratification.evaluationProcess.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.domain.*;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.readmodel.AchievementApplicationDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EvaluationProcessService {
    private final EvaluationProcessRepository evaluationProcessRepository;
    private final AchievementRepository achievementRepository;
    private final AchievementConfigurationService achievementConfigurationService;

    @Transactional
    public void applyForAchievement(AchievementApplicationDTO achievementApplicationDTO) {
        //TODO: Work with optional, maybe?
        var achievement = achievementRepository.findById(achievementApplicationDTO.getAchievementCode())
                .orElseThrow(AchievementException::new);

        var application = AchievementApplicationFactory.create(UUID.randomUUID(),
                achievement,
                achievementApplicationDTO.getProposedOutcome(),
                achievementConfigurationService);

        var evaluationProcess = evaluationProcessRepository.findById(achievementApplicationDTO.getEvaluationProcessId())
                .orElseThrow(AchievementException::new);

        evaluationProcess.applyForAchievement(application);
        evaluationProcessRepository.save(evaluationProcess);
    }
}
