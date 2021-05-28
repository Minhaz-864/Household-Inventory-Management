package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Test;

import org.junit.After;
import org.junit.Before;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class MainTest extends ApplicationTest{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }
    @Before
    public void setUp() throws Exception{

    }
    @After
    public void tearDown () throws Exception{
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    @Test
    public void add(){
        clickOn("#name");
        write("Apple");
        clickOn("#type");
        write("Food");
        clickOn("#quantity");
        write("2 kg");
        clickOn("#ttl");
        write("10");
        clickOn("#add");
    }
    @Test
    public void addWithBlank(){
        clickOn("#name");
        write("");
        clickOn("#type");
        write("");
        clickOn("#quantity");
        write("");
        clickOn("#ttl");
        write("");
        clickOn("#add");
    }
    @Test
    public void deleteFromList() {
        //add functionality in GUI

        //delete functionality in GUI
        clickOn("#row");
        write("Apple, Food, 2 kg, 10");
        clickOn("#delete");

    }
    @Test
    public void deleteWritten(){
        //adding data again to delete later
        clickOn("#name");
        write("Banana");
        clickOn("#type");
        write("Food");
        clickOn("#quantity");
        write("20");
        clickOn("#ttl");
        write("10");
        clickOn("#add");
        clickOn("#row");
        write("Banana, Food, 20, 10");
        clickOn("#delete");
    }
    @Test
    public void searchFunc(){
        //search functionality
        clickOn("#searchText");
        write("Apple");
        clickOn("#search");
    }


}
