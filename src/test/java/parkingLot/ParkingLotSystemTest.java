package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem;

    @Mock
    ParkingLot parkingLot;
    AssignLot assignLot;

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
    public void givenVehicle_ShouldUnParkTheVehicle() {
        this.parkingLot = mock(ParkingLot.class);
        this.parkingLotSystem.setMockObject(assignLot, parkingLot);
        Object vehicle = new Object();
        parkingLotSystem.unParkVehicle(vehicle);
        verify(parkingLot).unParkVehicle(vehicle);
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
            parkingLotSystem.parkVehicle(DriverType.HANDICAP,vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.parkVehicle(DriverType.HANDICAP,vehicle2);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle2);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0),parkedVehicleLot);
        } catch (ParkingLotException e) {
        }
    }
}
