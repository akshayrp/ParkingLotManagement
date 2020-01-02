package parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
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

    public void setMockedObject(ArrayList<Slot> slotList, InformObservers informObservers) {
        this.informer = informObservers;
        this.slots = slotList;
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        this.checkForFullParkingLot();
        int first = IntStream.range(0, slots.size()).filter(vehicles -> slots.get(vehicles).getVehicle() == null).findFirst().getAsInt();
        slots.get(first).setVehicleAndTime(vehicle);
        this.vehicleCount++;
    }

    public void unParkVehicle(Object vehicle) {
        slots.stream().filter(slot -> slot.getVehicle().equals(vehicle)).findFirst().ifPresent(slot -> slot.setVehicleAndTime(null));
        this.informer.informWhenLotAvailableAgain();
        this.vehicleCount--;
    }

    public List getEmptySlots() {
        List emptySlots = new ArrayList();
        IntStream.range(0, slots.size()).filter(slot -> this.slots.get(slot).getVehicle() == null).forEach(slots -> {
            emptySlots.add(slots);
        });
        return emptySlots;
    }

    public int findVehicle(Object vehicle) throws ParkingLotException {
        Stream<Slot> stream = slots.stream();
        int slotNumber = 0;
        slotNumber = stream.filter(slot -> slot.getVehicle() != null && slot.getVehicle().equals(vehicle)).findFirst()
                .orElseThrow(() -> new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "vehicle not found"))
                .getSlotNumber();
        return slotNumber;
    }

    public boolean isVehiclePresent(Object vehicle) {
        Slot tempSlot = new Slot(vehicle);
        if (this.slots.contains(tempSlot)) {
            return true;
        }
        return false;
        //return slots.stream().filter(slot -> slot.getVehicle().equals(vehicle)).findFirst().get().equals(vehicle);
    }

    public int getNumberOfVehiclesParked() {
        return this.vehicleCount;
    }

    public void parkVehicle(Object vehicle, int slotNumber) throws ParkingLotException {
        this.checkForFullParkingLot();
        slots.get(slotNumber).setVehicleAndTime(vehicle);
        vehicleCount++;
    }

    private void checkForFullParkingLot() throws ParkingLotException {
         if (vehicleCount == actualCapacity) {
            this.informer.informParkingIsFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
        }
    }
}
