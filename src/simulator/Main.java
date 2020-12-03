package simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {

        Application.launch();
        System.out.println("hello");





//        Track track = new Track(60, "Hungaroring", 50);
//        Car c1 = new Car("Mercedes", new Tire(2), 1);
//        Car c2 = new Car("Sauber", new Tire("Hard", 2), 3);
//        track.participantCars.add(c1);
//        //track.participantCars.add(c2);
//        //track.participantCars.add(new Car("BMW", new Tire(4)));
//        //track.participantCars.add(new Car("Quick", new Tire(0)));
//        track.go();


    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //Controller ctrl = new Controller();

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));


        //Parent game = FXMLLoader.load(getClass().getResource("game.fxml"));

        primaryStage.setTitle("F1 sim");
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        SceneController sceneController = new SceneController(scene);

        SceneController.addScreen("menu", FXMLLoader.load(getClass().getResource("menu.fxml")));
        SceneController.addScreen("game", FXMLLoader.load(getClass().getResource("game.fxml")));
        SceneController.activate("menu");

        //Controller.setMyname();

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(700);
        primaryStage.show();






    }
}
