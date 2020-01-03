package parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLotSystem {
    public static List<ParkingLot> parkingLotsList;
    public ParkingLot parkingLot;
    public ParkingStrategy strategy;

    public ParkingLotSystem() {
        this.parkingLotsList = new ArrayList<>();
        this.strategy = new ParkingStrategy();
    }

    public void setMockObject(ParkingStrategy strategy,ParkingLot parkingLot) {
        this.strategy = strategy;
        this.parkingLot = parkingLot;
    }

    public void createParkingLot(int slotCapacity, int numberOfLots) {
        IntStream.range(0, numberOfLots).forEach(lot
                -> this.parkingLotsList.add(new ParkingLot(slotCapacity)));
    }

    public void parkVehicle(Object vehicle,int slotNumber) throws ParkingLotException {
        parkingLot.parkVehicle(vehicle,slotNumber);
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        this.parkingLot = strategy.getLot();
        this.parkingLot.parkVehicle(vehicle);
    }

    public void unParkVehicle(Object vehicle) {
        parkingLot.unParkVehicle(vehicle);
    }


    public ParkingLot getParkedVehicleLot(Object vehicle) {
        ParkingLot parkingLotWithParkedVehicle = parkingLotsList.stream().filter(parkingLot -> parkingLot.isVehiclePresent(vehicle)).findFirst().orElse(null);
        return parkingLotWithParkedVehicle;
    }
}
