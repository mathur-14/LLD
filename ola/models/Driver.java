package ola.models;

import ola.enums.Rating;

public class Driver {
    private String name;
    private Rating rating;
    private boolean available;
    private Location currentLocation;
    
    public Driver(String name, Rating rating) {
        this.name = name;
        this.rating = rating;
        this.available = true;
        this.currentLocation = new Location(0, 0); // Default location
    }
    
    public String getName() { 
        return name; 
    }
    
    public Rating getRating() { 
        return rating; 
    }
    
    public boolean isAvailable() { 
        return available; 
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public Location getCurrentLocation() {
        return currentLocation;
    }
    
    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }
}