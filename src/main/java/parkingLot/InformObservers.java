package parkingLot;

import java.util.ArrayList;
import java.util.List;

public class InformObservers {
    LotObserver observer = new LotObserver();
    public List<ParkingLotObserver> lotObservers;

    public InformObservers() {
        this.lotObservers = new ArrayList<>();
        lotObservers.add(observer);
    }

    public void informParkingIsFull() {
        lotObservers.forEach(ParkingLotObserver::informWhenLotFull);
    }

    public void informWhenLotAvailableAgain() {
        lotObservers.forEach(ParkingLotObserver::informWhenLotAvailableAgain);
    }
}
