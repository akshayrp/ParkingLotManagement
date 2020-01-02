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

public class parkingLotTest {
    @Mock
    InformObservers informObservers;
    Slot slot;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    ParkingLot ParkingLot;
    Object vehicle;
    Object vehicle1;
    ArrayList<Slot> slotList;

    @Before
    public void setUp() {
        this.slot = mock(Slot.class);
        this.slotList =mock(ArrayList.class);
        this.informObservers = mock(InformObservers.class);
        this.ParkingLot = new ParkingLot(2);
        this.vehicle = new Object();
        this.vehicle1 = new Object();
    }

    @Test
    public void givenVehicleWhenParked_ShouldReturnTrue() {
        try {
            ParkingLot.parkVehicle(vehicle,0);
            Assert.assertTrue(ParkingLot.isVehiclePresent(vehicle));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhichIsNotParked_AndTriedToCheckIfParked_ShouldThrowException() {
        try {
            ParkingLot.parkVehicle(vehicle,0);
            ParkingLot.parkVehicle(vehicle1,0);
            Assert.assertFalse(ParkingLot.slots.contains(new Object()));
        } catch (ParkingLotException e) {
            Assert.assertEquals("parked vehicle is not same as given vehicle or vehicle not parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        try {
            ParkingLot.parkVehicle(vehicle,0);
            ParkingLot.unParkVehicle(vehicle);
            Assert.assertFalse(ParkingLot.isVehiclePresent(vehicle));
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicleWhenParkingLotFull_ShouldThrowException() {
        try {
            ParkingLot.parkVehicle(vehicle,0);
            ParkingLot.parkVehicle(vehicle1,0);
            ParkingLot.parkVehicle(vehicle1,0);
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
            List<Integer> emptySlots = ParkingLot.getEmptySlots();
            ParkingLot.parkVehicle(vehicle,0);
            Assert.assertEquals(expectedEmptySlots,emptySlots);
        } catch (ParkingLotException e) {
        }
    }

    /////////UC7
    @Test
    public void givenVehicleWhenParked_ShouldBeAbleToFindTheVehicleAtThatSlot() {
        try {
            ParkingLot.parkVehicle(vehicle,0);
            int expectedSlot = ParkingLot.findVehicle(vehicle);
            Assert.assertEquals(0,expectedSlot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleWhenNotParkedAndTriedToSearch_ShouldThrowException() {
        try {
            ParkingLot.findVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle not found", e.getMessage());
        }
    }


    /////////UC8
   /* @Test
    public void givenVehicleWhenParked_ShouldNoteParkingTime() {
        try {
            parkingLot.setMockedObject(slotList,informObservers);
            verify(slotList).get(0).setVehicleAndTime(vehicle);
            parkingLot.parkVehicle(vehicle,0);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }*/

   /////////UC9
    /*@Test
    public void givenVehicle_ShouldParkVehicleOnSlotByEvenDistribution() {
        try {
            ParkingLotSystem lots = new ParkingLotSystem();
            lots.getEmptySlot();
            ParkingLot.parkVehicle(vehicle,0);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }*/
}
