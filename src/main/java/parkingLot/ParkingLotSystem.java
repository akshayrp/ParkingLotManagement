package parkingLot;

public class ParkingLotSystem {

    VehicleAvailabilityCheck availabilityCheck;
    LotAttendant lotAttendant;

    public ParkingLotSystem(int capacity) {
        this.availabilityCheck = new VehicleAvailabilityCheck();
        this.lotAttendant = new LotAttendant(capacity);
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        lotAttendant.parkVehicle(vehicle);
    }

    public void unParkVehicle(Object vehicle) {
        lotAttendant.unParkVehicle(vehicle);
    }

    public boolean isVehicleParked(Object vehicle) throws ParkingLotException {
        return availabilityCheck.isVehicleParked(vehicle, lotAttendant.parkingLot);
    }

    public boolean isVehicleUnParked(Object vehicle) throws ParkingLotException {
        return availabilityCheck.isVehicleUnParked(vehicle, lotAttendant.parkingLot);
    }
}
