package pl.kacper.starzynski.employeeGratification.achievementCard;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kacper.starzynski.employeeGratification.achievementCard.application.AchievementCardService;
import pl.kacper.starzynski.employeeGratification.achievementCard.readmodel.AchievementApplicationDTO;

@RestController
@RequestMapping("/employee-gratification")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementCardService achievementCardService;
//    private final EvaluationProcessRepository evaluationProcessRepository;

    @PostMapping(path = "/{evaluationProcess}/achievement-application", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getALlAchievements(@PathVariable UUID achievementCardId, @RequestBody
            AchievementApplicationDTO achievementApplicationDTO) {
        achievementCardService.applyForAchievement(achievementCardId, achievementApplicationDTO);
    }

//    @GetMapping("")
//    void xdddd() {
//        var eval = EvaluationProcessFactory.create(2L, new AchievementCard(new ArrayList<AchievementApplication>()), Arrays.asList(
//                new AchievementCode("MA001"),
//                new AchievementCode("MA002"),
//                new AchievementCode("RA001")
//        ));
//        evaluationProcessRepository.save(eval);
//    }
}
