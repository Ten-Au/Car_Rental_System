package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Car {
    private final int id;
    private final CarType type;
    private final List<Reservation> schedule;

    public Car(int id, CarType type) {
        this.id = id;
        this.type = type;
        this.schedule = new ArrayList<>();
    }

    public CarType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        for (Reservation r : schedule) {
            if (r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    public void reserve(LocalDateTime start, LocalDateTime end) {
        if (!isAvailable(start, end)) {
            throw new IllegalArgumentException("Car is not available at this time");
        }
        schedule.add(new Reservation(start, end));
    }
}
