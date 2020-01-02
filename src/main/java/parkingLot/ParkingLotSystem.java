package parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLotSystem {

    private final int actualCapacity;
    private final InformObservers informer;
    private int currentCapacity;
    public List parkingLot;

    public ParkingLotSystem(int capacity) {
        this.parkingLot = new ArrayList();

        this.actualCapacity = capacity;
        this.informer = new InformObservers();
        initialiseList();
    }

    private void initialiseList() {
        IntStream.range(0, actualCapacity).forEach(slots -> parkingLot.add(slots, null));
    }

    public void parkVehicle(Object vehicle, Integer slot) throws ParkingLotException {
        if (currentCapacity == actualCapacity) {
            this.informer.informParkingIsFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
        }
        parkingLot.set(slot, vehicle);
        currentCapacity++;
    }

    public void unParkVehicle(Object vehicle) {
        if (parkingLot.contains(vehicle))
            parkingLot.remove(vehicle);
        this.informer.informWhenLotAvailableAgain();
    }

    public List getEmptySlots() {
        List emptySlots = new ArrayList();
        IntStream.range(0, parkingLot.size()).filter(slots -> parkingLot.get(slots) == null).forEach(slots -> {
            emptySlots.add(slots);
        });
        return emptySlots;
    }

}
