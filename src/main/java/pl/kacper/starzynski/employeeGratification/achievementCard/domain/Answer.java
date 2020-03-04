package pl.kacper.starzynski.employeeGratification.achievementCard.domain;

import com.mongodb.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Immutable
public class Answer {
    private final String answer;

    public boolean isBlank() {
        return answer.isBlank();
    }
}
