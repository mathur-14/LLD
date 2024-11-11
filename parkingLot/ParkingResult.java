package parkingLot;

public class ParkingResult {
    private final int status;
    private final String spotId;
    private final String vehicleNumber;
    private final String ticketId;

    public ParkingResult(int status, String spotId, String vehicleNumber, String ticketId) {
        this.status = status;
        this.spotId = spotId;
        this.vehicleNumber = vehicleNumber;
        this.ticketId = ticketId;
    }

    // Getters
    public int getStatus() { return status; }
    public String getSpotId() { return spotId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getTicketId() { return ticketId; }
}