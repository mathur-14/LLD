package parkingLot.models;
public class Vehicle {
    private final String vehicleNumber;
    private final int type;
    private final String ticketId;

    public Vehicle(String vehicleNumber, int type, String ticketId) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.ticketId = ticketId;
    }

    // Getters
    public String getVehicleNumber() { return vehicleNumber; }
    public int getType() { return type; }
    public String getTicketId() { return ticketId; }
}