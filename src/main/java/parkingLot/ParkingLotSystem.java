package parkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLotSystem {
    private final int slotCapacity;
    private final int numberOfLots;
    public List<ParkingLot> parkingLotsList;
    ParkingLot parkingLot;

    public ParkingLotSystem(int slotCapacity,int numberOfLots) {
        this.slotCapacity = slotCapacity;
        this.parkingLotsList = new ArrayList<>();
        this.numberOfLots = numberOfLots;
        IntStream.range(0,numberOfLots).forEach(lot -> this.parkingLotsList.add(new ParkingLot(2)));
    }

    public void parkVehicle(Object vehicle,int slotNumber) throws ParkingLotException {
        parkingLot.parkVehicle(vehicle,slotNumber);
    }

    public void parkVehicle(Object vehicle) throws ParkingLotException {
        ParkingLot lot = this.getLot();
        lot.parkVehicle(vehicle);
    }

    private ParkingLot getLot() {
        List<ParkingLot> tempLots = new ArrayList<>(this.parkingLotsList);
        tempLots.sort(Comparator.comparing(lot->lot.getNumberOfVehiclesParked()));
        return tempLots.get(0);
    }
}
