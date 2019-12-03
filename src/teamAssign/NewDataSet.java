package teamAssign;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import teamAssign.DisplayData;

public class NewDataSet extends Main{
	
	public TextField topBound;
	public TextField lowBound;
	public TextField name;
	public static String id = "";


    public void btnCancelClk(javafx.event.ActionEvent actionEvent) throws IOException {

        //Todo: If data has been created go to DisplayData, Else go back to start Menu
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("./MainPage.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

    public void btnCreateClk(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);


        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

    public void openBrowseBtn( ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();

        final FileChooser fileChooser = new FileChooser();

        // File filter to limit user to filetypes .txt, .csv
        FileChooser.ExtensionFilter txtFilter =
                new FileChooser.ExtensionFilter(
                        "TEXT files (*.txt)",
                        "*.txt");
        FileChooser.ExtensionFilter csvFilter =
                new FileChooser.ExtensionFilter(
                        "CSV files (*.csv)",
                        "*.csv");

        fileChooser.getExtensionFilters().add(txtFilter);
        fileChooser.getExtensionFilters().add(csvFilter);

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
        stage.show();
        stage.close();
    }

    // todo: What happens to file goes here
    private boolean openFile(File file) throws FileNotFoundException {
    	id = name.getText();

    	// Default value = 100,  if text field is not empty apply new value
    	float max = 100.0f;
    	if(!topBound.getText().isEmpty()) {
            max = Float.parseFloat(topBound.getText());
        }
    	//Default value = 0,  if text field is not empty apply new value
    	float min = 0.0f;
        if(!lowBound.getText().isEmpty()) {
            min = Float.parseFloat(lowBound.getText());
        }

        Scanner data = new Scanner(file);
        String [] temp;
        int i = 0;
        while(data.hasNextLine()) {
              temp = data.nextLine().split(",");
            for(int j = 0; j < temp.length; j++){
            	if(!(Float.parseFloat(temp[j]) >= min) || !(Float.parseFloat(temp[j]) <= max)){
                    //Send error to error list and cancel import
                    dataSet.clear();
                    return success;
            	}
                dataSet.add(Float.parseFloat(temp[j]));
                System.out.println(dataSet.get(i));
                i++;
            }
        }
        Collections.sort(dataSet, Collections.reverseOrder());
        success = true;
        return success;
    }

}
