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
        this.parkingLotSystem = new ParkingLotSystem(2);
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
            parkingLotSystem.isVehicleParked(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("parked vehicle is not same as given vehicle or vehicle not parked",e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.unParkVehicle(vehicle);
            boolean isUnParked  = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhenTriedToCheckIfUnParkedWithoutUnParking_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.isVehicleUnParked(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle not UnParked",e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenParkingLotFull_ShouldThrowException() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("cannot park more vehicles",e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenParkingLotFull_ShouldInformOwner() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {

            Assert.assertEquals(true,parkingLotSystem.owner.isFull);
            Assert.assertEquals("cannot park more vehicles",e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenParkingLotFull_ShouldInformAirportSecurity() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals(true, parkingLotSystem.security.isFull);
            Assert.assertEquals("cannot park more vehicles", e.getMessage());
        }
    }
}
