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

    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    public boolean isVehicleUnParked() {
        if (this.vehicle == null)
            return true;
        return false;
    }
}
