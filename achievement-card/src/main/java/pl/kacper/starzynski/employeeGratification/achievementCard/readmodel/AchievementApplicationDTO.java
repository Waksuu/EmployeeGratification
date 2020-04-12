package pl.kacper.starzynski.employeeGratification.achievementCard.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.util.Pair;
import pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities.QuestionId;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class AchievementApplicationDTO implements Serializable {
    private final AchievementCode achievementCode;
    private final ProposedOutcome proposedOutcome;
    private final List<Pair<QuestionId, String>> answers;
}
