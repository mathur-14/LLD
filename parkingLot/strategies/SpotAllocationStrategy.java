package parkingLot.strategies;
import parkingLot.models.ParkingSpot;

import java.util.List;

import parkingLot.models.ParkingFloor;

public interface SpotAllocationStrategy {
    ParkingSpot findSpot(List<ParkingFloor> floors, int vehicleType);
}