import java.util.*;

abstract class Vehicle_Q2 {
    private String vehicleId;
    private String modelName;
    private boolean isAvailable;

    public Vehicle_Q2(String vehicleId, String modelName) {
        this.vehicleId = vehicleId;
        this.modelName = modelName;
        this.isAvailable = true;
    }

    public String getModelName() { return modelName; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public abstract double calculateRentalCharge(int days);
}

class StandardCar_Q2 extends Vehicle_Q2 {
    public StandardCar_Q2(String vehicleId, String modelName) {
        super(vehicleId, modelName);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 50.0;
    }
}

class LuxuryCar_Q2 extends Vehicle_Q2 {
    public LuxuryCar_Q2(String vehicleId, String modelName) {
        super(vehicleId, modelName);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 100.0;
    }
}

class Customer_Q2 {
    private String customerId;
    private String name;

    public Customer_Q2(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }
}

class Rental_Q2 {
    private String rentalId;
    private Customer_Q2 customer;
    private Vehicle_Q2 vehicle;
    private int days;
    private double totalCharge;

    public Rental_Q2(String rentalId, Customer_Q2 customer, Vehicle_Q2 vehicle, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalCharge = vehicle.calculateRentalCharge(days);
    }

    public Vehicle_Q2 getVehicle() { return vehicle; }
    public double getTotalCharge() { return totalCharge; }
}

class RentalService_Q2 {
    private Map<String, Vehicle_Q2> vehicles = new HashMap<>();
    private Map<String, Rental_Q2> activeRentals = new HashMap<>();

    public void addVehicle(Vehicle_Q2 vehicle) {
        vehicles.put(vehicle.getModelName(), vehicle);
    }

    public double rentVehicle(Customer_Q2 customer, String modelName, int days) {
        Vehicle_Q2 vehicle = vehicles.get(modelName);
        if (vehicle == null || !vehicle.isAvailable()) {
            throw new IllegalStateException("Vehicle " + modelName + " is not available for rent.");
        }

        vehicle.setAvailable(false);
        Rental_Q2 rental = new Rental_Q2("R-" + (activeRentals.size() + 1), customer, vehicle, days);
        activeRentals.put(modelName, rental);

        System.out.printf("%s rented for %d days. Total charge: $%.2f%n", modelName, days, rental.getTotalCharge());
        return rental.getTotalCharge();
    }

    public void returnVehicle(String modelName) {
        Rental_Q2 rental = activeRentals.get(modelName);
        if (rental == null) {
            throw new IllegalArgumentException("No active rental found for " + modelName);
        }

        rental.getVehicle().setAvailable(true);
        activeRentals.remove(modelName);
        System.out.println(modelName + " returned. Now available.");
    }
}

public class VehicleRentalSystem_Q2 {
    public static void main(String[] args) {
        RentalService_Q2 service = new RentalService_Q2();
        service.addVehicle(new LuxuryCar_Q2("V01", "Luxury Car A"));
        service.addVehicle(new StandardCar_Q2("V02", "Standard Car B"));

        Customer_Q2 customer = new Customer_Q2("C01", "Customer");

        service.rentVehicle(customer, "Luxury Car A", 3);
        service.rentVehicle(customer, "Standard Car B", 5);
        service.returnVehicle("Luxury Car A");
    }
}
