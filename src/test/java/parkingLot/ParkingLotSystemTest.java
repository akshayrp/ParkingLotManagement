package parkingLot;

import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;

    @Before
    public void setUp() throws Exception {
        this.parkingLotSystem = new ParkingLotSystem(2,2);
    }

    @Test
    public void givenVehicle_WhenParkedSystemShouldParkVehicleOnEvenlyDistributedSlots() {
        try {
            Object vehicle2 = new Object();
            Object vehicle1 = new Object();
            Object vehicle3 = new Object();
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
