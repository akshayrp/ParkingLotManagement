package parkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParkingStrategy {

    public ParkingLot getLot() {
        List<ParkingLot> tempLots = new ArrayList<>(ParkingLotSystem.parkingLotsList);
        tempLots.sort(Comparator.comparing(lot->lot.getNumberOfVehiclesParked()));
        return tempLots.get(0);
    }
}
