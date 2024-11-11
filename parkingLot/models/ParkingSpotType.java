package parkingLot.models;

public enum ParkingSpotType {
    TWO_WHEELER(2),
    FOUR_WHEELER(4);

    private final int value;

    ParkingSpotType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ParkingSpotType fromValue(int value) {
        for (ParkingSpotType type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid parking spot type: " + value);
    }
}