package simulator.gamelogic;

import java.util.ArrayList;
import java.util.Timer;

public class Track {
    static double minimumLaptime;
    static String name;
    static int lapNum;
    private boolean started = false;
    public ArrayList<Car> participantCars = new ArrayList<Car>();
    Timer timer;


    public Track(double mL, String n, int lN){
        Track.minimumLaptime = mL;
        Track.name = n;
        Track.lapNum = lN;
    }

    public void start() throws InterruptedException {
        if(started == true) throw new Error("Already started");

        for (Car car : participantCars) {
            System.out.println(car.getName() + " starts on " + car.getTyre());
        }

//        for (int i = 0; i < Track.lapNum; i++) {
//            TimeUnit.SECONDS.sleep(1);
//            for (int j = 0; j < participantCars.size(); j++) {
//                participantCars.get(j).oneLap();
//            }
//        }

        for(int i = 0; i < Track.lapNum; i++){
            for(int j = 0;j < participantCars.size(); j++){
                participantCars.get(j).go(i+1);
            }
        }

        finishAll();
    }

    private void finishAll(){
        for (int i = 0; i < participantCars.size(); i++) {
            participantCars.get(i).finish();
        }
    }

    public void planPit(String n, int l, String t){

    }

}
