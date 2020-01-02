package parkingLot;

import java.util.ArrayList;

public class LotObserver implements ParkingLotObserver {
    public boolean isFull = false;

    @Override
    public void informWhenLotFull() {
        this.isFull = true;
    }
    @Override
    public void informWhenLotAvailableAgain() {
        this.isFull = false;
    }
}
