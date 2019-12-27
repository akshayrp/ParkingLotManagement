package parkingLot;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {

    private final int actualCapacity;
    private int currentCapacity;
    List parkingLot;
    public Owner owner;
    public AirportSecurity security;

    public ParkingLotSystem(int capacity) {
        this.parkingLot = new ArrayList();
        this.actualCapacity = capacity;
        this.owner = new Owner();
        this.security = new AirportSecurity();
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (currentCapacity == actualCapacity) {
            owner.informWhenLotFull();
            security.informWhenLotFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
        }
        parkingLot.add(vehicle);
       currentCapacity++;
    }

    public void unParkVehicle(Object vehicle) {
        if(parkingLot.contains(vehicle))
            parkingLot.remove(vehicle);
        }


    public boolean isVehicleParked(Object vehicle) throws ParkingLotException {
        if(parkingLot.contains(vehicle))
            return true;
        throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_PARKING_EXCEPTION,"parked vehicle is not same as given vehicle or vehicle not parked");
    }

    public boolean isVehicleUnParked(Object vehicle) throws ParkingLotException {
        if(parkingLot.contains(vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_UNPARKING_EXCEPTION, "vehicle not UnParked");
        return true;
    }
}
