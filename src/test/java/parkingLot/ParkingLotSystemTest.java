package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem;
    Object vehicle;
    Object vehicle1;

    @Before
    public void setUp() {
        this.parkingLotSystem = new ParkingLotSystem(2);
        this.vehicle = new Object();
        this.vehicle1 = new Object();
    }

    @Test
    public void givenVehicleWhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle, 1);
            Assert.assertTrue(parkingLotSystem.parkingLot.contains(vehicle));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhichIsNotParked_AndTriedToCheckIfParked_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle, 0);
            parkingLotSystem.parkVehicle(vehicle1,1 );
            Assert.assertFalse(parkingLotSystem.parkingLot.contains(new Object()));
        } catch (ParkingLotException e) {
            Assert.assertEquals("parked vehicle is not same as given vehicle or vehicle not parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle, 0);
            parkingLotSystem.unParkVehicle(vehicle);
            Assert.assertFalse(parkingLotSystem.parkingLot.contains(vehicle));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhenParkingLotFull_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle,0);
            parkingLotSystem.parkVehicle(vehicle1, 1);
            parkingLotSystem.parkVehicle(vehicle1, 2);
        } catch (ParkingLotException e) {
            Assert.assertEquals("cannot park more vehicles", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_SystemShouldReturnListOfAvailableEmptySlots() {
        List expectedEmptySlots = new ArrayList();
        expectedEmptySlots.add(0);
        expectedEmptySlots.add(1);
        try {
            List emptySlots = parkingLotSystem.getEmptySlots();
            parkingLotSystem.parkVehicle(vehicle, (Integer) emptySlots.get(0));
            Assert.assertEquals(expectedEmptySlots,emptySlots);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
