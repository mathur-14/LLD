package ola.managers;

import java.util.Map;

import ola.enums.TripStatus;
import ola.models.Driver;
import ola.models.Location;
import ola.models.Rider;
import ola.models.Trip;
import ola.models.TripMetaData;

import java.util.HashMap;

public class TripManager {
    private static TripManager instance;
    private Map<Integer, Trip> trips;
    private int tripCounter;
    
    private TripManager() {
        trips = new HashMap<>();
        tripCounter = 0;
    }
    
    public static TripManager getInstance() {
        if (instance == null) {
            instance = new TripManager();
        }
        return instance;
    }
    
    public Trip createTrip(Rider rider, Location pickup, Location dropoff) {
        TripMetaData metadata = new TripMetaData(rider, pickup, dropoff);
        Driver driver = StrategyManager.getInstance()
            .getDriverMatchingStrategy()
            .matchDriver(metadata);
            
        if (driver == null) {
            throw new RuntimeException("No drivers available");
        }
        
        double price = StrategyManager.getInstance()
            .getPricingStrategy()
            .calculatePrice(metadata);
            
        Trip trip = new Trip(++tripCounter, rider, driver, pickup, dropoff, price);
        trips.put(tripCounter, trip);
        return trip;
    }
    
    public Trip getTrip(int tripId) {
        return trips.get(tripId);
    }
    
    public void updateTripStatus(int tripId, TripStatus status) {
        Trip trip = trips.get(tripId);
        if (trip != null) {
            switch (status) {
                case IN_PROGRESS:
                    trip.startTrip();
                    break;
                case COMPLETED:
                    trip.endTrip();
                    break;
                case CANCELLED:
                    trip.cancelTrip();
                    break;
            }
        }
    }
}