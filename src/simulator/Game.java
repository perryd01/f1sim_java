package simulator;

import simulator.controller.GamePanelController;
import simulator.gamelogic.Track;
import simulator.gamelogic.Tyre;

import java.util.Random;
import java.util.TimerTask;

public class Game {
    private boolean isRunning = false;
    private Track track;
    private int playerID;

    public Game(){}

    public void reset(){
        track = null;
    }

    /**
     * Reset's track and gamestate
     * @throws Exception
     */
    public void reinit() throws Exception {
        isRunning = false;
        GameTick.stopAll();
        track.cancel();

    }

    /**
     * @param gpc GamePanelController
     * @throws Exception
     * Creates a track, adds 20 cars and make them start the race.
     * The TimerTask is for getting data from player's car and updating the GUI intervally.
     */
    public void start(GamePanelController gpc) throws Exception {
        track = new Track(40, "Hungaroring", 20);
        playerID = new Random().nextInt(20) + 1;
        track.addRandomCars(20);
        track.go();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                gpc.setLaps(String.valueOf(track.participantCars.get(playerID).getLaps()), track.getLapNum());
                gpc.setPlace(track.participantCars.get(playerID).getPlace());
                gpc.setLaptime(track.participantCars.get(playerID).getLaptime());
                gpc.setCarName(track.participantCars.get(playerID).getCarName() + " on:" + track.participantCars.get(playerID).getTyre());
                gpc.setElapsedTime(track.getFirstCar().getTotalTimeString());
                gpc.setPlace(
                        track.allFinished() ? "Finished" : "Gap to leader: " + (-1)*((track.getFirstCar().getTotalTime() - track.participantCars.get(playerID).getTotalTime()))
                );
                gpc.pitReset();
                if(track.participantCars.get(playerID).getLaps() == track.getLapNum()) gpc.pitButton.setDisable(true);
            }
        };

        GameTick.guiticker.schedule(timerTask, 100, 50);

        isRunning = true;
    }


    /**
     * Send's player's car to pit
     * @param tyre
     */
    public void pit(Tyre tyre){
        track.participantCars.get(playerID).pit(tyre);
    }




}
