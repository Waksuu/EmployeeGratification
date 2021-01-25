package pl.kacper.starzynski.employeeGratification.achievementCard.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.util.Pair;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

@Getter
@AllArgsConstructor
public class AchievementApplicationDTO {
    private final AchievementCode achievementCode;
    private final ProposedOutcome proposedOutcome;
    private final List<Pair<QuestionId, String>> answers;
}
