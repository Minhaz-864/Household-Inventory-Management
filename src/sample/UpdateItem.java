package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UpdateItem {

    String type,name,quantity,estimated;
    List<String> str = new ArrayList<>();
    BufferedWriter writer;
    BufferedWriter writerAgain;
    BufferedReader BR;
    String Full_line;


    public UpdateItem(){

    }
    public UpdateItem(String type, String name, String quantity, String estimated){
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.estimated = estimated;

    }

    /**************************************************************************
     * **READS EVERY LINE FROM ITEMS.TXT FILE ; EVERY LINE IS A DIFFERENT.*****
     * **ENTRY WE ARE READING EVERY LINE AND PUSHING THAT BACK TO CONTROLLER***
     * **CLASS WHERE LISTVIEW SHOWS THE ITEMS LIST WE PASS FROM THIS METHOD.***
     * ************************************************************************/
    public List<String> viewItem(){
        File file = new File("src/items.txt");
        String line;
        Full_line = "";
        List<String> str = new ArrayList<>();
        try {
            BR = new BufferedReader(new FileReader(file));
            while (( line = BR.readLine()) != null){ //Wont run if there is line left

                Full_line = Full_line.concat(line); //reads every single line and put it into a string
                str.add(Full_line);  //enters full string to str list

                Full_line=""; //clears variable to be free for next line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str ;
    }

    /**
     * STRING PASSED FROM CONTROLLER CLASS UPON SELECTION OR WRITTEN
     * IS PASSED TO THIS METHOD. WHERE DATA SENT FROM VIEWITEM METHOD
     * AGAIN STORES HERE.
     * **/
    public void DelItem(String s) throws Exception {


        List<String> store = viewItem();
        s = s.replaceAll(" ", "");
        char c = s.charAt(0);

        if (store.removeIf(n -> ((n.charAt(0) == c)))) { //checking the first letter to match passed string
            System.out.println("Deleting and Rewriting data");

            writer = new BufferedWriter(new FileWriter("src/items.txt"));
            writer.write("");//writes a blank space to the file replacing the data into it
            writer.close();      //thus clears out the whole items.txt file
            writerAgain = new BufferedWriter(new FileWriter("src/items.txt", true));//if true, can append
            for (String element : store) { //runs in loop as the length of "store"
                System.out.println(element);
                writerAgain.append(element); //appends new data stored in "store" variable
                writerAgain.append("\n");//prepared for next line in a new line in listview
            }
            writerAgain.close();
            System.out.println("Done");
        } else {
            System.out.println("data not found"); //if for some reason "store" is blank will show this message
        }
    }


    /***********************************************************
     * ***SEARCH METHOD FROM CONTROLLER CLASS USES THIS CLASS***
     * ***TO DETERMINE POSITION OF THE GIVEN STRING IF EXIST****
     * *********************************************************/
    public int searched(String s) throws IOException {
        List<String> searches; //GETS THE WHOLE LIST OF READ FROM FILE
        searches = viewItem();
        System.out.println(s);

        int RowNum=0;
        for(String x: searches){ //TAKES EACH ENTRY ONE BY ONE
            RowNum++;
            if(x.toLowerCase(Locale.ROOT).contains(s.toLowerCase(Locale.ROOT))){
                //CHECKS IF EVERY OTHER STRING CONTAINS OUR GIVEN SEARCH STRING
                return RowNum;
            }
        }
        return RowNum;
    }

    /*******************************************************
     * THIS METHOD DOES SAME AS THE VIEWITEM METHOD DOES.***
     * REMINDVIEW METHOD READS FROM FILE AND SHOW ENTRIES***
     * TO THE REMINDER LIST VIEW.***************************
     * *****************************************************/
    public List<String> RemindView(){
        List<String> remList = new ArrayList<>();
        String line;
        String dataLine="";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/remindersTxt.txt"));
            while (( line = bufferedReader.readLine()) != null){

                dataLine = dataLine.concat(line); //reads every single line and put it into a string
                remList.add(dataLine);  //enters full string to str list

                dataLine=""; //clears variable to be free for next line
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return remList;
    }
}
