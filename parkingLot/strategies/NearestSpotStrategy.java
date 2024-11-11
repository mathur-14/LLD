package parkingLot.strategies;
import java.util.List;

import parkingLot.models.ParkingFloor;
import parkingLot.models.ParkingSpot;

public class NearestSpotStrategy implements SpotAllocationStrategy {
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, int vehicleType) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findSpotForVehicle(vehicleType);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }
}