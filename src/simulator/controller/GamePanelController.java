package simulator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import simulator.Game;
import simulator.GameTick;
import simulator.SceneController;
import simulator.gamelogic.Tyre;

public class GamePanelController {
    public Text laptime;

    public Group softTyre;
    public Group mediumTyre;
    public Group hardTyre;
    public Text currentTyre;

    public Button pitButton;
    public Text elapsedTime;
    public Text laps;
    public Text place;
    public Button raceStartButton;

    public Text carName;

    private String selectedTyre;

    private String prevLapNumber = null;

    private Game game = new Game();

    /**
     * Set's laptime in a '{min}:{sec}:{ms}' format in GUI
     * @param laptime laptime in double
     */
    public void setLaptime(double laptime){
        int min = (int) (laptime / 60);
        int sec = (int) (laptime - min*60);
        int ms =  (int) (((laptime - min*60) - sec) * 1000);
        this.laptime.setText("Laptime: " + min + ":" + sec + ":" + ms);
    }

    /**
     * Sets Elapsed time on GUI
     * @param et Elapsed time
     */
    public void setElapsedTime(String et){
        elapsedTime.setText("Elapsed time: "+ et);
    }

    /**
     * Sets laps in GUI '{laps}/{fulllaps}'
     * @param laps laps, the player car has already completed
     * @param fullLaps laps, the player car has to complete
     */
    public void setLaps(String laps, int fullLaps){
        this.laps.setText(laps + "/" + fullLaps);
    }

    /**
     * sets player car position
     * @param place player car position
     */
    public void setPlace(int place){this.place.setText(String.valueOf(place));}

    /**
     * sets player car position
     * @param s player car position in string
     */
    public void setPlace(String s){this.place.setText(s);}

    /**
     * sets carname (Mercedes2, Ferrari1)
     * @param cn carname String
     */
    public void setCarName(String cn){ this.carName.setText(cn);}


    /**
     * Changes scene to the Menu, and resets gamestate
     * @param actionEvent backToMenu Button Event
     */
    public void backToMenuEvent(ActionEvent actionEvent) {
        SceneController.activate("menu");
        resetGUI();
    }


    /**
     * Initalizes GUI in GamePanel
     */
    @FXML public void initialize() {
        laptime.setText("Laptime: 0000000");
        pitButton.setDisable(true);

    }


    /**
     * sets preferred tyre to hard
     * @param mouseEvent Hard/White Tyre button click
     */
    public void setHard(MouseEvent mouseEvent) {
        if(!pitButton.isDisabled()) {
            currentTyre.setText("Tyre selected: Hard");
            selectedTyre = "Hard";
        }
    }

    /**
     * sets preferred tyre to medium
     * @param mouseEvent Medium/Yellow Tyre button click
     */
    public void setMedium(MouseEvent mouseEvent) {
        if(!pitButton.isDisabled()) {currentTyre.setText("Tyre selected: Medium");
        selectedTyre = "Medium";}
    }

    /**
     * sets preferred tyre to soft
     * @param mouseEvent Soft/Red Tyre button click
     */
    public void setSoft(MouseEvent mouseEvent) {
        if(!pitButton.isDisabled()) {
            currentTyre.setText("Tyre selected: Soft");
            selectedTyre = "Soft";
        }
    }

    /**
     * Pressing Pit button on GUI. Disables button for one lap. Calls pit function on game logic.
     * @param mouseEvent pit button click
     */
    public void pitButtonAction(MouseEvent mouseEvent) {
        if(selectedTyre == null) {currentTyre.setText("Choose a tyre first"); return;}

        prevLapNumber = laps.getText();
        pitButton.setText("Pitting next lap!");
        pitButton.setStyle("-fx-background-color: lightgreen;");
        pitButton.setDisable(true);
        game.pit(new Tyre(selectedTyre, 0));
    }

    /**
     * resets GUI's tyre and pitting part
     */
    public void resetGUI(){
        pitButton.setDisable(true);
        pitButton.setStyle(null);
        currentTyre.setText("tyre not selected");
        raceStartButton.setDisable(false);
    }


    /**
     * Changes scene to menu and resets current panel.
     * @param mouseEvent 'Back' button click event
     */
    public void backToMenuEventMouse(MouseEvent mouseEvent) {
        SceneController.activate("menu");
        resetGUI();
        if(GameTick.guitickerRunning && GameTick.gametickerRunning) {
            GameTick.stopAll();
            game.reInit();
        }
    }

    /**
     * Creates a game and starts it
     * @param mouseEvent Start button click event
     */
    public void raceStart(MouseEvent mouseEvent) {
        game = new Game();
        pitButton.setDisable(false);
        raceStartButton.setDisable(true);
        game.start(this);
    }


    /**
     * Reset's pitting part in GUI
     */
    public void pitReset() {
        if(prevLapNumber != null && !prevLapNumber.equals(laps.getText())) {
            pitButton.setStyle(null);
            //pitButton.setText("Pit this lap");
            pitButton.setDisable(false);
        }
    }
}
