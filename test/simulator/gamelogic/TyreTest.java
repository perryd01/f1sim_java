package simulator.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TyreTest {

    Tyre tyre;

    @Before
    public void setUp() {
        tyre = new Tyre("Medium", 10);
    }

    @Test
    public void incLaps() {
        tyre.incLaps();
        assertEquals(11, tyre.getLaps());
    }

    @Test
    public void setLaps(){
        tyre.setLaps(20);
        assertEquals(20, tyre.getLaps());
    }

    @Test
    public void resetLaps(){
        tyre.resetLaps();
        assertEquals(0, tyre.getLaps());
    }
}