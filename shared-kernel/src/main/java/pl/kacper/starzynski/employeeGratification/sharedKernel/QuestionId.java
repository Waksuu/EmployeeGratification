package pl.kacper.starzynski.employeeGratification.sharedKernel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class QuestionId {
    private final UUID questionId;
}
