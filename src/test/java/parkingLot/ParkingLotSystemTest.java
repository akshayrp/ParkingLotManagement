package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem;

    @Mock
    ParkingLot parkingLot;
    AssignLot assignLot;
    ArrayList mockList;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        this.assignLot = mock(AssignLot.class);
        this.parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenVehicle_shouldParkVehicle() {
        try {
            this.parkingLot = mock(ParkingLot.class);
            this.parkingLotSystem.setMockObject(assignLot, parkingLot);
            Object vehicle = new Object();
            when(assignLot.getLot(DriverType.NORMAL)).thenReturn(parkingLot);
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle);
            verify(parkingLot).parkVehicle(vehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleAndParkingSlot_ShouldParkTheVehicle() {
        try {
            this.parkingLot = mock(ParkingLot.class);
            this.parkingLotSystem.setMockObject(assignLot, parkingLot);
            Object vehicle = new Object();
            parkingLotSystem.parkVehicle(vehicle, 0);
            verify(parkingLot).parkVehicle(vehicle, 0);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_ShouldUnParkTheVehicle() {
        try {

            this.parkingLot = mock(ParkingLot.class);
            this.parkingLotSystem.setMockObject(assignLot, parkingLot);
            Object vehicle = new Object();
            parkingLotSystem.unParkVehicle(vehicle);
            verify(parkingLot).unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
        }

    }

    @Test
    public void givenVehicle_ShouldParkVehicleEvenlyOnEmptySlot() {
        try {
            parkingLotSystem.createParkingLot(2,2);
            Object vehicle = new Object();
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle2);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0),parkedVehicleLot);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWithHandicappedDriver_ShouldParkVehicleAtNearestFreeSpace() {
        try {
            parkingLotSystem.createParkingLot(2,2);
            Object vehicle = new Object();
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.parkVehicle(DriverType.HANDICAP_DRIVER, vehicle2);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle2);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0),parkedVehicleLot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenLargeVehicle_ShouldParkVehicleAtMoreFreeSpace() {
        try {
            Object vehicle1 = new Object();
            Object vehicle2 = new Object();
            Object vehicle3 = new Object();
            Object vehicle4 = new Object();
            Object vehicle5 = new Object();
            parkingLotSystem.createParkingLot(3, 2);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle1);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle2);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle3);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle4);
            parkingLotSystem.unParkVehicle(vehicle2);
            parkingLotSystem.unParkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(Vehicle.LARGE, vehicle5);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle5);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0), parkedVehicleLot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
