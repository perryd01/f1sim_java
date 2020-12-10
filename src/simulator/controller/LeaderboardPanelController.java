package simulator.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import simulator.LeaderboardData;
import simulator.SceneController;

import java.io.IOException;

public class LeaderboardPanelController {

    @FXML
    public Text leaderboardText;
    @FXML
    public Button backButton;

    /**
     * Switches back to menu panel
     */

    @FXML
    public void initialize() throws IOException {
        String data = LeaderboardData.read("leaderboard.txt");
        if(!data.isEmpty()) leaderboardText.setText(data);
    }

    public void MenuBack(MouseEvent mouseEvent) {
        SceneController.activate("menu");
    }

    public void backToMenuButton(MouseEvent mouseEvent) {
        SceneController.activate("menu");
    }

    public void backToMenuEvent(javafx.event.ActionEvent actionEvent) {
        SceneController.activate("menu");
    }
}
