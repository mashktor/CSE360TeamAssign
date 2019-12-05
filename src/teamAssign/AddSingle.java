package teamAssign;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

import static teamAssign.NewDataSet.max;
import static teamAssign.NewDataSet.min;

import static teamAssign.Main.dataSet;
import static teamAssign.ErrorLog.addError;
public class AddSingle
{
    @FXML TextField gradeEntry;
    @FXML private Button cancelButton;
    @FXML private Button enter;
    @FXML Label entryNotValid;

    /**
     * @param actionEvent
     * @throws IOException
     * This method updates the ArrayList of data and the table in DisplayData window
     * when an input is entered into the TextField and a the enter button is clicked.
     */
    //Todo: Error catching is done, we now just have to send the message with time stamp to error log
    public void enterButtonClicked(ActionEvent actionEvent) throws IOException
    {
        try {
            Float.parseFloat(gradeEntry.getText());
            float grade = Float.parseFloat(gradeEntry.getText());

                if(grade > max || grade < min){
                    throw new Exception();
                }

            //adds the input to the "dataSet" ArrayList
            dataSet.add(grade);
            //sorts the dataSet after new data is inputed
            Collections.sort(dataSet, Collections.reverseOrder());

            //"closes" out the window when enter is hit
            enter.getScene().getWindow().hide();
        }
        catch (NumberFormatException e) {
            //Not an integer
        	ErrorLog.addError("Did not enter numerical value for new grade.  :  Try entering a numerical value.");
            entryNotValid.setText("Not a valid Entry");
        }
        catch(Exception e){
        	ErrorLog.addError("Value Entered is out of bounds.  :  Enter a value within the given bounds.");
            entryNotValid.setText("Entry out of bounds");
        }


    }

    /**
     * @param actionEvent
     * @throws IOException
     * This method closes the AddSingle window when the "cancel" button is clicked
     */
    public void cancelButtonClicked(ActionEvent actionEvent) throws IOException
    {
        //closes out the window
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}
