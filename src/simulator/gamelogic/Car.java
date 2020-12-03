package simulator.gamelogic;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Car {
    private final String name;
    private final int quickness;
    private Tire tire;
    private int laps;
    private double laptime;
    private double totalTime = 0;
    private int lapsToComplete;
    private boolean finished = false;
    private boolean pitThisLap = false;

    Timer timer = new Timer();


    public Car(String name, Tire tire, int quickness) {
        if (quickness > 3 || quickness < 1)
            throw new Error("Wrong car 'quickness' class");
        this.name = name;
        this.tire = tire;
        this.laps = 0;
        this.quickness = quickness;
    }

    public Car(String name, Tire tire) {
        this.name = name;
        Random random = new Random();
        this.tire = tire;
        this.laps = 0;
        this.quickness = random.nextInt(2) + 1;
    }



    public void oneLap() {
        this.laps++;
        tire.incLaps();
        this.updateLaptime();


        //System.out.println(this.getLaptimeString());
        System.out.println(this.name + " " + this.laptime + "\t" + this.laps + "\t" + this.totalTime);
    }




    public void go() {
        //lapsToComplete = this.lapsToComplete;
        double lt = generateLaptime(Car.this.tire, Car.this.laps);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                laps++;
                updateLaptime(lt);

                System.out.println(laps + " " + " "+  lt  +  totalTime + " " + name);
                if(pitThisLap){

                }

                if(laps == Car.this.lapsToComplete) {
                    timer.cancel();
                    finished = true;
                    System.out.println(name + " finished the race");
                    }
                }

            };
        timer.schedule(task, (long) (lt/20*1000), (long) (lt/20*1000));

    };




    public void requestPit(){
        pitThisLap = true;
    }

    private void addPitTime(){

    }

    public void pit(String t) {

        this.laptime += 20.0;
        this.tire.setTyre(t);
        this.tire.resetLaps();
        System.out.println("Changed to " + t + " in lap " + this.laps);
    }

    public void pit(Tire t) {
        this.tire = t;
    }

    public void finish() {
        System.out.println(this.name + " finished after " + getLaps() + " laps");
        System.out.println(this.totalTime);
        timer.cancel();
    }

    public double getTotalTime(){return this.totalTime;}

    public int getLaps() {
        return this.laps;
    }

    public String getTyre() {
        return this.tire.getTyre();
    }

    public String getName() {
        return this.name;
    }

    public void setLapstoComplete(int laps){
        this.lapsToComplete = laps;
    }

    private void updateLaptime() {
        this.laptime = generateLaptime(this.tire, this.laps);
        this.totalTime += this.laptime;
    }

    private void updateLaptime(double lt) {
        this.laptime = lt;
        this.totalTime += this.laptime;
    }

    private String getLaptimeString() {
        return (int) this.laptime / 60.0 + ":" + (this.laptime - ((int) this.laptime / 60) * 60);
    }


    private double generateLaptime(Tire t, int l) {
        double laptime = Track.minimumLaptime;
        double slowness = t.getSlownessMultiplier();
        double durability;
        if (t.getTyre() == "Soft") {
            durability = -21;
        } else if (t.getTyre() == "Medium") {
            durability = -27;
        } else {
            durability = -30;
        }
        //System.out.println(((Math.PI / 2) + Math.atan(durability + t.getLaps() )));
        double magic = (1 + (Math.PI / 2) + Math.atan(durability + t.getLaps()));
        laptime = laptime * slowness * (1 + ((Math.PI / 2) + Math.atan(durability + t.getLaps())) / 5);
        laptime -= this.quickness / 3;
        if (l == 1) laptime += 10;
        if (new Random().nextInt(5) == 1) laptime += 1;

        switch (new Random().nextInt(8)){
            case 1:
                laptime += 0.5;
                break;
            case 2:
                laptime += 0.1;
                break;
            case 3:
                laptime -= 0.1;
                break;
            case 4:
                laptime -= 0.5;
                break;
            default:
                laptime += 0;
                break;
        }

        return laptime;
    }

}
