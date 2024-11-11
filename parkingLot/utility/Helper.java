package parkingLot.utility;

public class Helper {
    private static final Helper instance = new Helper();
    
    public Helper() {}
    
    public static Helper getInstance() {
        return instance;
    }
    
    public void print(String message) {
        System.out.print(message);
    }
    
    public void println(String message) {
        System.out.println(message);
    }
    
    public void logError(String message) {
        System.err.println("ERROR: " + message);
    }
    
    public void logInfo(String message) {
        System.out.println("INFO: " + message);
    }
    
    // Utility methods for parking lot
    public static boolean isValidSpotId(String spotId) {
        if (spotId == null || spotId.isEmpty()) return false;
        String[] parts = spotId.split("-");
        return parts.length == 3 && 
               isNumeric(parts[0]) && 
               isNumeric(parts[1]) && 
               isNumeric(parts[2]);
    }
    
    public static boolean isValidVehicleNumber(String vehicleNumber) {
        return vehicleNumber != null && !vehicleNumber.trim().isEmpty();
    }
    
    public static boolean isValidTicketId(String ticketId) {
        return ticketId != null && !ticketId.trim().isEmpty();
    }
    
    public static boolean isValidVehicleType(int type) {
        return type == 2 || type == 4;
    }
    
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}