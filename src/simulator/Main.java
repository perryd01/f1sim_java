package simulator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        Application.launch();
        System.out.println("hello");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("res/menu.fxml"));

        primaryStage.setTitle("F1 sim");
        Scene scene = new Scene(root, 800, 600);

        SceneController sceneController = new SceneController(scene);

        SceneController.addScreen("menu", FXMLLoader.load(getClass().getResource("res/menu.fxml")));
        SceneController.addScreen("game", FXMLLoader.load(getClass().getResource("res/game.fxml")));
        SceneController.addScreen("leaderboard", FXMLLoader.load(getClass().getResource("res/leaderboard.fxml")));
        //SceneController.activate("game");
        SceneController.activate("menu");

        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);

        primaryStage.show();
    }
}
