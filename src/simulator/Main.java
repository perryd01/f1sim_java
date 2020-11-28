package simulator;

import simulator.gamelogic.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {



        Track track = new Track(92, "Hungaroring", 60);


        Car c1 = new Car("Mercedes", new Tire(2), 1);
        Car c2 = new Car("Sauber", new Tire("Hard", 2), 3);

        track.participantCars.add(c1);
        track.participantCars.add(c2);
        track.participantCars.add(new Car("BMW", new Tire(4)));

        c1.go(70);


        track.start();

    }


}
