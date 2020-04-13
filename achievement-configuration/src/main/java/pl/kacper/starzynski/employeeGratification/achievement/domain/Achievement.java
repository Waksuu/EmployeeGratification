package pl.kacper.starzynski.employeeGratification.achievement.domain;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import pl.kacper.starzynski.employeeGratification.sharedKernel.AchievementCode;
import pl.kacper.starzynski.employeeGratification.sharedKernel.ProposedOutcome;
import pl.kacper.starzynski.employeeGratification.sharedKernel.QuestionId;

import java.util.List;

@Immutable
@AllArgsConstructor
@EqualsAndHashCode
public abstract class Achievement {
    @Id
    private final AchievementCode achievementCode;
    private final List<QuestionId> questionIds;

    public abstract boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome);
    public abstract boolean canBeAppliedForMultipleTimes();

    public List<QuestionId> getQuestionIds() {
        return questionIds;
    }
}
