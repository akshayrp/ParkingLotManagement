package parkingLot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParkingLot {

    private final int actualCapacity;
    private final int parkingLotNumber;
    private InformObservers informer;
    private int vehicleCount = 0;
    public List<Slot> slots;

    public ParkingLot(int parkingLotNumber,int slotCapacity) {
        this.parkingLotNumber = parkingLotNumber;
        this.slots = new ArrayList<>();
        this.actualCapacity = slotCapacity;
        this.informer = new InformObservers();
        this.slots = new ArrayList<>();
        initialiseList();
    }

    private void initialiseList() {
        IntStream.range(0, actualCapacity).forEach(slotNumber -> this.slots.add(slotNumber, new Slot(slotNumber)));
    }

    public void parkVehicle(Vehicle vehicle) throws ParkingLotException {
        this.checkForFullParkingLot(vehicle);
        int emptySlot = IntStream.range(0, slots.size()).filter(vehicles
                -> slots.get(vehicles).getVehicle() == null).findFirst().getAsInt();
        slots.get(emptySlot).setVehicleAndTime(vehicle,LocalDateTime.now());
        this.vehicleCount++;
    }

    public void parkVehicle(Vehicle vehicle, int slotNumber) throws ParkingLotException {
        this.checkForFullParkingLot(vehicle);
        slots.get(slotNumber).setVehicleAndTime(vehicle, LocalDateTime.now());
        vehicleCount++;
    }

    public void unParkVehicle(Vehicle vehicle) {
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

    public int findSlotOfParkedVehicle(Vehicle vehicle) {
        return getSlot(vehicle).findFirst().get().getSlotNumber();
    }

    public boolean isVehiclePresent(Vehicle vehicle){
        return getSlot(vehicle).findFirst().isPresent();
    }

    public int getNumberOfVehiclesParked() {
        return this.vehicleCount;
    }

    public int getNumberOfEmptySlots() {
        return this.actualCapacity - vehicleCount;
    }

    private void checkForFullParkingLot(Vehicle vehicle) throws ParkingLotException {
         if (vehicleCount == actualCapacity) {
            this.informer.informParkingIsFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL, "cannot park more vehicles");
         }
    }

    private Stream<Slot> getSlot(Vehicle vehicle) {
        Stream<Slot> vehicleSlot = slots.stream()
                .filter(slot -> slot.getVehicle() != null && slot.getVehicle().equals(vehicle));
        return vehicleSlot;
    }

    public ArrayList<String> getLocationByColorAndModel(String findByColor, String findByVehicleModel) {
        ArrayList<String> collect = getLocationOfVehicle(findByColor)
                .filter(slot -> slot.getVehicle().getModel().equalsIgnoreCase(findByVehicleModel))
                .map(slot -> new VehicleParkingDTO(slot).toString())
                .collect(Collectors.toCollection(ArrayList::new));
        return collect;
    }

    public ArrayList<Integer> getLocationByColor(String findByColor) {
        ArrayList<Integer> collect = getLocationOfVehicle(findByColor)
                .map(slot -> slot.getSlotNumber()).collect(Collectors.toCollection(ArrayList::new));
        return collect;
    }

    public ArrayList<Vehicle> getVehicleByModel(String findByVehicleModel) {
        ArrayList<Vehicle> sortedVehicle = slots.stream().filter(slot -> slot.getVehicle() != null
                && slot.getVehicle().getModel().equalsIgnoreCase(findByVehicleModel))
                .map(slot -> slot.getVehicle()).collect(Collectors.toCollection(ArrayList::new));
        return sortedVehicle;
    }

    private Stream<Slot> getLocationOfVehicle(String findByColor) {
        Stream<Slot> slotStream = slots.stream().filter(slot -> slot.getVehicle() != null)
                .filter(slot -> slot.getVehicle().getColor().equalsIgnoreCase(findByColor));
        return slotStream;
    }

    public ArrayList<Vehicle> getVehicleByTime(int minutes) {
        return slots.stream().filter(slot -> slot.getVehicle() != null).filter(slot -> slot.getParkingTime().getMinute() <= minutes
                && slot.getParkingTime().getMinute() > 0).map(slot -> slot.getVehicle())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
