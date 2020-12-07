package simulator.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import simulator.SceneController;


public class MenuPanelController {


    /**
     * Terminates program
     * @param actionEvent
     */
    public void exitButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void aboutEvent(ActionEvent actionEvent) {}

    public void optionsEvent(ActionEvent actionEvent) {}

    /**
     * Switches to game panel
     * @param mouseEvent
     */
    public void startGameEvent(MouseEvent mouseEvent) {
        SceneController.activate("game");
    }

    /**
     * Switches to leaderboard panel
     * @param mouseEvent
     */
    public void leaderboardButtonEvent(MouseEvent mouseEvent) {
        SceneController.activate("leaderboard");
    }
}
