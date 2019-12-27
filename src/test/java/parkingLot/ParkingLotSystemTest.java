package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem;
    Object vehicle;

    @Before
    public void setUp() {
        this.parkingLotSystem = new ParkingLotSystem();
        this.vehicle = new Object();

    }

    @Test
    public void givenVehicleWhenParked_ShouldReturnTrue() {

        boolean isParked = parkingLotSystem.parkVehicle(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assert.assertTrue(isUnParked);
    }
}
