package simulator;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

//  https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx

public class SceneController {

    private static HashMap<String, Pane> screenMap = new HashMap<>();
    private static Scene main;

    public SceneController(Scene main) {
        this.main = main;
    }

    protected static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected static void removeScreen(String name){
        screenMap.remove(name);
    }

    protected static void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}