package parkingLot.strategies;

import parkingLot.models.ParkingSpot;
import java.util.List;
import parkingLot.models.ParkingFloor;

public class OptimalSpotStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, int vehicleType) {
        ParkingSpot bestSpot = null;
        int minDistance = Integer.MAX_VALUE;

        for (ParkingFloor floor : floors) {
            List<ParkingSpot> availableSpots = floor.getAvailableSpots(vehicleType);
            for (ParkingSpot spot : availableSpots) {
                int distance = calculateDistance(spot);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestSpot = spot;
                }
            }
        }
        return bestSpot;
    }

    private int calculateDistance(ParkingSpot spot) {
        String[] coordinates = spot.getSpotId().split("-");
        int floor = Integer.parseInt(coordinates[0]);
        int row = Integer.parseInt(coordinates[1]);
        int col = Integer.parseInt(coordinates[2]);
        
        // Calculate Manhattan distance from entrance (assumed to be at 0,0,0)
        return floor * 10 + row + col; // Floor distance weighted more
    }
}