package parkingLot;

import java.util.List;

public class VehicleAvailabilityCheck {

    public boolean isVehicleParked(Object vehicle, List parkingLot) throws ParkingLotException {
        if (parkingLot.contains(vehicle))
            return true;
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_PARKING_EXCEPTION, "parked vehicle is not same as given vehicle or vehicle not parked");
    }

    public boolean isVehicleUnParked(Object vehicle, List parkingLot) throws ParkingLotException {
        if (parkingLot.contains(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_UNPARKING_EXCEPTION, "vehicle not UnParked");
        return true;
    }
}
