package parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        int emptySlot = IntStream.range(0, slots.size()).filter(vehicles
                -> slots.get(vehicles).getVehicle() == null).findFirst().getAsInt();
        slots.get(emptySlot).setVehicleAndTime(vehicle);
        this.vehicleCount++;
    }

    public void parkVehicle(Object vehicle, int slotNumber) throws ParkingLotException {
        this.checkForFullParkingLot();
        slots.get(slotNumber).setVehicleAndTime(vehicle);
        vehicleCount++;
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
        return getSlot(vehicle).getSlotNumber();
    }

    public boolean isVehiclePresent(Object vehicle){
        try {
            return getSlot(vehicle).getVehicle().equals(vehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNumberOfVehiclesParked() {
        return this.vehicleCount;
    }

    private void checkForFullParkingLot() throws ParkingLotException {
         if (vehicleCount == actualCapacity) {
            this.informer.informParkingIsFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
        }
    }

    private Slot getSlot(Object vehicle) throws ParkingLotException {
        Slot vehicleSlot = slots.stream()
                .filter(slot -> slot.getVehicle() != null && slot.getVehicle().equals(vehicle))
                .findFirst()
                .orElseThrow(() -> new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "vehicle not found"));
        return vehicleSlot;
    }
}
