package parkingLot.managers;
import parkingLot.models.ParkingSpot;
import parkingLot.strategies.NearestSpotStrategy;
import parkingLot.strategies.SpotAllocationStrategy;

import java.util.*;

import parkingLot.models.ParkingFloor;
import parkingLot.models.ParkingFloorStatus;

import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingLotManager {
    private final List<ParkingFloor> floors;
    private final SpotAllocationStrategy spotAllocationStrategy;

    public ParkingLotManager(String[][][] parkingLayout, NearestSpotStrategy strategy) {
        this.floors = new CopyOnWriteArrayList<>();
        this.spotAllocationStrategy = strategy;
        initializeFloors(parkingLayout);
    }

    private void initializeFloors(String[][][] parkingLayout) {
        for (int floorNumber = 0; floorNumber < parkingLayout.length; floorNumber++) {
            ParkingFloor floor = new ParkingFloor(floorNumber, parkingLayout[floorNumber]);
            floors.add(floor);
        }
    }

    public ParkingSpot findAvailableSpot(int vehicleType) {
        return spotAllocationStrategy.findSpot(floors, vehicleType);
    }

    public boolean removeVehicle(String spotId) {
        String[] parts = spotId.split("-");
        int floorNumber = Integer.parseInt(parts[0]);
        
        if (floorNumber >= floors.size()) {
            return false;
        }
        
        return floors.get(floorNumber).releaseSpot(spotId);
    }

    public ParkingSpot getSpot(String spotId) {
        String[] parts = spotId.split("-");
        int floorNumber = Integer.parseInt(parts[0]);
        
        if (floorNumber >= floors.size()) {
            return null;
        }
        
        return floors.get(floorNumber).getSpotById(spotId);
    }

    public int getFreeSpotsCount(int floor, int vehicleType) {
        if (floor >= floors.size()) {
            return 0;
        }
        return floors.get(floor).getFreeSpotsCount(vehicleType);
    }

    public List<ParkingFloor> getFloors() {
        return Collections.unmodifiableList(floors);
    }

    public List<ParkingFloorStatus> getParkingLotStatus() {
        List<ParkingFloorStatus> status = new ArrayList<>();
        for (int i = 0; i < floors.size(); i++) {
            ParkingFloorStatus floorStatus = new ParkingFloorStatus(i);
            ParkingFloor floor = floors.get(i);
            
            // Update counts for each vehicle type
            floorStatus.updateCount(2, floor.getFreeSpotsCount(2));
            floorStatus.updateCount(4, floor.getFreeSpotsCount(4));
            
            status.add(floorStatus);
        }
        return status;
    }

    public boolean isFullForVehicleType(int vehicleType) {
        return floors.stream()
            .allMatch(floor -> floor.getFreeSpotsCount(vehicleType) == 0);
    }

    public Optional<ParkingSpot> findNearestSpotToExit(int vehicleType) {
        return floors.stream()
            .flatMap(floor -> floor.getAvailableSpots(vehicleType).stream())
            .min(Comparator.comparingInt(spot -> {
                String[] coords = spot.getSpotId().split("-");
                return Integer.parseInt(coords[0]) * 100 + 
                       Integer.parseInt(coords[1]) + 
                       Integer.parseInt(coords[2]);
            }));
    }

    public Map<Integer, Integer> getTotalAvailableSpots() {
        Map<Integer, Integer> totalSpots = new HashMap<>();
        for (ParkingFloor floor : floors) {
            for (int type : new int[]{2, 4}) {
                totalSpots.merge(type, 
                    floor.getFreeSpotsCount(type), 
                    Integer::sum);
            }
        }
        return totalSpots;
    }
}