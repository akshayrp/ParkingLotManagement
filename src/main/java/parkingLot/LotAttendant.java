package parkingLot;

import java.util.ArrayList;
import java.util.List;

public class LotAttendant {

    private final int actualCapacity;
    private final InformObservers informer;
    private int currentCapacity;
    List parkingLot;


    public LotAttendant(int capacity) {
        this.parkingLot = new ArrayList();
        this.actualCapacity = capacity;
        this.informer = new InformObservers();
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (currentCapacity == actualCapacity) {
            this.informer.informParkingIsFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
        }
        parkingLot.add(vehicle);
        currentCapacity++;
    }

    public void unParkVehicle(Object vehicle) {
        if (parkingLot.contains(vehicle))
            parkingLot.remove(vehicle);
        this.informer.informWhenLotAvailableAgain();
    }
}
