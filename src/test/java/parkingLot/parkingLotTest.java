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

    ParkingLot parkingLot;
    ArrayList<Slot> slotList;
    Vehicle vehicle;
    Vehicle vehicle1;


    @Before
    public void setUp() {
        this.slot = mock(Slot.class);
        this.slotList = mock(ArrayList.class);
        this.informObservers = mock(InformObservers.class);
        this.parkingLot = new ParkingLot(1,3);
        vehicle = new Vehicle("red","bfdiu","jhbedi","nelk");
        vehicle1 = new Vehicle("White","ndklj","kjnlsd","jnd");
    }

    @Test
    public void givenVehicleWhenParked_ShouldReturnTrue() {
        try {
            parkingLot.parkVehicle(vehicle,0);
            Assert.assertTrue(parkingLot.isVehiclePresent(vehicle));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleWhichIsNotParked_AndTriedToCheckIfParked_ShouldThrowException() {
        try {
            parkingLot.parkVehicle(vehicle,0);
            parkingLot.parkVehicle(vehicle1,0);
            Assert.assertFalse(parkingLot.slots.contains(new Object()));
        } catch (ParkingLotException e) {
            Assert.assertEquals("parked vehicle is not same as given vehicle or vehicle not parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenUnParked_ShouldReturnTrue() {
        try {
            parkingLot.parkVehicle(vehicle,0);
            parkingLot.unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("vehicle not found",e.getMessage());
        }
    }

    @Test
    public void givenVehicleWhenParkingLotFull_ShouldThrowException() {
        try {
            parkingLot.parkVehicle(vehicle,0);
            parkingLot.parkVehicle(vehicle1,1);
            parkingLot.parkVehicle(vehicle1,2);
            parkingLot.parkVehicle(vehicle1,3);
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
        expectedEmptySlots.add(2);
        try {
            List<Integer> emptySlots = parkingLot.getEmptySlots();
            parkingLot.parkVehicle(vehicle,0);
            Assert.assertEquals(expectedEmptySlots,emptySlots);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicle_WhenParked_shouldReturnTrue() {
        try {
            Vehicle vehicle = new Vehicle("White","jne","nlksn","eln");
            parkingLot.parkVehicle(vehicle);
            boolean vehiclePresent = parkingLot.isVehiclePresent(vehicle);
            Assert.assertTrue(vehiclePresent);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicle_WhenNotParked_shouldReturnFalse() {
            boolean vehiclePresent = parkingLot.isVehiclePresent(vehicle);
            Assert.assertFalse(vehiclePresent);

    }

    /////////UC7
    @Test
    public void givenVehicleWhenParked_ShouldBeAbleToFindTheVehicleAtThatSlot() {
        try {
            parkingLot.parkVehicle(vehicle,0);
            int expectedSlot = parkingLot.findSlotOfParkedVehicle(vehicle);
            Assert.assertEquals(0,expectedSlot);
        } catch (ParkingLotException e) {
        }
    }


    @Test
    public void givenVehicleWhenParked_ShouldReturnVehicleCount() {
        try {
            parkingLot.parkVehicle(vehicle);
            int numberOfVehiclesParked = parkingLot.getNumberOfVehiclesParked();
            Assert.assertEquals(1,numberOfVehiclesParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleWhenParked_ShouldReturnAvailableEmptySlots() {
        try {
            parkingLot.parkVehicle(vehicle);
            int emptySlots = parkingLot.getNumberOfEmptySlots();
            Assert.assertEquals(2,emptySlots);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenARequestToGiveSlotNumberOfAllWhiteCars_ShouldReturnListOfSlotNumbersContainingWhiteCars() {
        try {
            ArrayList<Integer> expectedSlotNumber = new ArrayList<>();
            expectedSlotNumber.add(0);
            Vehicle vehicle = new Vehicle("White","nano","MH112","abc");
            Vehicle vehicle2 = new Vehicle("Yellow","alto","MH30","abc");
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(vehicle2);
            ArrayList<Integer> actual = parkingLot.getLocation("white");
            Assert.assertEquals(expectedSlotNumber,actual);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenARequestToGiveSlotNumberOfAllWhiteCars_WhenWhiteCarNotPresent_ShouldReturnListOfSlotNumbersContainingWhiteCars() {
        try {
            ArrayList<Integer> expectedSlotNumber = new ArrayList<>();
            expectedSlotNumber.add(1);
            Vehicle vehicle = new Vehicle("Red","maruti","MH102","abc");
            Vehicle vehicle2 = new Vehicle("white","bmw","MH202","xyz");
            parkingLot.parkVehicle(vehicle);
            parkingLot.parkVehicle(vehicle2);
            ArrayList<Integer> actual = parkingLot.getLocation("white");
            Assert.assertEquals(expectedSlotNumber,actual);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehiclesWhenParked_ShouldReturnLocationPlateNumberAndAttendantNameOfAllBlueToyotaCars() {
        try {
            ArrayList<Vehicle> expectedList = new ArrayList<>();
            Vehicle vehicle1 = new Vehicle("Blue","Toyota","MH102","abc");
            Vehicle vehicle2 = new Vehicle("Blue","Toyota","MH100","abc");
            Vehicle vehicle3 = new Vehicle("Brown","Duster","MH117","abc");
            parkingLot.parkVehicle(vehicle1);
            parkingLot.parkVehicle(vehicle2);
            parkingLot.parkVehicle(vehicle3);
            expectedList.add(vehicle1);
            expectedList.add(vehicle2);
            ArrayList<Vehicle> location = parkingLot.getLocation("blue", "Toyota");
            Assert.assertEquals(expectedList,location);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
