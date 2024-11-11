package parkingLot.models;

public class ParkingSpot {
    private final String spotId;
    private final int type;
    private volatile boolean occupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(int floor, int row, int col, int type) {
        this.spotId = String.format("%d-%d-%d", floor, row, col);
        this.type = type;
        this.occupied = false;
    }

    public synchronized boolean occupy(Vehicle vehicle) {
        if (occupied || vehicle.getType() != type) return false;
        this.parkedVehicle = vehicle;
        this.occupied = true;
        return true;
    }

    public synchronized boolean vacate() {
        if (!occupied) return false;
        this.parkedVehicle = null;
        this.occupied = false;
        return true;
    }

    // Getters
    public boolean isOccupied() { return occupied; }
    public String getSpotId() { return spotId; }
    public int getType() { return type; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
}