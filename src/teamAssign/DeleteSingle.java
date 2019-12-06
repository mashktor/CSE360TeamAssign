package teamAssign;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static teamAssign.Main.dataSet;

public class DeleteSingle {

    @FXML
    private TextField input;
    @FXML
    private ListView view;
    @FXML
    private Button cancelButton;
    @FXML
    private Button delete;
    public void populateTable(ActionEvent event) throws IOException
    {
        //gets the user input from the TextField and parses it to a float
        ArrayList<Float> temp = new ArrayList<>();
        float grade = Float.parseFloat(input.getText());
        //loop searches for all the grades in the ArrayList
        for(int i = 0; i < dataSet.size(); i++)
        {
            if(grade == dataSet.get(i))
            {
            	temp.add(dataSet.get(i));
            }
        }
        ObservableList<Float> matches = (ObservableList<Float>) FXCollections.observableArrayList(temp);
        view.setItems(matches);
        temp.clear();

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