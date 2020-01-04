package parkingLot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParkingLot {

    private final int actualCapacity;
    private InformObservers informer;
    private int vehicleCount = 0;
    public List<Slot> slots;

    public ParkingLot(int slotCapacity) {
        this.slots = new ArrayList<>();
        this.actualCapacity = slotCapacity;
        this.informer = new InformObservers();
        this.slots = new ArrayList<>();
        initialiseList();
    }

    private void initialiseList() {
        IntStream.range(0, actualCapacity).forEach(slotNumber -> this.slots.add(slotNumber, new Slot(slotNumber)));
    }

  /*  public void setMockedObject(ArrayList<Slot> slotList, InformObservers informObservers) {
        this.informer = informObservers;
        this.slots = slotList;
    }*/

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        this.checkForFullParkingLot();
        int emptySlot = IntStream.range(0, slots.size()).filter(vehicles
                -> slots.get(vehicles).getVehicle() == null).findFirst().getAsInt();
        slots.get(emptySlot).setVehicleAndTime(vehicle,LocalDateTime.now());
        this.vehicleCount++;
    }

    public void parkVehicle(Object vehicle, int slotNumber) throws ParkingLotException {
        this.checkForFullParkingLot();
        slots.get(slotNumber).setVehicleAndTime(vehicle, LocalDateTime.now());
        vehicleCount++;
    }

    public void unParkVehicle(Object vehicle) {
        slots.stream().filter(slot -> slot.getVehicle().equals(vehicle)).findFirst().ifPresent(slot -> slot.setVehicleAndTime(null,null));
        this.informer.informWhenLotAvailableAgain();
        vehicleCount--;
    }

    public List getEmptySlots() {
        List emptySlots = new ArrayList();
        IntStream.range(0, slots.size()).filter(slot -> this.slots.get(slot).getVehicle() == null).forEach(slots -> {
            emptySlots.add(slots);
        });
        return emptySlots;
    }

    public int findSlotOfParkedVehicle(Object vehicle) {
        return getSlot(vehicle).findFirst().get().getSlotNumber();
    }

    public boolean isVehiclePresent(Object vehicle){
        return getSlot(vehicle).findFirst().isPresent();
    }

    public int getNumberOfVehiclesParked() {
        return this.vehicleCount;
    }

    public int getNumberOfEmptySlots() {
        return this.actualCapacity - vehicleCount;
    }

    private void checkForFullParkingLot() throws ParkingLotException {
         if (vehicleCount == actualCapacity) {
            this.informer.informParkingIsFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
        }
    }

    private Stream<Slot> getSlot(Object vehicle) {
        Stream<Slot> vehicleSlot = slots.stream()
                .filter(slot -> slot.getVehicle() != null && slot.getVehicle().equals(vehicle));
        return vehicleSlot;
    }
}
