package parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLotSystem {
    public static List<ParkingLot> parkingLotsList;
    public ParkingLot parkingLot;
    public AssignLot assignLot;

    public ParkingLotSystem() {
        this.parkingLotsList = new ArrayList<>();
        this.assignLot = new AssignLot();
    }

    public void setMockObject(AssignLot assignLot,ParkingLot parkingLot) {
        this.assignLot = assignLot;
        this.parkingLot = parkingLot;
    }

    public void createParkingLot(int slotCapacity, int numberOfLots) {
        IntStream.range(0, numberOfLots).forEach(lot
                -> this.parkingLotsList.add(new ParkingLot(slotCapacity)));
    }

    public void parkVehicle(Object vehicle,int slotNumber) throws ParkingLotException {
        parkingLot.parkVehicle(vehicle,slotNumber);
    }

    public void parkVehicle(Enum parkingStrategy,Object vehicle) throws ParkingLotException {
        this.parkingLot = assignLot.getLot(parkingStrategy);
        this.parkingLot.parkVehicle(vehicle);
    }

    public void unParkVehicle(Object vehicle) throws ParkingLotException {
        getParkedVehicleLot(vehicle).unParkVehicle(vehicle);
    }


    public ParkingLot getParkedVehicleLot(Object vehicle) throws ParkingLotException {
        return parkingLotsList.stream()
                .filter(parkingLot -> parkingLot.isVehiclePresent(vehicle)).findFirst().orElseThrow(() -> new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_NOT_FOUND, "No vehicle present"));
    }
}
