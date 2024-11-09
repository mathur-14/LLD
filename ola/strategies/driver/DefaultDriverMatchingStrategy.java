package ola.strategies.driver;

// File name: DefaultDriverMatchingStrategy.java
import java.util.List;

import ola.managers.DriverManager;
import ola.models.Driver;
import ola.models.Location;
import ola.models.TripMetaData;

import java.util.Comparator;

public class DefaultDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public Driver matchDriver(TripMetaData tripMetaData) {
        List<Driver> availableDrivers = DriverManager.getInstance().getAvailableDrivers();
        
        if (availableDrivers.isEmpty()) {
            return null;
        }
        
        // Find the nearest driver based on location
        return availableDrivers.stream()
            .min(Comparator.comparingDouble(driver -> 
                calculateDistance(
                    driver.getCurrentLocation(), 
                    tripMetaData.getPickupLocation()
                )))
            .orElse(null);
    }
    
    private double calculateDistance(Location driverLocation, Location pickupLocation) {
        double dx = driverLocation.getX() - pickupLocation.getX();
        double dy = driverLocation.getY() - pickupLocation.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}