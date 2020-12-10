package simulator.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrackTest {

    Track track;

    @Before
    public void setUp(){
        track = new Track(30, "Silverstone", 20);
    }

    @Test
    public void addRandomCars() {
        track.addRandomCars(20);
        assertEquals(20, track.participantCars.size());
    }

    @Test
    public void startRace() throws InterruptedException {
        track.addRandomCars(20);
        track.go();
        Thread.sleep(6000);

    }

}