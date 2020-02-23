package pl.kacper.starzynski.employeeGratification.evaluationProcess;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.application.EvaluationProcessService;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.domain.*;
import pl.kacper.starzynski.employeeGratification.evaluationProcess.readmodel.AchievementApplicationDTO;

import java.util.Arrays;

@RestController
@RequestMapping("/employee-gratification")
@RequiredArgsConstructor
public class AchievementController {
    private final EvaluationProcessService evaluationProcessService;
    private final EvaluationProcessRepository evaluationProcessRepository;

    @PostMapping(path = "/{evaluationProcess}/achievement-application", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getALlAchievements(@PathVariable long evaluationProcess, @RequestBody AchievementApplicationDTO achievementApplicationDTO) {
        evaluationProcessService.applyForAchievement(evaluationProcess, achievementApplicationDTO);
    }

    @GetMapping("")
    void xdddd() {
        var eval = EvaluationProcessFactory.create(2L, AchievementCardFactory.create(), Arrays.asList(
                AchievementCodeFactory.create("MA001"),
                AchievementCodeFactory.create("MA002"),
                AchievementCodeFactory.create("RA001")
        ));
        evaluationProcessRepository.save(eval);
    }
}
