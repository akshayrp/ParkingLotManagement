package parkingLot;

import java.time.LocalDateTime;
import java.util.Objects;

public class Slot {
    private int slotNumber;
    private Object vehicle;
    private LocalDateTime parkingTime;

    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Slot(Object vehicle) {
        this.vehicle = vehicle;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public Object getVehicle() {
        return vehicle;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    public void setVehicleAndTime(Object vehicle) {
        this.vehicle = vehicle;
        this.parkingTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Slot slot = (Slot) that;
        return Objects.equals(vehicle, slot.vehicle);
    }
}