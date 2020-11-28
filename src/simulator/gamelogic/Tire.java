package simulator.gamelogic;

public class Tire {
    private String type;
    private int laps;
    private double slownessMultiplier = 1;

    public Tire(String t, int l) {
        if(allowedTyre(t)){
            this.setTyre(t);
            this.setLaps(l);
            setSlownessMultiplier(t);
        }

    }

    public Tire(int l){
        this.setTyre("Soft");
        this.setLaps(l);
    }

    Tire(){
        this.setLaps(2);
        this.setTyre("Soft");
    }

    public void setTyre(String t) {
        if(allowedTyre(t)){
            this.type = t;
        }
    }

    public void incLaps() {
        this.laps++;
    }

    public void setLaps(int l){
        if(l >= 0){
            this.laps = l;
        }
        else{
            throw new Error("Bad lap number");
        }
    }

    public void resetLaps() {
        this.laps = 0;
    }

    public String getTyre(){
        return this.type;
    }

    public int getLaps(){
        return this.laps;
    }

    public double getSlownessMultiplier(){
        return this.slownessMultiplier;
    }
    private void setSlownessMultiplier(String t){
        this.slownessMultiplier = calcSlownessMultiplier(t);
    }

    private boolean allowedTyre(String t) {
        if (t == "Hard" || t == "Medium" || t == "Soft") {
            return true;
        } else {
            return false;
        }
    }
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
