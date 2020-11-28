package simulator.gamelogic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Car {
    private final String name;
    private Tire tire;
    private int laps;
    private double laptime; // Ezt máshogy kéne
    private final int quickness;
    private int seasonPoints = 0;
    private double totalTime = 0;
    private ArrayList<Tire> scheduledPits = new ArrayList<>();

    Timer timer = new Timer();


    public Car(String n, Tire t, int q) {
        if (q > 3 || q < 1)
            throw new Error("Wrong car 'quickness' class");

        this.name = n;
        this.tire = t;
        this.laps = 0;
        this.quickness = q;
    }

    public Car(String name, Tire t) {
        this.name = name;
        Random random = new Random();
        this.tire = t;
        this.laps = 0;
        this.quickness = random.nextInt(2) + 1;
    }

    public void oneLap(){
        this.laps++;
        tire.incLaps();
        this.updateLaptime();


        //System.out.println(this.getLaptimeString());
        System.out.println(this.name + " " + this.laptime + "\t" + this.laps + "\t" + this.totalTime);
    }

    public void go(int LapNumber){
        double lt = generateLaptime(this.tire, this.laps);

        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                laps++;
                updateLaptime(lt);
                System.out.println(laps);
            }
        };


        timer.schedule(task, 1000);

    }

    public void pit(String t){
        this.laptime += 20.0;
        this.tire.setTyre(t);
        this.tire.resetLaps();
        System.out.println("Changed to " + t + " in lap " + this.laps);
    }

    public void pit(Tire t){
        this.tire = t;
    }

    public void finish(){
        System.out.println(this.name +  " finished after " + getLaps() + " laps");
        System.out.println(this.totalTime);
    }

    public int getLaps(){
        return this.laps;
    }

    public String getTyre(){
        return this.tire.getTyre();
    }

    public String getName(){
        return this.name;
    }

    private void updateLaptime(){
        this.laptime = generateLaptime(this.tire, this.laps);
        this.totalTime += this.laptime;
    }

    private void updateLaptime(double lt){
        this.laptime = lt;
        this.laptime += this.laptime;
    }

    private String getLaptimeString(){
        return String.valueOf((int) this.laptime / 60.0)  + ":" + String.valueOf(this.laptime - ((int)this.laptime / 60) * 60);
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
        double magic = ( 1 + (Math.PI / 2) + Math.atan(durability + t.getLaps() ));
        laptime = laptime * slowness * ( 1 + ((Math.PI / 2) + Math.atan(durability + t.getLaps() )) / 5);
        laptime -= this.quickness / 3;

        if(l == 1) laptime += 10;



        return laptime;
    }

}
