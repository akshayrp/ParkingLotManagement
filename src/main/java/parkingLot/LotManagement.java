package parkingLot;

import java.util.ArrayList;
import java.util.List;

public class LotManagement {

    private final int actualCapacity;
    private int currentCapacity;
    List parkingLot;
    public Owner owner;
    public AirportSecurity security;

    public LotManagement(int capacity) {
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
        if (parkingLot.contains(vehicle))
            parkingLot.remove(vehicle);
    }
}
