package simulator.gamelogic;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Car{
    private final String name;
    private final int quickness;
    private Tyre tyre;
    private int laps;
    private double laptime;
    private double totalTime = 0;
    private int lapsToComplete;
    private boolean finished = false;
    private int pittingAt;
    private int position;

    Timer timer = new Timer();


    /**
     * Car constructor
     * @param name Car name
     * @param tyre Tyre
     * @param quickness Int 1,2,3 for a bit of car speed.
     */
    public Car(String name, Tyre tyre, int quickness) {
        if (quickness > 3 || quickness < 1)
            throw new Error("Wrong car 'quickness' class");
        this.name = name;
        this.tyre = tyre;
        this.laps = 0;
        this.quickness = quickness;
    }


    /**
     * Car constructor
     * sets random quickness
     * @param name Car name
     * @param tyre Tyre
     */
    public Car(String name, Tyre tyre) {
        this.name = name;
        Random random = new Random();
        this.tyre = tyre;
        this.laps = 0;
        this.quickness = random.nextInt(2) + 1;
    }

    public void oneLap() {
        this.laps++;
        tyre.incLaps();
        this.updateLaptime();


        System.out.println(this.name + " " + this.laptime + "\t" + this.laps + "\t" + this.totalTime);
    }


    /**
     * Starts the car
     * Starts timer that updates Car status every x seconds
     */
    public void go() {
        //lapsToComplete = this.lapsToComplete;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                double lt = generateLaptime(Car.this.tyre, Car.this.laps);
                laps++;
                tyre.incLaps();
                Car.this.updateLaptime(lt);

                System.out.println(laps + "\t"+  Car.this.laptime  +"\t" +   totalTime + " " + name + " " + Car.this.tyre.getLaps()) ;


                if(laps == Car.this.lapsToComplete) {
                    timer.cancel();
                    timer = null;
                    finished = true;
                    System.out.println(name + " finished the race");
                    }
                }

            };
        //System.out.println(Car.this.laptime);
        if(Car.this.laps == 0) Car.this.laptime = 90;
        timer.schedule(task, (long) (Car.this.laptime/20*1000), (long) (Car.this.laptime/20*1000));

    }


    public void pit(String t) {
        resetTimer();
        this.laptime += 20.0;
        this.tyre.setTyre(t);
        this.tyre.resetLaps();
        System.out.println("Changed to " + t + " in lap " + this.laps);
        pittingAt = this.laps;
        go();
    }

    /**
     * Changes tyre, adds 20 sec to total time and laptime, resets timer.
     * @param t Tyre
     */
    public void pit(Tyre t) {
        this.tyre = t;
        this.tyre.resetLaps();
        this.laptime += 20.0;
        resetTimer();
        System.out.println("Changed to " + t + " in lap " + this.laps);
        pittingAt = this.laps;
        go();
    }

    public void finish() {
        System.out.println(this.name + " finished after " + getLaps() + " laps");
        System.out.println(this.totalTime);
        timer.cancel();
    }

    /**
     * @return Total time in double
     */
    public double getTotalTime(){return this.totalTime;}

    /**
     * @return Total time in string format
     */
    public String getTotalTimeString(){
        int min = (int) (totalTime / 60);
        int sec = (int)(totalTime - min*60);
        return min + " min " + sec + " sec";
    }

    /**
     * @return Completed laps
     */
    public int getLaps() {
        return this.laps;
    }

    /**
     * @return Car's tyre
     */
    public String getTyre() {
        return this.tyre.getTyre();
    }

    /**
     * @return Car name
     */
    public String getCarName(){ return this.name; }

    /**
     * @param laps Set's laps to complete
     */
    public void setLapstoComplete(int laps){
        this.lapsToComplete = laps;
    }

    /**
     * @return Car's current laptime
     */
    public double getLaptime() {return laptime;}

    /**
     * @return Car finished the race.
     */
    public boolean isFinished(){return finished;}

    /**
     * Updates Car's laptime by generating one and adding the value to the total time.
     */
    private void updateLaptime() {
        this.laptime = generateLaptime(this.tyre, this.laps);
        this.totalTime += this.laptime;
    }

    /**
     * Updates Car's laptime and adds the value to the total time.
     * @param lt laptime
     */
    private void updateLaptime(double lt) {
        this.laptime = lt;
        this.totalTime += this.laptime;
    }

    /**
     * @return Car's place (int).
     */
    public int getPlace(){
        return this.position;
    }

    /**
     * @param t Tyre
     * @param l laps
     * @return laptime double
     */
    private double generateLaptime(Tyre t, int l) {
        double laptime = Track.minimumLaptime;
        double slowness = t.getSlownessMultiplier();
        double durability;
        if (t.getTyre().equals("Soft")) {
            durability = -21;
        } else if (t.getTyre().equals("Medium")) {
            durability = -27;
        } else {
            durability = -30;
        }
        //System.out.println(((Math.PI / 2) + Math.atan(durability + t.getLaps() )));
        laptime = laptime * slowness * (1 + ((Math.PI / 2) + Math.atan(durability + t.getLaps())) / 5);
        laptime -= this.quickness / 3.0;
        if (l == 0) laptime += 10;
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

    /**
     * Cancels car's timer
     */
    public void cancelTimer(){
        timer.cancel();
    }

    /**
     * Cancels car's timer and resets it by creating a new one.
     */
    public void resetTimer() {
        timer.cancel();
        timer = new Timer();}
}
