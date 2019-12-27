package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class VehicleAvailabilityCheckTest {

    VehicleAvailabilityCheck availabilityCheck;
    Object vehicle1;
    Object vehicle2;
    Object vehicle3;
    ArrayList<Object> parkingLot;

    @Before
    public void setUp() throws Exception {
        this.availabilityCheck = new VehicleAvailabilityCheck();
        this.vehicle1 =  new Object();
        this.vehicle2 = new Object();
        this.vehicle3 = new Object();
        this.parkingLot = new ArrayList();
        parkingLot.add(vehicle1);
        parkingLot.add(vehicle2);
    }

    @Test
    public void givenVehicleAndParkingLot_returnTrueIfVehicleIsAvailable() throws ParkingLotException {
        Assert.assertTrue(availabilityCheck.isVehicleParked(vehicle1,parkingLot));
    }

    @Test
    public void givenVehicleAndParkingLot_returnTrueIfVehicleIsNotAvailable() throws ParkingLotException {
        Assert.assertTrue(availabilityCheck.isVehicleUnParked(vehicle3,parkingLot));
    }
}
