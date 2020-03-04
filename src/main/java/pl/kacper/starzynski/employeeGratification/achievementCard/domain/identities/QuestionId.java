package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@Immutable
@AllArgsConstructor
public class QuestionId {
    private final UUID questionId;
}
