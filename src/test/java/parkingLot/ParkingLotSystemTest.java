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
    ParkingStrategy strategy;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        this.strategy = mock(ParkingStrategy.class);
        this.parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenVehicle_shouldParkVehicle() {
        try {
            this.parkingLot = mock(ParkingLot.class);
            this.parkingLotSystem.setMockObject(strategy, parkingLot);
            Object vehicle = new Object();
            when(strategy.getLot()).thenReturn(parkingLot);
            parkingLotSystem.parkVehicle(vehicle);
            verify(parkingLot).parkVehicle(vehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_ShouldUnParkTheVehicle() {
        this.parkingLot = mock(ParkingLot.class);
        this.parkingLotSystem.setMockObject(strategy, parkingLot);
        Object vehicle = new Object();
        parkingLotSystem.unParkVehicle(vehicle);
        verify(parkingLot).unParkVehicle(vehicle);
    }

    @Test
    public void givenVehicle_ShouldParkVehicleEvenlyOnEmptySlot() {
        try {
            parkingLotSystem.createParkingLot(2,2);
            Object vehicle = new Object();
            parkingLotSystem.parkVehicle(vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.parkVehicle(vehicle2);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle);
            ParkingLot parkingLot = parkingLotSystem.parkingLotsList.get(0);
            Assert.assertEquals(parkingLot,parkedVehicleLot);
        } catch (ParkingLotException e) {
        }
    }


    /* @Test
    public void givenVehicle_ShouldUnParkTheVehicle() {
        Object vehicle = new Object();
        verify(parkingLot).unParkVehicle(vehicle);
    }*/
}
