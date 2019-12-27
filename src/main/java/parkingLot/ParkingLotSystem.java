package parkingLot;

public class ParkingLotSystem {

    VehicleAvailabilityCheck availabilityCheck;
    LotManagement lotManagement;

    public ParkingLotSystem(int capacity) {
        this.availabilityCheck = new VehicleAvailabilityCheck();
        this.lotManagement = new LotManagement(capacity);
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        lotManagement.parkVehicle(vehicle);
    }

    public void unParkVehicle(Object vehicle) {
        lotManagement.unParkVehicle(vehicle);
    }

    public boolean isVehicleParked(Object vehicle) throws ParkingLotException {
        return availabilityCheck.isVehicleParked(vehicle, lotManagement.parkingLot);
    }

    public boolean isVehicleUnParked(Object vehicle) throws ParkingLotException {
        return availabilityCheck.isVehicleUnParked(vehicle, lotManagement.parkingLot);
    }
}
