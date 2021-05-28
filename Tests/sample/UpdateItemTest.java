package sample;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UpdateItemTest {



    @Test(expected = Test.None.class)
    public void viewItem() {
        UpdateItem updateItem = new UpdateItem();
        assertTrue(updateItem.Full_line == null);
        assertNotNull(updateItem.viewItem());
        updateItem.viewItem();
    }

    @Test(expected = Test.None.class)
    public void delItem() throws Exception {
        UpdateItem updateItem = new UpdateItem();
        updateItem.DelItem("Data");
    }

    @Test(expected = Test.None.class)
    public void searched() throws IOException {
        UpdateItem updateItem = new UpdateItem();
        assertNotEquals(updateItem.searched("Apple"),0);
    }

    @Test(expected = Test.None.class)
    public void remindView() {
        UpdateItem updateItem = new UpdateItem();
        updateItem.RemindView();
    }

}