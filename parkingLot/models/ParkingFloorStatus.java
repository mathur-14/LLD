package parkingLot.models;

import java.util.*;

public class ParkingFloorStatus {
    private final int floorNumber;
    private final Map<Integer, Integer> typeToCount;

    public ParkingFloorStatus(int floorNumber) {
        this.floorNumber = floorNumber;
        this.typeToCount = new HashMap<>();
    }

    public void updateCount(int vehicleType, int count) {
        typeToCount.put(vehicleType, count);
    }

    public int getCount(int vehicleType) {
        return typeToCount.getOrDefault(vehicleType, 0);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Map<Integer, Integer> getTypeToCount() {
        return Collections.unmodifiableMap(typeToCount);
    }
}