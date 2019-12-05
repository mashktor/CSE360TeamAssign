package teamAssign;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

import static teamAssign.Main.dataSet;

public class DeleteSingle {

    @FXML
    private TextField input;


    public void populateTable(ActionEvent event) throws IOException
    {
        //gets the user input from the TextField and parses it to a float
        float grade = Float.parseFloat(input.getText());
        //loop searches for all the grades in the ArrayList
        for(int i = 0; i < dataSet.size(); i++)
        {
            if(grade == dataSet.get(i))
            {
            	
            }
        }
    }
}