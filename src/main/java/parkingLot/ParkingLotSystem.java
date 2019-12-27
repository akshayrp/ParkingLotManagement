package parkingLot;

public class ParkingLotSystem {

    private Object vehicle;

    public void parkVehicle(Object vehicle) {
        this.vehicle = vehicle;
    }

    public void unParkVehicle(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
        }
    }

    public boolean isVehicleParked(Object vehicle) throws ParkingLotException {
        if (this.vehicle.equals(vehicle))
            return true;
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_PARKING_EXCEPTION,"parked vehicle is not same as given vehicle or vehicle not parked");
    }

    public boolean isVehicleUnParked() throws ParkingLotException {
        if (this.vehicle == null)
            return true;
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_UNPARKING_EXCEPTION, "vehicle not UnParked");
    }
}
