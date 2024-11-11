package parkingLot.trackers;
import java.util.concurrent.ConcurrentHashMap;

import parkingLot.models.ParkingSpot;
import parkingLot.models.Vehicle;

public class VehicleTracker {
    private final ConcurrentHashMap<String, ParkingSpot> spotIdToSpot;
    private final ConcurrentHashMap<String, ParkingSpot> vehicleNumberToSpot;
    private final ConcurrentHashMap<String, ParkingSpot> ticketIdToSpot;

    public VehicleTracker() {
        this.spotIdToSpot = new ConcurrentHashMap<>();
        this.vehicleNumberToSpot = new ConcurrentHashMap<>();
        this.ticketIdToSpot = new ConcurrentHashMap<>();
    }

    public void trackVehicle(Vehicle vehicle, ParkingSpot spot) {
        spotIdToSpot.put(spot.getSpotId(), spot);
        vehicleNumberToSpot.put(vehicle.getVehicleNumber(), spot);
        ticketIdToSpot.put(vehicle.getTicketId(), spot);
    }

    public void untrackVehicle(Vehicle vehicle, ParkingSpot spot) {
        spotIdToSpot.remove(spot.getSpotId());
        vehicleNumberToSpot.remove(vehicle.getVehicleNumber());
        ticketIdToSpot.remove(vehicle.getTicketId());
    }

    public boolean isVehicleParked(String vehicleNumber) {
        return vehicleNumberToSpot.containsKey(vehicleNumber);
    }

    public ParkingSpot findSpotByVehicleNumber(String vehicleNumber) {
        return vehicleNumberToSpot.get(vehicleNumber);
    }

    public ParkingSpot findSpotByTicketId(String ticketId) {
        return ticketIdToSpot.get(ticketId);
    }

    public ParkingSpot findSpotById(String spotId) {
        return spotIdToSpot.get(spotId);
    }
}