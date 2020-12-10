package simulator;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import simulator.controller.GamePanelController;
import simulator.gamelogic.Track;
import simulator.gamelogic.Tyre;

import java.util.Random;

public class Game{
    private Track track;
    private int playerID;

    public Game(){}

    /**
     * Reset's track and gamestate
     */
    public void reInit() {
        //GameTick.stopAll();
        track.cancel();

    }

    /**
     * @param gpc GamePanelController
     */
    public void start(GamePanelController gpc) {
        track = new Track(40, "Hungaroring", 20);
        playerID = new Random().nextInt(20) + 1;
        track.addRandomCars(20);
        track.go();
        GameTick.guitickerRunning = true;
        GameTick.gametickerRunning = true;



        GameTick.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    gpc.setLaps(String.valueOf(track.participantCars.get(playerID).getLaps()), track.getLapNum());
                    gpc.setPlace(track.participantCars.get(playerID).getPlace());
                    gpc.setLaptime(track.participantCars.get(playerID).getLaptime());
                    gpc.setCarName(track.participantCars.get(playerID).getCarName() + " on:" + track.participantCars.get(playerID).getTyre());
                    gpc.setElapsedTime(track.getFirstCar().getTotalTimeString());
                    gpc.setPlace(
                            track.allFinished() ? "Finished" : "Gap to leader: " + (-1) * ((track.getFirstCar().getTotalTime() - track.participantCars.get(playerID).getTotalTime()))
                    );
                    gpc.pitReset();
                    if (track.participantCars.get(playerID).getLaps() == track.getLapNum())
                        gpc.pitButton.setDisable(true);
                    if (track.allFinished()) {
                        GameTick.stopAll();
                        String leaderboardData = track.participantCars.get(playerID).getCarName() + " - " + track.participantCars.get(playerID).getTotalTime() + " - " + track.participantCars.get(playerID).getLaps();
                        LeaderboardData.append(leaderboardData);
                    }
                })
        );

        GameTick.timeline.setCycleCount(Timeline.INDEFINITE);
        GameTick.timeline.play();

    }


    /**
     * Send's player's car to pit
     *
     * @param tyre Car Tyre
     */
    public void pit(Tyre tyre) {
        track.participantCars.get(playerID).pit(tyre);
    }

}
