package simulator.gamelogic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    Car car;

    @Before
    public void setUp() {
        car = new Car("Mercedes1", new Tyre(2));
    }

    @Test
    public void isTheCarFinished() {
        assertFalse(car.isFinished());
    }

    @Test
    public void tyreNotNull() {
        assertNotNull(car.getTyre());
    }

    @Test
    public void getLaptime() throws Exception {
        Track track = new Track(10, "Hungaroring", 20);
        track.participantCars.add(car);
        track.go();
        Thread.sleep(4000);
        assertTrue(car.getLaptime() > 0);
    }

    @Test
    public void getLaps() throws Exception {
        Track track = new Track(10, "Hungaroring", 20);
        track.participantCars.add(car);
        track.go();
        Thread.sleep(10000);
        assertTrue(car.getLaps() > 1);
    }

    @Test
    public void testingPitting() throws Exception {
        Track track = new Track(10, "Hungaroring", 20);
        track.participantCars.add(car);
        track.go();
        Thread.sleep(3000);
        car.pit(new Tyre("Soft", 5));
        Thread.sleep(3000);
        assertSame("Soft", car.getTyre());
    }

    @Test
    public void addRandomCars(){
        Track track = new Track(10, "Hungaroring", 20);
        track.addRandomCars(10);
        assertEquals(10, track.participantCars.size());
    }


}