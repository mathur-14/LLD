package parkingLot;

import parkingLot.factory.ParkingSystemFactory;
import parkingLot.services.ParkingService;
import parkingLot.utility.Helper;

// Solution.java
public class Solution {
    private ParkingService parkingService;
        private Helper helper;
    
        public Solution() {
            this.parkingService = null;
            this.helper = null;
        }
    
        public void init(Helper helper, String[][][] parking) {
            this.helper = helper;
            ParkingSystemFactory factory = ParkingSystemFactory.getInstance(parking);
            this.parkingService = factory.getParkingService();
        this.helper.println("Parking system initialized successfully");
    }

    public ParkingResult park(int vehicleType, String vehicleNumber, String ticketId) {
        if (!Helper.isValidVehicleType(vehicleType)) {
            helper.logError("Invalid vehicle type: " + vehicleType);
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }
        
        if (!Helper.isValidVehicleNumber(vehicleNumber)) {
            helper.logError("Invalid vehicle number");
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }
        
        if (!Helper.isValidTicketId(ticketId)) {
            helper.logError("Invalid ticket ID");
            return new ParkingResult(404, "", vehicleNumber, ticketId);
        }

        ParkingResult result = parkingService.parkVehicle(vehicleType, vehicleNumber, ticketId);
        if (result.getStatus() == 201) {
            helper.println("Vehicle parked successfully at spot: " + result.getSpotId());
        } else {
            helper.logError("Failed to park vehicle");
        }
        return result;
    }

    public int removeVehicle(String spotId, String vehicleNumber, String ticketId) {
        if (spotId.isEmpty() && vehicleNumber.isEmpty() && ticketId.isEmpty()) {
            helper.logError("At least one of spotId, vehicleNumber, or ticketId must be provided");
            return 404;
        }

        int result = parkingService.removeVehicle(spotId, vehicleNumber, ticketId);
        if (result == 201) {
            helper.println("Vehicle removed successfully");
        } else {
            helper.logError("Failed to remove vehicle");
        }
        return result;
    }

    public ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId) {
        if (spotId.isEmpty() && vehicleNumber.isEmpty() && ticketId.isEmpty()) {
            helper.logError("At least one of spotId, vehicleNumber, or ticketId must be provided");
            return new ParkingResult(404, "", "", "");
        }

        return parkingService.searchVehicle(spotId, vehicleNumber, ticketId);
    }

    public int getFreeSpotsCount(int floor, int vehicleType) {
        if (!Helper.isValidVehicleType(vehicleType)) {
            helper.logError("Invalid vehicle type: " + vehicleType);
            return 0;
        }
        return parkingService.getFreeSpotsCount(floor, vehicleType);
    }
}