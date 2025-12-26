package service;

import model.Car;
import model.CarType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private final List<Car> fleet;

    public RentalSystem() {
        this.fleet = new ArrayList<>();
    }

    public void addCar(Car car) {
        fleet.add(car);
    }

    public boolean makeReservation(CarType type, LocalDateTime start, int days) {
        if (days < 1) throw new IllegalArgumentException("Days must be > 0");
        LocalDateTime end = start.plusDays(days);

        for (Car car : fleet) {
            if (car.getType() == type && car.isAvailable(start, end)) {
                car.reserve(start, end);
                return true;
            }
        }

        return false;
    }
}
