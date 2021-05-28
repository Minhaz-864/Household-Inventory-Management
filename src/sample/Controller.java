package sample;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.TextFieldListCell;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private DatePicker datePick;

    /***********************************************************
    * *********All Required Buttons are defined here!***********
    * ***Button id's are defined same as defined in FXML file***
    * **********************************************************/
    @FXML
    private Button remindThis;

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button search;

    /*********************************************
     * ***we have 2 individual Listview section***
     * ***which will display, select and**********
     * ***update info that we need to display*****
     * *******************************************/

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<String> reminderList;

    /*******************************************************************
    * *********All of the text field's required input adjacent**********
    * *********to its button are defined on this part and strings*******
    * *********that I have used to store data related to it also********
    * *********is in this section***************************************
    * ******************************************************************/
    /**Adding new product will required to have this four filed filled**/
    @FXML
    private TextField type;
    String Type = "";
    @FXML
    public TextField name;
    String  Name = "";
    @FXML
    private TextField quantity;
    String amount = "";
    @FXML
    private TextField ttl;
    String estimate = "";
    /****Deleting and entry from listview and storage****
     * **file will require this text field to be filled***/
    @FXML
    private TextField row;
    /**Searching a product specific goes on this text field**/
    @FXML
    private TextField searchText;
    String searched = "";
    /**Adding a reminder to remind about requires one to input an Subject which goes on this section**/
    @FXML
    private TextField remindTopic;

    /**SETTER METHODS**/
    public void setType(TextField type) {
        this.type = type;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public void setQuantity(TextField quantity) {
        this.quantity = quantity;
    }

    public void setTtl(TextField ttl) {
        this.ttl = ttl;
    }

    public void setSearchText(TextField searchText) {
        this.searchText = searchText;
    }

    /***************************************************************************************************
     * **FOUR TEXT FIELDS "NAME","TYPE","QUANTITY","TIME TO LAST IN DAYS" WHILE FILLED AND ADD BUTTON***
     * **HAS RECORDED AN INSERT BUTTON PRESS WILL INVOKE THIS METHOD. WHICH WILL WRITE THE INSERTED*****
     * **DATA TO THE STORAGE FILE ALONG WITH INCLUDING THAT IN THE LISTVIEW. THIS METHOD DOES***********
     * **1)READING ALL INSERTED VALUES. 2)ADDING ALL THOSE VALUE TO A SINGLE STRING IF NONE IS BLANK.***
     * **3)APPEND THE NEWLY INSERTED STRING TO THE FILE. 4)SHOWING UP INSERTED STRING TO THE LISTVIEW***
     * *************************************************************************************************/
    @FXML
    void WriteItem() throws IOException {
        Name = name.getText(); /**SYNTAX:: variable = textFiled.getText();**/
        amount = quantity.getText();
        estimate = ttl.getText();          /**(1)**/
        Type = type.getText();

        if(!(((Name.isBlank()== true ) && (amount.isBlank()== true) && (estimate.isBlank()== true) && (Type.isBlank()== true))
                && ((Name.trim().isBlank()==true) && (amount.trim().isBlank()==true)
                && (estimate.trim().isBlank()==true) && (Type.trim().isBlank()==true)))) { //check if data is blank or none
            String fullStr = Name + ", " + Type + ", " + amount + ", " + estimate;  /**(2)**/

            File file = new File("src/items.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); //
            writer.append(fullStr);    /**(3)**/
            writer.append("\n"); //Prepares the file to insert next string on a new line
            writer.close();

            listView.getItems().add(fullStr); /**shows up on the listview(4)**/

        }
        else {
            System.out.println("Wrong Input format");
        }

    }
    /*******************************************
     * DELETING A STRING THROUGH SELECTION******
     * OR TYPING ON THE TEXT FIELD WILL DELETE**
     * THAT ENTRY FROM FILE AND LISTVIEW********
     * *****************************************/

    @FXML
    void DeleteItem() throws Exception {
        //delete item
        try {
            String ListviewSelection = listView.getSelectionModel().getSelectedItem();
            this.row.setText(ListviewSelection); /**SELECTION OF A ENTRY FROM FILE ACCEPTS HERE**/

            File file = new File("src/items.txt");
            String delstr = "";
            delstr = row.getText();
            /**ON "DELETE" CLICK delstr VARIABLE GETS VALUE OF THE ENTRY TO BE DELETED
             * AND AGAIN WE PUSH THAT STRING TO DelItem METHOD TO PROCESS
             * **/
            if (delstr != null) {
                UpdateItem upi = new UpdateItem();
                upi.DelItem(delstr);/**PASSING THE TARGETED STRING TO DelItem METHOD IN UpdateItem CLASS*/
                listView.getItems().removeAll(delstr); /**DELETES ENTRY FROM LISTVIEW**/
            } else {
                throw new NullPointerException(); /**THROWS EXCEPTION ON BLANK CLICK**/
            }
        }catch (NullPointerException e){
            e.getMessage();
        }
    }
    /**HIGHLIGHTS THE SEARCH STRING IF PRESENT OR ELSE DOES NOTHING**/
    @FXML
    int Searched() throws IOException {

        UpdateItem upi = new UpdateItem();
        int rowNum = upi.searched(searchText.getText()); /**GETS ROW NUMBER FOR THE SEARCH STRING**/
        listView.getSelectionModel().select(rowNum-1); /**HIGHLIGHTS THE SEARCH STRING IF PRESENT**/
        return rowNum;
    }
    /**SETTING UP A REMINDER LISTVIEW TO SHOW A LIST OF USER DEFINED REMINDERS
     * WHICH TAKES TWO INPUTS TOPIC OF REMINDER AND DATE FOR THE REMINDER*****/
    @FXML
    void reminderBox() throws IOException{
        String remindData = remindTopic.getText();
        String dates = datePick.getValue().toString();

        BufferedWriter bfw = new BufferedWriter(new FileWriter("src/remindersTxt.txt",true));
        bfw.append(remindData+" , "+dates);
        reminderList.getItems().add(remindData+" , "+dates);
    }
    /**
    * this part handles what to be show on list view
    * up there listview is associated with this method
    * this has to be implemented somewhere other class
    *
    * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UpdateItem upi = new UpdateItem();
        /**REMINDERS CAN BE SELECTED AND EDITED AND EDITED STRING CAN BE STORED AFTERWARDS**/
        reminderList.setEditable(true); //MAKE THE LISTVIEW EDITABLE
        reminderList.setCellFactory(TextFieldListCell.forListView());
        reminderList.getItems().addAll(upi.RemindView());//ADDING ITEMS FROM REMINDERTXT.TXT FILE TO REMINDER LISTVIEW
        reminderList.getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                //OVERRIDING THE CHANGE LISTENER METHOD TO LISTEN TO THE CHANGES OVER DOUBLE CLICK ON A CELL
                UpdateItem upi = new UpdateItem();
                try (BufferedWriter bfw = new BufferedWriter(new FileWriter("src/remindersTxt.txt",true));)
                {
                    bfw.append(change.getList().toString());

                } catch (IOException exception) {
                    exception.getMessage();
                }
            }
        });

        listView.getItems().addAll(upi.viewItem()); //ADDS ITEM TO THE AVAILABLE ITEMS LIST FROM GIVEN FILE

        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //MAKES THE LISTVIEW SELECTABLE TO DELETE AND HIGHLIGHT

    }

}

