package ola.models;

import ola.enums.TripStatus;

public class Trip {
    private int tripId;
    private Rider rider;
    private Driver driver;
    private Location pickupLocation;
    private Location dropLocation;
    private double price;
    private TripStatus status;
    
    public Trip(int tripId, Rider rider, Driver driver, Location pickupLocation, 
                Location dropLocation, double price) {
        this.tripId = tripId;
        this.rider = rider;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.price = price;
        this.status = TripStatus.CREATED;
    }
    
    // Getters
    public int getTripId() { return tripId; }
    public Rider getRider() { return rider; }
    public Driver getDriver() { return driver; }
    public Location getPickupLocation() { return pickupLocation; }
    public Location getDropLocation() { return dropLocation; }
    public double getPrice() { return price; }
    public TripStatus getStatus() { return status; }
    
    // Status management
    public void startTrip() {
        this.status = TripStatus.IN_PROGRESS;
        this.driver.setAvailable(false);
    }
    
    public void endTrip() {
        this.status = TripStatus.COMPLETED;
        this.driver.setAvailable(true);
    }
    
    public void cancelTrip() {
        this.status = TripStatus.CANCELLED;
        this.driver.setAvailable(true);
    }
    
    @Override
    public String toString() {
        return String.format("Trip[id=%d, rider=%s, driver=%s, status=%s, price=%.2f]",
            tripId, rider.getName(), driver.getName(), status, price);
    }
}