package simulator.gamelogic;


import javafx.event.Event;
import javafx.event.EventType;

public class RaceFinish extends Event {
    public static EventType<RaceFinish> raceFinishEventType = new EventType<RaceFinish>("end");

    Car winner;

    public RaceFinish(Car winner){
        super(raceFinishEventType);
        this.winner = winner;
    }

}