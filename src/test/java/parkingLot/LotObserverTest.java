package parkingLot;

import org.junit.Assert;
import org.junit.Test;

public class LotObserverTest {
    LotObserver lotObserver = new LotObserver();

    @Test
    public void givenLotFull_ShouldReturnTrue() {
        lotObserver.informWhenLotFull();
        Assert.assertTrue(lotObserver.isFull);
    }

    @Test
    public void givenLotAvailableAgain_ShouldReturnFalse() {
        lotObserver.informWhenLotAvailableAgain();
        Assert.assertFalse(lotObserver.isFull);
    }
}
