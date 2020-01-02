package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ParkingLotTest {
    @Mock
    InformObservers informObservers;
    Slot slot;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    ParkingLot parkingLot;
    Object vehicle;
    Object vehicle1;
    ArrayList<Slot> slotList;

    @Before
    public void setUp() {
        this.slot = mock(Slot.class);
        this.slotList =mock(ArrayList.class);
        this.informObservers = mock(InformObservers.class);
        this.parkingLot = new ParkingLot(2,slotList,informObservers);
        this.vehicle = new Object();
        this.vehicle1 = new Object();
    }

    @Test
    public void givenVehicleWhenParked_ShouldReturnTrue() {
        try {
            parkingLot.parkVehicle(vehicle, 0);
            Assert.assertTrue(parkingLot.isVehiclePresent(vehicle));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhichIsNotParked_AndTriedToCheckIfParked_ShouldThrowException() {
        try {
            parkingLot.parkVehicle(vehicle, 0);
            parkingLot.parkVehicle(vehicle1, 1);
            Assert.assertFalse(parkingLot.slots.contains(new Object()));
        } catch (ParkingLotException e) {
            Assert.assertEquals("parked vehicle is not same as given vehicle or vehicle not parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        try {
            parkingLot.parkVehicle(vehicle, 0);
            parkingLot.unParkVehicle(vehicle);
            verify(informObservers).informWhenLotAvailableAgain();
            Assert.assertFalse(parkingLot.isVehiclePresent(vehicle));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhenParkingLotFull_ShouldThrowException() {
        try {
            parkingLot.parkVehicle(vehicle, 0);
            parkingLot.parkVehicle(vehicle1, 1);
            parkingLot.parkVehicle(vehicle1, 2);
            verify(informObservers).informParkingIsFull();
        } catch (ParkingLotException e) {
            Assert.assertEquals("cannot park more vehicles", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_SystemShouldReturnListOfAvailableEmptySlots() {
        List<Integer> expectedEmptySlots = new ArrayList();
        expectedEmptySlots.add(0);
        expectedEmptySlots.add(1);
        try {
            List<Integer> emptySlots = parkingLot.getEmptySlots();
            parkingLot.parkVehicle(vehicle, emptySlots.get(0));
            Assert.assertEquals(expectedEmptySlots,emptySlots);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhenParked_ShouldBeAbleToFindTheVehicleAtThatSlot() {
        try {
            parkingLot.parkVehicle(vehicle, 0);
            int expectedSlot = parkingLot.findVehicle(vehicle);
            Assert.assertEquals(0,expectedSlot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleWhenNotParkedAndTriedToSearch_ShouldThrowException() {
        try {
            parkingLot.findVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle not found", e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenParked_ShouldNoteParkingTime() {
        try {
            verify(slotList).get(0).setVehicleAndTime(vehicle);
            parkingLot.parkVehicle(vehicle,0);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
