package simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

public class Controller {


    @FXML private Button startGame;
    @FXML private MenuItem exittab;
    @FXML private Button exitButton;
    @FXML private Text laptime;
    @FXML private Text myname;


    public void exitButtonAction(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void optionsEvent(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You don't want to set anything"  + " ?", ButtonType.NEXT, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
    }

    public void leaderBoardEvent(ActionEvent actionEvent) {}

    public void backToMenuEvent(ActionEvent actionEvent){
        SceneController.activate("menu");

    }

    @FXML public void startGameEvent(javafx.scene.input.MouseEvent mouseEvent) {

        SceneController.activate("game");
        setLaptime();
        setStartGame();
        setMyname();

    }

    public void aboutEvent(ActionEvent actionEvent) { }


    @FXML public void setLaptime(){
        if(laptime == null) {System.out.println("LAPTIMEnulla"); return;}
        laptime.setText("Laptime");
    }

    @FXML public void setStartGame(){
        startGame.setText("asd");
    }

    @FXML public void setMyname(){
        if(myname == null) {System.out.println("Mynamenulla"); return;}
        if(laptime == null) {System.out.println("laptime is null");return;}
        myname.setText("asd");
    }




}

