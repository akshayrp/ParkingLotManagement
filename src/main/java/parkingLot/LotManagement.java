package parkingLot;

import java.util.ArrayList;
import java.util.List;

public class LotManagement {

    private final int actualCapacity;
    private int currentCapacity;
    List parkingLot;
    public List<ParkingLotObserver> lotObservers;

    public LotManagement(int capacity) {
        this.parkingLot = new ArrayList();
        this.actualCapacity = capacity;
        this.lotObservers = new ArrayList<>();
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        if (currentCapacity == actualCapacity) {
            for (ParkingLotObserver observer:
                    lotObservers) {
                observer.informWhenLotFull();
            }
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
        }
        parkingLot.add(vehicle);
        currentCapacity++;
    }

    public void unParkVehicle(Object vehicle) {
        if (parkingLot.contains(vehicle))
            parkingLot.remove(vehicle);
        for (ParkingLotObserver observer:
                lotObservers) {
            observer.informWhenLotAvailableAgain();
        }
    }
}
