package ola.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ola.models.Driver;

// File name: DriverManager.java
public class DriverManager {
    private static DriverManager instance;
    private Map<String, Driver> drivers;
    
    private DriverManager() {
        drivers = new HashMap<>();
    }
    
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }
    
    public void addDriver(String id, Driver driver) {
        drivers.put(id, driver);
    }
    
    public Driver getDriver(String id) {
        return drivers.get(id);
    }
    
    public List<Driver> getAvailableDrivers() {
        return drivers.values()
            .stream()
            .filter(driver -> driver.isAvailable())
            .collect(Collectors.toList());
    }
    
    public void removeDriver(String id) {
        drivers.remove(id);
    }
}