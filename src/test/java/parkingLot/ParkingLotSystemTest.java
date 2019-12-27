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
        parkingLotSystem.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.unParkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked();
        Assert.assertTrue(isUnParked);
    }
}
