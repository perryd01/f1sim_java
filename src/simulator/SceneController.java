package simulator;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;


/**
 * Scene controller class
 * Makes changing FXML scenes is easier
 * @see <a href="stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx">Stackoverflow</a>
 */
public class SceneController {

    /**
     * HashMap containing scenes
     */
    private static final HashMap<String, Pane> screenMap = new HashMap<>();
    /**
     * Primary Scene used to modify
     */
    private static Scene main;

    /**
     * Sets main Scene
     * @param main Primary Scene
     */
    public static void setMain(Scene main) {
        SceneController.main = main;
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