package teamAssign;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static teamAssign.Main.dataSet;

public class DeleteSingle {
	
	@FXML Label entryNotValid;

    @FXML
    private TextField input;
    @FXML
    private ListView view;
    @FXML
    private Button cancelButton;
    @FXML
    private Button delete;
    private float grade;
    public boolean populateTable(ActionEvent event) throws IOException
    {
    	entryNotValid.setText("");
        //gets the user input from the TextField and parses it to a float
        ArrayList<Float> temp = new ArrayList<>();
        if(!input.getText().isEmpty()) {
        	
        	//try parse
        	try {
            	grade = Float.parseFloat(input.getText());
            }catch(NumberFormatException e) {
            	view.getItems().clear();
            	view.getSelectionModel().clearSelection();
            	ErrorLog.addError("Did not enter numerical value for deletion.  :  Try entering a numerical value.");
                entryNotValid.setText("Not a valid Entry.");
                return false;
            }
        }
        
        //check for inbounds input
        if(grade < NewDataSet.min || grade > NewDataSet.max) {
        	ErrorLog.addError("Input is outside of bounds.  :  Enter valid input.");
        	entryNotValid.setText("Input out of bounds.");
        	return false;
        }
        
        
        //loop searches for all the grades in the ArrayList
        for(int i = 0; i < dataSet.size(); i++)
        {
            if(grade == dataSet.get(i))
            {
            	temp.add(dataSet.get(i));
            }
        }
        ObservableList<Float> matches = (ObservableList<Float>) FXCollections.observableArrayList(temp);
        if(matches.isEmpty() && !input.getText().isEmpty()) {
        	entryNotValid.setText("Input not found in data set.");
        	return false;
        }
        view.setItems(matches);
        temp.clear();
        return true;
    }
    
    
    
    public void deleteButtonClicked(ActionEvent event) throws IOException
    {
        float temp = (float) view.getSelectionModel().getSelectedItem();

        int i = 0;
        boolean done = false;
        float value;
        do
        {
            value = dataSet.get(i);
            if(temp == value)
            {
                dataSet.remove(i);
                done = true;
            }
            i++;
        } while(!done);

        //"closes" out the window when enter is hit
        delete.getScene().getWindow().hide();

    }

    public void cancelButtonClicked(ActionEvent event) throws IOException
    {
        //closes out the window
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}