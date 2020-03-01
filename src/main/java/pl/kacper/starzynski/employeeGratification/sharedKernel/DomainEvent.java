package pl.kacper.starzynski.employeeGratification.sharedKernel;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import lombok.Getter;

@Getter
public abstract class DomainEvent {
    private final LocalDateTime timeStamp;

    protected DomainEvent() {
        this.timeStamp = LocalDateTime.now(ZoneOffset.UTC);
    }
}
