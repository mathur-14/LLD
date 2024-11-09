package ola.models;

// File name: TripMetaData.java
public class TripMetaData {
    private Rider rider;
    private Location pickupLocation;
    private Location dropLocation;
    private double distance;
    private double duration;
    private double basePrice;
    
    public TripMetaData(Rider rider, Location pickupLocation, Location dropLocation) {
        this.rider = rider;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.distance = calculateDistance();
        this.duration = calculateEstimatedDuration();
        this.basePrice = calculateBasePrice();
    }
    
    // Getters
    public Rider getRider() {
        return rider;
    }
    
    public Location getPickupLocation() {
        return pickupLocation;
    }
    
    public Location getDropLocation() {
        return dropLocation;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public double getDuration() {
        return duration;
    }
    
    public double getBasePrice() {
        return basePrice;
    }
    
    // Helper methods
    private double calculateDistance() {
        // Using Euclidean distance for simplicity
        double dx = dropLocation.getX() - pickupLocation.getX();
        double dy = dropLocation.getY() - pickupLocation.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    private double calculateEstimatedDuration() {
        // Simplified duration calculation
        // Assuming average speed of 40 units per hour
        return distance / 40.0;
    }
    
    private double calculateBasePrice() {
        // Basic price calculation
        // Base fare + (price per km * distance) + (price per minute * duration)
        double baseFare = 50.0;
        double pricePerKm = 10.0;
        double pricePerMinute = 2.0;
        
        return baseFare + (pricePerKm * distance) + (pricePerMinute * duration * 60);
    }
}