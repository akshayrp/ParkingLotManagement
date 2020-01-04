package parkingLot;

import java.time.LocalDateTime;
import java.util.Objects;

public class Slot {
    private int slotNumber;
    private Vehicle vehicle;
    private LocalDateTime parkingTime;

    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    public void setVehicleAndTime(Vehicle vehicle, LocalDateTime parkingTime) {
        this.vehicle = vehicle;
        this.parkingTime = parkingTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Slot slot = (Slot) that;
        return Objects.equals(vehicle, slot.vehicle);
    }
}
