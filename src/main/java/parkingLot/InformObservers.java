package parkingLot;

import java.util.ArrayList;
import java.util.List;

public class InformObservers {
    public List<ParkingLotObserver> lotObservers;

    public InformObservers() {
        this.lotObservers = new ArrayList<>();
    }

    public void informParkingIsFull() {
        lotObservers.forEach(ParkingLotObserver::informWhenLotFull);
    }

    public void informWhenLotAvailableAgain() {
        lotObservers.forEach(ParkingLotObserver::informWhenLotAvailableAgain);
    }
}
