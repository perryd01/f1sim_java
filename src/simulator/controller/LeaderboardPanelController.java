package simulator.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import simulator.SceneController;

public class LeaderboardPanelController {

    /**
     * Switches back to menu panel
     * @param actionEvent
     */
    public void backToMenuEvent(ActionEvent actionEvent) {
        SceneController.activate("menu");
    }

    public void MenuBack(MouseEvent mouseEvent) {
        SceneController.activate("menu");
    }
}
