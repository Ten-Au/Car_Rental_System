import model.Car;
import model.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.RentalSystem;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Car Rental System Unit Tests")
class RentalSystemTest {

    private RentalSystem system;

    @BeforeEach
    void setUp() {
        system = new RentalSystem();
        system.addCar(new Car(1, CarType.SEDAN));
        system.addCar(new Car(2, CarType.SUV));
    }

    @Test
    @DisplayName("Should successfully reserve a car when inventory is available")
    void testSuccessfulReservation() {
        boolean result = system.makeReservation(CarType.SEDAN, LocalDateTime.now(), 2);
        assertTrue(result, "Should be able to reserve the only Sedan");
    }

    @Test
    @DisplayName("Should fail to reserve when all cars of the requested type are booked")
    void testSoldOutScenario() {
        LocalDateTime start = LocalDateTime.now();

        boolean firstBooking = system.makeReservation(CarType.SUV, start, 3);
        assertTrue(firstBooking);

        boolean secondBooking = system.makeReservation(CarType.SUV, start, 3);
        assertFalse(secondBooking, "Should return false because the only SUV is already taken");
    }

    @Test
    @DisplayName("Should allow booking the same car for non-overlapping dates")
    void testNonOverlappingReservation() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextMonth = now.plusMonths(1);

        assertTrue(system.makeReservation(CarType.SEDAN, now, 2));

        assertTrue(system.makeReservation(CarType.SEDAN, nextMonth, 2));
    }

    @Test
    @DisplayName("Should fail when the requested car type is not in the fleet")
    void testCarTypeUnavailable() {
        boolean result = system.makeReservation(CarType.VAN, LocalDateTime.now(), 1);
        assertFalse(result, "Should fail as no Vans exist in fleet");
    }

    @Test
    @DisplayName("Should throw exception if reservation duration is invalid (less than 1 day)")
    void testInvalidDuration() {
        assertThrows(IllegalArgumentException.class, () -> {
            system.makeReservation(CarType.SEDAN, LocalDateTime.now(), 0);
        });
    }
}
