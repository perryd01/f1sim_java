package simulator.gamelogic;

import java.util.Random;

/**
 * Tyre class.
 * A Car uses (a) Tyre ( and also includes )
 */
public class Tyre {
    /**
     * Tyre type in string
     */
    private String type;
    /**
     * Laps with these tyres
     */
    private int laps;
    /**
     * Slowness multiplier
     */
    private double slownessMultiplier = 1;

    /**
     * Tyre constructor
     * @param t Tyre string
     * @param l laps
     */
    public Tyre(String t, int l) {
        if(allowedTyre(t)){
            this.setTyre(t);
            this.setLaps(l);
            setSlownessMultiplier(t);
        }

    }

    /**
     * Tyre constructor
     * @param l laps
     */
    public Tyre(int l){
        this.setTyre("Soft");
        this.setLaps(l);
    }


    /**
     * @param random used to generate random tyre for bots
     */
    public Tyre(boolean random){
        String[] tyretypes = {"Soft", "Medium", "Hard"};
        if(!random) throw new IllegalArgumentException();

        this.setLaps(new Random().nextInt(5));
        this.setTyre(tyretypes[new Random().nextInt(3)]);
    }


    public Tyre(){
        this.setLaps(2);
        this.setTyre("Soft");
    }

    /**
     * Sets Tyre
     * @param t Tyre
     */
    public void setTyre(String t) {
        if(allowedTyre(t)){
            this.type = t;
        }
    }

    /**
     * Increase laps with tyres
     */
    public void incLaps() {
        this.laps++;
    }

    /**
     * Sets completed laps with these tyres
     * @param l laps
     */
    public void setLaps(int l){
        if(l >= 0){
            this.laps = l;
        }
        else{
            throw new Error("Bad lap number");
        }
    }

    /**
     * Resets timer(laps) of Tyre
     */
    public void resetLaps() {
        this.laps = 0;
    }

    /**
     * @return Gets Tyre
     */
    public String getTyre(){
        return this.type;
    }

    /**
     * @return Laps the Tyre has completed
     */
    public int getLaps(){
        return this.laps;
    }

    /**
     * @return Gets slowness multiplier
     */
    public double getSlownessMultiplier(){
        return this.slownessMultiplier;
    }

    /**
     * Sets slowness multiplier
     * @param t Tyre
     */
    private void setSlownessMultiplier(String t){
        this.slownessMultiplier = calcSlownessMultiplier(t);
    }

    /**
     * @param t Tyre
     * @return  if Tyre String parameter is correct
     */
    private boolean allowedTyre(String t) {
        if (t == "Hard" || t == "Medium" || t == "Soft") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param t Tyre string
     * @return slowness multiplier
     */
    private double calcSlownessMultiplier(String t){
        switch (t) {
            case "Soft":
                return 1;

            case "Medium":
                return 1.011;

            case "Hard":
                return 1.03;

            default:
                return 1.015;
        }
    }
}
