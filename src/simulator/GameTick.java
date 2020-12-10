package simulator;

import javafx.animation.Timeline;

import java.util.Timer;

public class GameTick {
    public static Timer gameticker = new Timer();
    public static Timer guiticker = new Timer();
    public static Timeline timeline = new Timeline();
    public static boolean gametickerRunning = false;
    public static boolean guitickerRunning = false;

    public static void stopAll(){
        gameticker.cancel();
        timeline.stop();
        gameticker = new Timer();
        gametickerRunning = false;
        guitickerRunning = false;
        System.out.println("Timers stopped");
    }

}

