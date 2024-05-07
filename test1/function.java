import java.time.Duration;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

class ParkingLot {
    // Other methods and properties
    
    public double calculateParkingFee(Vehicle vehicle) {
        LocalDateTime timeIn = vehicle.getTimeIn();
        LocalDateTime timeOut = LocalDateTime.now(); // Assuming current time is the time out
        long totalDays = Duration.between(timeIn, timeOut).toDays();
        if (totalDays < 1) {
            totalDays = 1;
        }
        double parkingFee = vehicle.getParkingPrice() * totalDays;
        return parkingFee;
    }
}

abstract class Vehicle {
    private String plateNumber;
    private double width;
    private LocalDateTime timeIn;
    private LocalDateTime timeOut;

    public Vehicle(String plateNumber, double width) {
        this.plateNumber = plateNumber;
        this.width = width;
        this.timeIn = LocalDateTime.now().minusDays(1); // Simulating entering 1 day ago
        this.timeOut = null;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public double getWidth() {
        return width;
    }

    public abstract double getParkingPrice();
}

class Car extends Vehicle {
    public Car(String plateNumber) {
        super(plateNumber, 2.0);
    }

    @Override
    public double getParkingPrice() {
        return 10.0;
    }
}

class Bike extends Vehicle {
    public Bike(String plateNumber) {
        super(plateNumber, 0.8);
    }

    @Override
    public double getParkingPrice() {
        return 2.0;
    }
}

public class TestParkingLot {

    @Test
    public void testCalculateParkingFee() {
        ParkingLot parkingLot = new ParkingLot();
        Vehicle car = new Car("ABC123");
        car.setTimeOut(LocalDateTime.now()); // Simulating leaving now
        Vehicle bike = new Bike("XYZ456");
        bike.setTimeOut(LocalDateTime.now()); // Simulating leaving now

        double carFee = parkingLot.calculateParkingFee(car);
        double bikeFee = parkingLot.calculateParkingFee(bike);

        // Check if the calculated fees are correct
        assertEquals(10.0, carFee, 0.001); // Car parking fee should be $10 for 1 day
        assertEquals(2.0, bikeFee, 0.001); // Bike parking fee should be $2 for 1 day
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("TestParkingLot");
    }
}
