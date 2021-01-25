package pl.kacper.starzynski.employeeGratification.achievementConfiguration.domain;

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
abstract class Achievement {
    @Id
    private final AchievementCode achievementCode;
    private final List<QuestionId> questionIds;

    abstract boolean isProposedOutcomeValid(ProposedOutcome proposedOutcome);
    abstract boolean canBeAppliedForMultipleTimes();

    boolean isEqual(AchievementCode achievementCode) {
        return this.achievementCode.equals(achievementCode);
    }

    List<QuestionId> getQuestionIds() {
        return questionIds;
    }
}
