package pl.kacper.starzynski.employeeGratification.achievementCard.domain.identities;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@Immutable
@AllArgsConstructor
@EqualsAndHashCode
public class QuestionnaireId {
    private final UUID questionnaireId;
}
