package parkingLot;

public class LotObserver implements ParkingLotObserver {
    public boolean isFull = false;

    public void informWhenLotFull() {
        this.isFull = true;
    }
    @Override
    public void informWhenLotAvailableAgain() {
        this.isFull = false;
    }
}
