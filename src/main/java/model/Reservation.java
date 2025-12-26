package model;

import java.time.LocalDateTime;

public record Reservation(LocalDateTime start, LocalDateTime end) {
    public Reservation {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
    }

    public boolean overlaps(LocalDateTime otherStart, LocalDateTime otherEnd) {
        return start.isBefore(otherEnd) && otherStart.isBefore(end);
    }

}
