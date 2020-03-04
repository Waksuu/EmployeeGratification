package pl.kacper.starzynski.employeeGratification.achievementCard.webui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kacper.starzynski.employeeGratification.achievementCard.application.AchievementCardService;
import pl.kacper.starzynski.employeeGratification.achievementCard.readmodel.AchievementApplicationDTO;

import java.util.UUID;

@RestController
@RequestMapping("/employee-gratification")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementCardService achievementCardService;

    @PostMapping(path = "/{achievementCardId}/achievement-application", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getALlAchievements(@PathVariable UUID achievementCardId, @RequestBody
            AchievementApplicationDTO achievementApplicationDTO) {
        achievementCardService.applyForAchievement(achievementCardId, achievementApplicationDTO);
    }
}
