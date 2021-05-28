package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class ControllerTest extends ApplicationTest {

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
    public void setName() throws NoSuchFieldException, IllegalAccessException {
        clickOn("#name");
        write("Apple");
        clickOn("#type");
        write("Food");
        clickOn("#quantity");
        write("2 kg");
        clickOn("#ttl");
        write("10");
        clickOn("#add");
        Controller controller = new Controller();

        assertNotNull(controller.Name);

    }

    @Test
    public void setQuantity() {
        clickOn("#name");
        write("Apple");
        clickOn("#type");
        write("Food");
        clickOn("#quantity");
        write("2 kg");
        clickOn("#ttl");
        write("10");
        clickOn("#add");
        Controller controller = new Controller();

        assertNotNull(controller.amount);
    }

    @Test
    public void setTimeLast() {
        clickOn("#name");
        write("Apple");
        clickOn("#type");
        write("Food");
        clickOn("#quantity");
        write("2 kg");
        clickOn("#ttl");
        write("10");
        clickOn("#add");
        Controller controller = new Controller();

        assertNotNull(controller.estimate);
    }

    @Test
    public void setType() {
        clickOn("#name");
        write("Apple");
        clickOn("#type");
        write("Food");
        clickOn("#quantity");
        write("2 kg");
        clickOn("#ttl");
        write("10");
        clickOn("#add");
        Controller controller = new Controller();

        assertNotNull(controller.Type);
    }

    @Test
    public void setSearch() {
        clickOn("#name");
        write("Apple");
        clickOn("#type");
        write("Food");
        clickOn("#quantity");
        write("2 kg");
        clickOn("#ttl");
        write("10");
        clickOn("#add");
        Controller controller = new Controller();

        assertNotNull(controller.searched);
    }


    @Test(expected = NullPointerException.class)
    public void writeItem() throws IOException {

        Controller controller = new Controller();
        controller.WriteItem();
    }

    @Test(expected = Test.None.class)
    public void deleteItem() throws Exception {
        Controller controller = new Controller();
        controller.DeleteItem();
    }

    @Test(expected = NullPointerException.class)
    public void Searched() throws IOException {
        clickOn("#searchText");
        write("Apple");

        Controller controller = new Controller();
        assertNotNull(controller.Searched());
    }
    @Test(expected = NullPointerException.class)
    public void reminderBox() throws IOException{
        Controller controller = new Controller();
        controller.reminderBox();
    }

    @Test
    public void initialize() {
    }
}