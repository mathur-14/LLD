package parkingLot;

import parkingLot.utility.Helper;

public class ParkingLotDemo {
    public static void main(String[] args) {
        // Initialize parking layout with 3 floors
        String[][][] parkingLayout = {
            // Floor 0
            {
                {"4-1", "4-1", "2-1", "2-1", "4-1"},
                {"2-1", "4-0", "2-1", "4-1", "2-1"},
                {"4-1", "2-1", "4-1", "2-0", "4-1"}
            },
            // Floor 1
            {
                {"2-1", "2-1", "4-1", "4-1", "2-1"},
                {"4-1", "4-1", "2-1", "2-1", "4-1"},
                {"2-1", "4-1", "4-0", "4-1", "2-1"}
            },
            // Floor 2
            {
                {"4-1", "4-1", "2-1", "2-1", "4-1"},
                {"2-1", "2-1", "4-1", "4-1", "2-1"},
                {"4-1", "4-1", "2-1", "2-1", "4-0"}
            }
        };

        Solution parkingLot = new Solution();
        Helper helper = new Helper();
        parkingLot.init(helper, parkingLayout);

        // Test Case 1: Park multiple vehicles
        helper.println("\n=== Test Case 1: Parking Multiple Vehicles ===");
        ParkingResult[] parkingResults = new ParkingResult[6];
        parkingResults[0] = parkingLot.park(4, "KA01HH1234", "TKT001"); // 4-wheeler
        parkingResults[1] = parkingLot.park(2, "KA02MM5678", "TKT002"); // 2-wheeler
        parkingResults[2] = parkingLot.park(4, "KA03HH9012", "TKT003"); // 4-wheeler
        parkingResults[3] = parkingLot.park(2, "KA04MM3456", "TKT004"); // 2-wheeler
        parkingResults[4] = parkingLot.park(4, "KA05HH7890", "TKT005"); // 4-wheeler
        parkingResults[5] = parkingLot.park(2, "KA06MM1234", "TKT006"); // 2-wheeler

        for (ParkingResult result : parkingResults) {
            helper.println(String.format("Parked vehicle %s at spot %s with status %d", 
                result.getVehicleNumber(), result.getSpotId(), result.getStatus()));
        }

        // Test Case 2: Try to park already parked vehicle
        helper.println("\n=== Test Case 2: Parking Duplicate Vehicle ===");
        ParkingResult duplicateResult = parkingLot.park(4, "KA01HH1234", "TKT007");
        helper.println("Trying to park already parked vehicle: Status " + duplicateResult.getStatus());

        // Test Case 3: Search vehicles using different criteria
        helper.println("\n=== Test Case 3: Searching Vehicles ===");
        // Search by vehicle number
        ParkingResult searchByVehicle = parkingLot.searchVehicle("", "KA01HH1234", "");
        helper.println("Search by vehicle number: Found at " + searchByVehicle.getSpotId());
        
        // Search by ticket
        ParkingResult searchByTicket = parkingLot.searchVehicle("", "", "TKT003");
        helper.println("Search by ticket: Found at " + searchByTicket.getSpotId());
        
        // Search by spot
        ParkingResult searchBySpot = parkingLot.searchVehicle(parkingResults[0].getSpotId(), "", "");
        helper.println("Search by spot: Found vehicle " + searchBySpot.getVehicleNumber());

        // Test Case 4: Check free spots count
        helper.println("\n=== Test Case 4: Checking Free Spots ===");
        for (int floor = 0; floor < 3; floor++) {
            int twoWheelerSpots = parkingLot.getFreeSpotsCount(floor, 2);
            int fourWheelerSpots = parkingLot.getFreeSpotsCount(floor, 4);
            helper.println(String.format("Floor %d - 2W: %d spots, 4W: %d spots", 
                floor, twoWheelerSpots, fourWheelerSpots));
        }

        // Test Case 5: Remove vehicles using different criteria
        helper.println("\n=== Test Case 5: Removing Vehicles ===");
        // Remove by spot ID
        int removeBySpot = parkingLot.removeVehicle(parkingResults[0].getSpotId(), "", "");
        helper.println("Remove by spot ID: Status " + removeBySpot);
        
        // Remove by vehicle number
        int removeByVehicle = parkingLot.removeVehicle("", "KA02MM5678", "");
        helper.println("Remove by vehicle number: Status " + removeByVehicle);
        
        // Remove by ticket
        int removeByTicket = parkingLot.removeVehicle("", "", "TKT003");
        helper.println("Remove by ticket: Status " + removeByTicket);

        // Test Case 6: Try to remove already removed vehicle
        helper.println("\n=== Test Case 6: Removing Non-existent Vehicle ===");
        int removeNonExistent = parkingLot.removeVehicle("", "KA01HH1234", "");
        helper.println("Remove non-existent vehicle: Status " + removeNonExistent);

        // Test Case 7: Park vehicles after removing some
        helper.println("\n=== Test Case 7: Parking After Removal ===");
        ParkingResult newPark1 = parkingLot.park(4, "KA07HH4321", "TKT008");
        ParkingResult newPark2 = parkingLot.park(2, "KA08MM8765", "TKT009");
        helper.println(String.format("New vehicle parked at %s with status %d", 
            newPark1.getSpotId(), newPark1.getStatus()));
        helper.println(String.format("New vehicle parked at %s with status %d", 
            newPark2.getSpotId(), newPark2.getStatus()));

        // Test Case 8: Check final state
        helper.println("\n=== Test Case 8: Final State Check ===");
        for (int floor = 0; floor < 3; floor++) {
            int twoWheelerSpots = parkingLot.getFreeSpotsCount(floor, 2);
            int fourWheelerSpots = parkingLot.getFreeSpotsCount(floor, 4);
            helper.println(String.format("Floor %d - 2W: %d spots, 4W: %d spots", 
                floor, twoWheelerSpots, fourWheelerSpots));
        }
    }
}