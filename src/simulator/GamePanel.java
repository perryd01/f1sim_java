package simulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GamePanel extends Application {

    Button button;



    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);

        primaryStage.setMaxHeight(700);
        primaryStage.setMaxWidth(1000);


        primaryStage.setOnCloseRequest((event) -> {
            System.out.println("Closing Stage");
        });

        primaryStage.setOnHiding((event) -> {
            System.out.println("Hiding Stage");
        });


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,  (event) -> {
            System.out.println("Key pressed: " + event.toString());

            switch(event.getCode().getCode()) {
                case 27 : { // 27 = ESC key
                    primaryStage.close();
                    break;
                }
                case 10 : { // 10 = Return
                    primaryStage.setWidth( primaryStage.getWidth() * 2);
                }
                default:  {
                    System.out.println("Unrecognized key");
                }
            }
        });


        primaryStage.show();


    }





}
