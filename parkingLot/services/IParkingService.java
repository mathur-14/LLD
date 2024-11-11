package parkingLot.services;
import parkingLot.*;

public interface IParkingService {
    ParkingResult parkVehicle(int vehicleType, String vehicleNumber, String ticketId);
    int removeVehicle(String spotId, String vehicleNumber, String ticketId);
    ParkingResult searchVehicle(String spotId, String vehicleNumber, String ticketId);
    int getFreeSpotsCount(int floor, int vehicleType);
}