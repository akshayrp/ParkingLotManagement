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
            Vehicle vehicle = new Vehicle("Yellow","ndfl","jnfl","ndl");
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
            Vehicle vehicle = new Vehicle("Black","nel","jnk","nle");
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
            Vehicle vehicle = new Vehicle("Grey","jebf","ndl","nkd");
            parkingLotSystem.unParkVehicle(vehicle);
            verify(parkingLot).unParkVehicle(vehicle);
        } catch (ParkingLotException e) {
        }

    }

    @Test
    public void givenVehicle_ShouldParkVehicleEvenlyOnEmptySlot() {
        try {
            parkingLotSystem.createParkingLot(2,2);
            Vehicle vehicle = new Vehicle("Red","nkd","nkle","mnsld");
            parkingLotSystem.parkVehicle(VehicleSize.REGULAR,vehicle);
            Vehicle vehicle2 = new Vehicle("White","jdn","nkds","kd");
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle2);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0),parkedVehicleLot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleWithHandicappedDriver_ShouldParkVehicleAtNearestFreeSpace() {
        try {
            parkingLotSystem.createParkingLot(2,2);
            Vehicle vehicle = new Vehicle("Black","ndfl","nd","mndks");
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle);
            Vehicle vehicle2 = new Vehicle("White","ndj","jkd","ndl");
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
            parkingLotSystem.createParkingLot(2,2);
            Vehicle vehicle1 = new Vehicle("White","dnkle","nek","kldmne");
            Vehicle vehicle2 = new Vehicle("Yellow","kdnl","jnde","nled");
            Vehicle vehicle3 = new Vehicle("White","jldns","jnd","ldkesm");
            Vehicle vehicle4 = new Vehicle("Green","kdlf","dmkl","kms");
            Vehicle vehicle5 = new Vehicle("Grey","mkldc","ks","mksd");
            parkingLotSystem.createParkingLot(3, 2);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle1);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle2);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle3);
            parkingLotSystem.parkVehicle(DriverType.NORMAL, vehicle4);
            parkingLotSystem.unParkVehicle(vehicle2);
            parkingLotSystem.unParkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(VehicleSize.LARGE, vehicle5);
            ParkingLot parkedVehicleLot = parkingLotSystem.getParkedVehicleLot(vehicle5);
            Assert.assertEquals(parkingLotSystem.parkingLotsList.get(0), parkedVehicleLot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotWithVehicle_ShouldReturnListOfLocationsWhiteVehicle() {
        try {
            ArrayList<ArrayList<Integer>> expectedList = new ArrayList<>();
            ArrayList<Integer> lot1 = new ArrayList<>();
            ArrayList<Integer> lot2 = new ArrayList<>();
            lot1.add(0);
            lot2.add(1);
            expectedList.add(lot1);
            expectedList.add(lot2);
            parkingLotSystem.createParkingLot(2,2);
            Vehicle vehicle = new Vehicle("White","kjsdnl","knd","kmd");
            Vehicle vehicle2 = new Vehicle("White","kndm","mnkdl","dsnl");
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle);
            parkingLotSystem.parkVehicle(DriverType.HANDICAP_DRIVER,vehicle2);
            ArrayList<ArrayList<Integer>> locationList = parkingLotSystem.getLocation("White");
            System.out.println(locationList.toString());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehiclesWhenParked_ShouldReturnLocationPlateNumberAndAttendantNameOfAllBlueToyotaCars() {
        try {
            ArrayList<ArrayList<Vehicle>> expectedList = new ArrayList<>();
            ArrayList<Vehicle> lot1 = new ArrayList<>();
            ArrayList<Vehicle> lot2 = new ArrayList<>();
            parkingLotSystem.createParkingLot(2,2);
            Vehicle vehicle1 = new Vehicle("Blue","Toyota","MH102","abc");
            Vehicle vehicle2 = new Vehicle("Blue","Toyota","MH100","abc");
            Vehicle vehicle3 = new Vehicle("Brown","Duster","MH117","abc");
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle1);
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle2);
            parkingLotSystem.parkVehicle(DriverType.NORMAL,vehicle3);
             lot1.add(vehicle1);
            lot2.add(vehicle2);
            expectedList.add(lot1);
            expectedList.add(lot2);
            ArrayList<ArrayList<Vehicle>> location = parkingLotSystem.getLocation("blue", "Toyota");
            System.out.println(location);
            Assert.assertEquals(expectedList,location);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
