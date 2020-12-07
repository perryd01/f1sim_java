package simulator;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;


/**
 * Scene controller class
 * Makes changing FXML scenes is easier
 * @see 'https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx'
 */
public class SceneController {

    /**
     * HashMap containing scenes
     */
    private static HashMap<String, Pane> screenMap = new HashMap<>();
    /**
     * Primary Scene used to modify
     */
    private static Scene main;

    /**
     * @param main Primary Scene
     */
    public SceneController(Scene main) {
        this.main = main;
    }

    /**
     * Adds screen to HashMap
     * @param name Scene name
     * @param pane location of FXML
     */
    protected static void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected static void removeScreen(String name){
        screenMap.remove(name);
    }

    /**
     * Activates scene
     * @param name Scene name
     */
    public static void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}