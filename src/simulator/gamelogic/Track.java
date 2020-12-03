package simulator.gamelogic;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Track {
    static double minimumLaptime;
    static String name;
    static int lapNum;
    private boolean started = false;
    public ArrayList<Car> participantCars = new ArrayList<Car>();

    private Car firstCar = new Car("null", new Tire(2));
    private boolean racewon = false;
    Timer trackPoller = new Timer();




    public Track(double minimumLaptime, String n, int lapNumber) throws Exception {
        if(lapNumber < 1 || minimumLaptime < 5) throw new Exception("Wrong input");
        this.minimumLaptime = minimumLaptime;
        this.name = n;
        this.lapNum = lapNumber;
    }

    public void start() throws InterruptedException {
        if(started == true) throw new Error("Already started");

        for (Car car : participantCars) {
            System.out.println(car.getName() + " starts on " + car.getTyre());
        }

        for (int i = 0; i < Track.lapNum; i++) {
            Thread.sleep(1000);
            for (int j = 0; j < participantCars.size(); j++) {
                participantCars.get(j).oneLap();
            }
        }



//        //for(int i = 0; i < Track.lapNum; i++){
//            for(int j = 0;j < participantCars.size(); j++){
//                participantCars.get(j).go(20);
//            }
//        //}

        finishAll();
    }

    private void finishAll(){
        for (int i = 0; i < participantCars.size(); i++) {
            participantCars.get(i).finish();
        }
    }

    public void planPit(String n, int l, String t){

    }

    private void finishCars(){

    }


    public void go(){
        for (Car itercar: participantCars) {
            itercar.setLapstoComplete(Track.lapNum);
            itercar.go();
        }
        this.startPolling();
    }

    private void startPolling(){
        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                int mostLaps = participantCars.get(0).getLaps();
                double leastOverallTime = participantCars.get(0).getTotalTime();
                int carId = 0;

                for (Car itercar: participantCars) {
                    if(itercar.getLaps() >= mostLaps && itercar.getTotalTime() < leastOverallTime){
                        mostLaps = itercar.getLaps();
                        leastOverallTime = itercar.getTotalTime();
                        carId = participantCars.indexOf(itercar);
                    }


                }

                firstCar = participantCars.get(carId);
                if(firstCar.getLaps() == lapNum) {
                    System.out.println("Race won");
                    finishCars();
                    trackPoller.cancel();
                    System.out.println(firstCar.getName() + "!!!!!!!!!!!!!");
                }

            }
        };

        trackPoller.schedule(task, 1000, (long) 10);

    }


}
