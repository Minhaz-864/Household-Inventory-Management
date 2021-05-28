package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); //Loades sample.fxml designer to the Parent.
        primaryStage.setTitle("Household Inventory Management"); //Title to show in header
        primaryStage.setScene(new Scene(root, 580, 410)); //loading the scene to the stage of specified initial size
        primaryStage.show(); //shows the stage on run!
    }


    public static void main(String[] args) throws Exception {
        launch(args); //launches the application
    }
}
