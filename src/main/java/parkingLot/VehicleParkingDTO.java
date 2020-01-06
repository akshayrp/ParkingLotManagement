package parkingLot;

public class VehicleParkingDTO {
    public int slotNumber;
    public String numberPlate;
    public String attendantName;

    public VehicleParkingDTO(int slotNumber, String numberPlate, String attendantName) {
        this.slotNumber = slotNumber;
        this.numberPlate = numberPlate;
        this.attendantName = attendantName;
    }

    public VehicleParkingDTO(Slot slot) {
        this.slotNumber = slot.getSlotNumber();
        this.numberPlate = slot.getVehicle().getPlateNumber();
        this.attendantName = slot.getVehicle().getAttendantName();
    }

    @Override
    public String toString() {
        return "VehicleParkingDTO{" +
                "slotNumber=" + slotNumber +
                ", numberPlate='" + numberPlate + '\'' +
                ", attendantName='" + attendantName + '\'' +
                '}';
    }
}
