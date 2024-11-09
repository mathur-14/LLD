package ola.managers;

import java.util.Map;

import ola.models.Rider;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class RiderManager {
    private static RiderManager instance;
    private Map<String, Rider> riders;
    
    private RiderManager() {
        riders = new HashMap<>();
    }
    
    public static RiderManager getInstance() {
        if (instance == null) {
            instance = new RiderManager();
        }
        return instance;
    }
    
    public void addRider(String id, Rider rider) {
        riders.put(id, rider);
    }
    
    public Rider getRider(String id) {
        return riders.get(id);
    }
    
    public void removeRider(String id) {
        riders.remove(id);
    }
    
    public List<Rider> getAllRiders() {
        return new ArrayList<>(riders.values());
    }
    
    public boolean isRiderRegistered(String id) {
        return riders.containsKey(id);
    }
}