package parkingLot.factory;

import parkingLot.managers.ParkingLotManager;
import parkingLot.services.ParkingService;
import parkingLot.trackers.VehicleTracker;
import parkingLot.strategies.NearestSpotStrategy;

// factory/ParkingSystemFactory.java
public class ParkingSystemFactory {
    private static ParkingSystemFactory instance;
    private final ParkingLotManager parkingLotManager;
    private final VehicleTracker vehicleTracker;
    private final ParkingService parkingService;

    private ParkingSystemFactory(String[][][] parkingLayout) {
        NearestSpotStrategy strategy = new NearestSpotStrategy();
        this.parkingLotManager = new ParkingLotManager(parkingLayout, strategy);
        this.vehicleTracker = new VehicleTracker();
        this.parkingService = new ParkingService(parkingLotManager, vehicleTracker);
    }

    public static synchronized ParkingSystemFactory getInstance(String[][][] parkingLayout) {
        if (instance == null) {
            instance = new ParkingSystemFactory(parkingLayout);
        }
        return instance;
    }

    public ParkingService getParkingService() {
        return parkingService;
    }
}