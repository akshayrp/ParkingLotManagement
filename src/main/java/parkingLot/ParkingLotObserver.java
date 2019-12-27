package parkingLot;

public interface ParkingLotObserver {
    void informWhenLotFull();

    void informWhenLotAvailableAgain();
}
