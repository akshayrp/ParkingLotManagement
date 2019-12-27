package parkingLot;

public class ParkingLotException extends Exception {
    enum ExceptionType {VEHICLE_UNPARKING_EXCEPTION, VEHICLE_PARKING_EXCEPTION}

    ExceptionType type;

    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
