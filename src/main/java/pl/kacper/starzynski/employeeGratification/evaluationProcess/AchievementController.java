package pl.kacper.starzynski.employeeGratification.evaluationProcess;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    @GetMapping()
    String getALlAchievements() {
        return "aaaa";
    }
}
