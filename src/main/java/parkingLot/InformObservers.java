package parkingLot;

import java.util.ArrayList;
import java.util.List;

public class InformObservers {
    LotObserver observer = new LotObserver();
    public List<ParkingLotObserver> observersList;

    public InformObservers() {
        this.observersList = new ArrayList<>();
        this.observersList.add(observer);
    }
    public void informParkingIsFull() {
        for (ParkingLotObserver lotObserver : observersList) {
            lotObserver.informWhenLotFull();
        }
    }

    public void informWhenLotAvailableAgain() {
        for (ParkingLotObserver lotObserver : observersList) {
            lotObserver.informWhenLotAvailableAgain();
        }
    }

    public void setMockObject(LotObserver lotObserver, ArrayList observersList) {
        this.observer = lotObserver;
        this.observersList = observersList;
    }
}
