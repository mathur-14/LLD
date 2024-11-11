package parkingLot.models;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// model/ParkingFloor.java
public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> spotIdToSpot;
    private final Map<Integer, List<ParkingSpot>> typeToSpots;
    private final AtomicInteger[] freeSpotCounts;

    public ParkingFloor(int floorNumber, String[][] floorPlan) {
        this.floorNumber = floorNumber;
        this.spotIdToSpot = new ConcurrentHashMap<>();
        this.typeToSpots = new ConcurrentHashMap<>();
        this.freeSpotCounts = new AtomicInteger[5]; // Support up to type 4
        
        for (int i = 0; i < freeSpotCounts.length; i++) {
            freeSpotCounts[i] = new AtomicInteger(0);
            typeToSpots.put(i, new CopyOnWriteArrayList<>());
        }
        
        initializeSpots(floorPlan);
    }

    private void initializeSpots(String[][] floorPlan) {
        for (int row = 0; row < floorPlan.length; row++) {
            for (int col = 0; col < floorPlan[row].length; col++) {
                String[] spotInfo = floorPlan[row][col].split("-");
                int type = Integer.parseInt(spotInfo[0]);
                boolean isActive = "1".equals(spotInfo[1]);

                if (isActive) {
                    ParkingSpot spot = new ParkingSpot(floorNumber, row, col, type);
                    String spotId = spot.getSpotId();
                    spotIdToSpot.put(spotId, spot);
                    typeToSpots.get(type).add(spot);
                    freeSpotCounts[type].incrementAndGet();
                }
            }
        }
    }

    public List<ParkingSpot> getAvailableSpots(int vehicleType) {
        return typeToSpots.get(vehicleType).stream()
            .filter(spot -> !spot.isOccupied())
            .collect(Collectors.toList());
    }

    public ParkingSpot findSpotForVehicle(int vehicleType) {
        List<ParkingSpot> spots = typeToSpots.get(vehicleType);
        if (spots == null) return null;

        return spots.stream()
            .filter(spot -> !spot.isOccupied())
            .findFirst()
            .orElse(null);
    }

    public synchronized boolean parkVehicle(Vehicle vehicle, ParkingSpot spot) {
        if (spot.isOccupied() || spot.getType() != vehicle.getType()) {
            return false;
        }
        
        spot.occupy(vehicle);
        freeSpotCounts[vehicle.getType()].decrementAndGet();
        return true;
    }

    public synchronized boolean releaseSpot(String spotId) {
        ParkingSpot spot = spotIdToSpot.get(spotId);
        if (spot == null || !spot.isOccupied()) {
            return false;
        }

        int vehicleType = spot.getType();
        spot.vacate();
        freeSpotCounts[vehicleType].incrementAndGet();
        return true;
    }

    public ParkingSpot getSpotById(String spotId) {
        return spotIdToSpot.get(spotId);
    }

    public int getFreeSpotsCount(int vehicleType) {
        return freeSpotCounts[vehicleType].get();
    }

    public Map<Integer, Integer> getFloorStatus() {
        Map<Integer, Integer> status = new HashMap<>();
        typeToSpots.forEach((type, spots) -> {
            long freeCount = spots.stream()
                .filter(spot -> !spot.isOccupied())
                .count();
            status.put(type, (int) freeCount);
        });
        return status;
    }

    public List<ParkingSpot> getNearestAvailableSpots(int vehicleType, int preferredRow) {
        return typeToSpots.get(vehicleType).stream()
            .filter(spot -> !spot.isOccupied())
            .sorted(Comparator.comparingInt(spot -> 
                Math.abs(Integer.parseInt(spot.getSpotId().split("-")[1]) - preferredRow)))
            .collect(Collectors.toList());
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isFloorFull() {
        return Arrays.stream(freeSpotCounts)
            .allMatch(count -> count.get() == 0);
    }
}