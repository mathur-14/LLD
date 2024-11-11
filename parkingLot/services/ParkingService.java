package parkingLot.services;
import parkingLot.*;
import parkingLot.managers.ParkingLotManager;
import parkingLot.models.ParkingSpot;
import parkingLot.models.Vehicle;
import parkingLot.trackers.VehicleTracker;

// service/ParkingService.java
public class ParkingService implements IParkingService {
    private final ParkingLotManager parkingLotManager;
    private final VehicleTracker vehicleTracker;

    public ParkingService(ParkingLotManager parkingLotManager, VehicleTracker vehicleTracker) {
        this.parkingLotManager = parkingLotManager;
        this.vehicleTracker = vehicleTracker;
    }

    @Override
    public synchronized ParkingResult parkVehicle(int vehicleType, String vehicleNumber, String ticketId) {
        // Check if vehicle is already parked
        if (vehicleTracker.isVehicleParked(vehicleNumber)) {
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }

        // Create vehicle object
        Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType, ticketId);
        
        // Find available spot
        ParkingSpot spot = parkingLotManager.findAvailableSpot(vehicleType);
        if (spot == null) {
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }

        // Park vehicle
        if (!spot.occupy(vehicle)) {
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }

        // Track vehicle
        vehicleTracker.trackVehicle(vehicle, spot);
        return new ParkingResult(201, spot.getSpotId(), vehicleNumber, ticketId);
    }

    @Override
    public synchronized int removeVehicle(String spotId, String vehicleNumber, String ticketId) {
        // Find the parking spot
        ParkingSpot spot = null;
        
        if (!spotId.isEmpty()) {
            spot = parkingLotManager.getSpot(spotId);
        } else if (!vehicleNumber.isEmpty()) {
            spot = vehicleTracker.findSpotByVehicleNumber(vehicleNumber);
        } else if (!ticketId.isEmpty()) {
            spot = vehicleTracker.findSpotByTicketId(ticketId);
        }

        if (spot == null) {
            return 404;
        }

        // Get vehicle before vacating the spot
        Vehicle vehicle = spot.getParkedVehicle();
        if (vehicle == null) {
            return 404;
        }

        // Vacate spot and untrack vehicle
        if (!spot.vacate()) {
            return 404;
        }

        vehicleTracker.untrackVehicle(vehicle, spot);
        return 201;
    }

    @Override
    public ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId) {
        ParkingSpot spot = null;

        // Search by spotId, vehicleNumber, or ticketId
        if (!spotId.isEmpty()) {
            spot = parkingLotManager.getSpot(spotId);
        } else if (!vehicleNumber.isEmpty()) {
            spot = vehicleTracker.findSpotByVehicleNumber(vehicleNumber);
        } else if (!ticketId.isEmpty()) {
            spot = vehicleTracker.findSpotByTicketId(ticketId);
        }

        if (spot == null) {
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }

        Vehicle vehicle = spot.getParkedVehicle();
        if (vehicle == null) {
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }

        return new ParkingResult(201, spot.getSpotId(), vehicle.getVehicleNumber(), vehicle.getTicketId());
    }

    @Override
    public int getFreeSpotsCount(int floor, int vehicleType) {
        return parkingLotManager.getFreeSpotsCount(floor, vehicleType);
    }
}