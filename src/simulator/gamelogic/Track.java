package simulator.gamelogic;

import simulator.GameTick;

import java.util.*;


/**
 * Track class the Cars are racing on.
 */
public class Track{
    /**
     * Approximate minimum laptime on track
     */
    static double minimumLaptime;
    /**
     * Name of track
     */
    static String name;
    /**
     * Array to store Cars
     */
    public final ArrayList<Car> participantCars = new ArrayList<>();
    /**
     * Laps needed to complete on track
     */
    private final int lapNum;

    /**
     * Copy of the leading car
     */
    private Car firstCar = new Car("null", new Tyre(2));


    /**
     * @param minimumLaptime Approximate minimum laptime on track
     * @param name Name of track
     * @param lapNumber Laps needed to complete on track
     */
    public Track(double minimumLaptime, String name, int lapNumber) {
        if (lapNumber < 1 || minimumLaptime < 5) throw new IllegalArgumentException("Wrong lapnumber or minimumLaptime at constructor");
        Track.minimumLaptime = minimumLaptime;
        Track.name = name;
        this.lapNum = lapNumber;
    }

    /**
     * @return Laps needed to complete
     */
    public int getLapNum() {
        return this.lapNum;
    }

    /**
     * @return gets the leader car
     */
    public Car getFirstCar() {
        return firstCar;
    }

    /**
     * Makes the cars start the race
     */
    public void go() {
        for (Car itercar : participantCars) {
            itercar.setLapstoComplete(this.lapNum);
            itercar.go();
        }
        this.startPolling();
    }

    /**
     * @return All cars finished the race
     */
    public boolean allFinished() {
        for (Car car : participantCars) {
            if (!car.isFinished()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Starts polling the cars for their status
     */
    private void startPolling() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                int mostLaps = participantCars.get(0).getLaps();
                double leastOverallTime = participantCars.get(0).getTotalTime();
                int carId = 0;

                for (Car itercar : participantCars) {
                    if (itercar.getLaps() >= mostLaps && itercar.getTotalTime() < leastOverallTime) {
                        mostLaps = itercar.getLaps();
                        leastOverallTime = itercar.getTotalTime();
                        carId = participantCars.indexOf(itercar);
                    }
                }

                firstCar = participantCars.get(carId);
                if (firstCar.getLaps() == lapNum) {
                    System.out.println("Race won");
                    GameTick.gameticker.cancel();
                    System.out.println(firstCar.getCarName() + "!!!!!!!!!!!!!");
                }

            }
        };

        GameTick.gameticker.schedule(task, 1, 100);

    }


    /**
     * Adds random Cars to the track with random options
     * @param carNum Number of cars
     */
    public void addRandomCars(int carNum) {
        Stack<String> carnames = new Stack<>();
        String[] carnamesArray = {"Mercedes1", "Mercedes2", "Williams1", "Williams2", "Haas1", "Haas2", "Racing Point1", "Racing Point2", "Mclaren1", "Mclaren2", "Renault1", "Renault2", "Redbull1", "Redbull2", "Ferrari1", "Ferrari2", "Alfa Romeo1", "ALfa Romeo2", "AlfaTauri1", "AlfaTauri2"};
        for (String car : carnamesArray) {
            carnames.push(car);
        }
        Collections.shuffle(carnames);

        for (int i = 0; i < carNum; i++) {
            participantCars.add(new Car(carnames.pop(), new Tyre(true), new Random().nextInt(2) + 1));
        }
    }

    /**
     * Resets every Car on the Track
     */
    public void cancel() {
        for (Car car : participantCars) {
            car.cancelTimer();
        }
        participantCars.clear();
        firstCar = null;
    }


}
