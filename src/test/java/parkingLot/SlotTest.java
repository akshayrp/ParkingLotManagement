package parkingLot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class SlotTest {
    Slot slot;
    int slotNumber = 0;

    @Before
    public void setUp() throws Exception {
     slot = new Slot(slotNumber);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldSetParkingTime() {
        Vehicle vehicle = new Vehicle("White");
        slot.setVehicleAndTime(vehicle,LocalDateTime.now());
        Assert.assertEquals(LocalDateTime.now(),slot.getParkingTime());
    }
}
