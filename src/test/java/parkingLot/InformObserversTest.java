package parkingLot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class InformObserversTest {
    InformObservers informObservers;

    @Mock
    LotObserver lotObserver;
    private ArrayList observersList;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        this.informObservers = new InformObservers();
        this.observersList = new ArrayList();
        this.lotObserver = mock(LotObserver.class);
        this.observersList.add(lotObserver);
        this.observersList.add(lotObserver);
        this.observersList.add(lotObserver);
        this.informObservers.setMockObject(lotObserver, observersList);
    }

    @Test
    public void givenParkingIsFull_ShouldInformToObservers() {
        informObservers.informParkingIsFull();
        verify(lotObserver,times(3)).informWhenLotFull();
    }

    @Test
    public void givenLotAvailableAgain_ShouldInformToObserversOfAvailability() {
        informObservers.informWhenLotAvailableAgain();
        verify(lotObserver,times(3)).informWhenLotAvailableAgain();
    }
}
