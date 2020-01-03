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

    public void parkVehicle(DriverType driverType,Object vehicle) throws ParkingLotException {
        this.parkingLot = assignLot.getLot(driverType);
        //this.parkingLot = strategy.getLot();
        this.parkingLot.parkVehicle(vehicle);
    }

    public void unParkVehicle(Object vehicle) {
        parkingLot.unParkVehicle(vehicle);
    }


    public ParkingLot getParkedVehicleLot(Object vehicle) {
        ParkingLot parkingLotWithParkedVehicle = parkingLotsList.stream()
                .filter(parkingLot -> parkingLot.isVehiclePresent(vehicle))
                .findFirst()
                .orElse(null);
        return parkingLotWithParkedVehicle;
    }
}
