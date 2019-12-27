package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem;
    Object vehicle;
    Object vehicle1;

    @Before
    public void setUp() {
        this.parkingLotSystem = new ParkingLotSystem();
        this.vehicle = new Object();
        this.vehicle1 = new Object();

    }

    @Test
    public void givenVehicleWhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhichIsNotParked_AndTriedToCheckIfParked_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.isVehicleParked(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("parked vehicle is not same as given vehicle or vehicle not parked",e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.unParkVehicle(vehicle);
            boolean isUnParked  = parkingLotSystem.isVehicleUnParked();
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhenTriedToCheckIfUnParkedWithoutUnParking_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.isVehicleUnParked();
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle not UnParked",e.getMessage());
        }
    }
}
