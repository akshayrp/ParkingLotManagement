package parkingLot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingStrategy {

    public ParkingStrategy() {
    }

    public ParkingLot getLotForHandicapDriver() throws ParkingLotException {
        //tempLots.sort(Comparator.comparing( lot-> lot.slots.forEach(slot -> slot.getVehicle().equals(null))) );
       return ParkingLotSystem.parkingLotsList.stream().filter(parkingLot -> parkingLot.getEmptySlots().size() > 0)
               .findFirst().orElseThrow(() -> new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_CAPACITY_FULL,""));
    }

    public ParkingLot getLotForNormalDriver() {
        List<ParkingLot> tempLots = new ArrayList<>(ParkingLotSystem.parkingLotsList);
        tempLots.sort(Comparator.comparing(lot->lot.getNumberOfVehiclesParked()));
        return tempLots.get(0);
    }
}
