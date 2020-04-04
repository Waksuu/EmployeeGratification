package pl.kacper.starzynski.employeeGratification.sharedKernel;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public abstract class DomainEvent {
    private final LocalDateTime timeStamp;

    protected DomainEvent() {
        this.timeStamp = LocalDateTime.now(ZoneOffset.UTC);
    }
}
