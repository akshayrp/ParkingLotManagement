package parkingLot;

public class AirportSecurity implements ParkingLotObserver{
    public boolean isFull = false;

    public void informWhenLotFull() {
        this.isFull = true;
    }
}
