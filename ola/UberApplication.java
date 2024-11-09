package ola;

import ola.models.*;
import ola.managers.*;
import ola.enums.*;

public class UberApplication {
    public static void main(String[] args) {
        // Initialize managers
        RiderManager riderManager = RiderManager.getInstance();
        DriverManager driverManager = DriverManager.getInstance();
        TripManager tripManager = TripManager.getInstance();
        
        // Create riders
        Rider keerti = new Rider("Keerti", Rating.FIVE_STARS);
        Rider gaurav = new Rider("Gaurav", Rating.FOUR_STARS);
        
        // Create drivers
        Driver yogita = new Driver("Yogita", Rating.THREE_STARS);
        yogita.setCurrentLocation(new Location(0, 0));
        
        Driver riddhi = new Driver("Riddhi", Rating.FOUR_STARS);
        riddhi.setCurrentLocation(new Location(2, 2));
        
        // Register users
        riderManager.addRider("keerti", keerti);
        riderManager.addRider("gaurav", gaurav);
        
        driverManager.addDriver("yogita", yogita);
        driverManager.addDriver("riddhi", riddhi);
        
        try {
            // Create a trip
            Trip trip1 = tripManager.createTrip(
                keerti,
                new Location(10, 10),
                new Location(30, 30)
            );
            
            System.out.println("Trip created: " + trip1);
            
            // Start the trip
            tripManager.updateTripStatus(trip1.getTripId(), TripStatus.IN_PROGRESS);
            System.out.println("Trip started: " + trip1);
            
            // End the trip
            tripManager.updateTripStatus(trip1.getTripId(), TripStatus.COMPLETED);
            System.out.println("Trip completed: " + trip1);
            
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}