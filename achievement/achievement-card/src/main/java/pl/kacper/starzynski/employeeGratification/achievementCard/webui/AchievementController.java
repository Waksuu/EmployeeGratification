package pl.kacper.starzynski.employeeGratification.achievementCard.webui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kacper.starzynski.employeeGratification.achievementCard.application.AchievementApplicationDTO;
import pl.kacper.starzynski.employeeGratification.achievementCard.application.AchievementCardService;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.AchievementCard;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.AchievementCardId;
import pl.kacper.starzynski.employeeGratification.achievementCard.infrastructure.mongo.AchievementCardMongoRepository;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementConfigurationId;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/employee-gratification")
@RequiredArgsConstructor
public class AchievementController {
    private final AchievementCardService achievementCardService;
    private final AchievementCardMongoRepository achievementCardRepository;

    @PostMapping(path = "/{achievementCardId}/achievement-application")
    void applyForAchievement(@PathVariable AchievementCardId achievementCardId,
            @RequestBody AchievementApplicationDTO achievementApplicationDTO) {
        achievementCardService.applyForAchievement(achievementCardId, achievementApplicationDTO);
    }

    @GetMapping(path = "/test")
    void get() {
        var a = achievementCardRepository.save(new AchievementCard(new AchievementCardId(UUID.randomUUID()), Collections.emptyList(), new AchievementConfigurationId(UUID.randomUUID())));
        System.out.print(a.getId().toString());
    }
}
