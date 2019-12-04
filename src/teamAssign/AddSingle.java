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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

import static teamAssign.Main.dataSet;

public class AddSingle
{
    @FXML
    TextField gradeEntry;
    @FXML
    private Button cancelButton;
    @FXML
    private Button enter;

    /**
     * @param actionEvent
     * @throws IOException
     * This method updates the ArrayList of data and the table in DisplayData window
     * when an input is entered into the TextField and a the enter button is clicked.
     */
    public void enterButtonClicked(ActionEvent actionEvent) throws IOException
    {

        //gets the user input and parses it to a float
        float grade =Float.parseFloat(gradeEntry.getText());
        //adds the input to the "dataSet" ArrayList
        dataSet.add(grade);
        //sorts the dataSet after new data is inputted
        Collections.sort(dataSet, Collections.reverseOrder());

        //"closes" out the window when enter is hit
        enter.getScene().getWindow().hide();

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
