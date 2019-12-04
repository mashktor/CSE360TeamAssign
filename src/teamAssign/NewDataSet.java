package teamAssign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Import of static global variables
import static teamAssign.Main.dataSet;
import static teamAssign.Main.success;

/**
 * This class is used to create a new Data Set
 */
public class NewDataSet {

    /**
     * Variables used for data set descriptions
     */
    @FXML public TextField topBound;
    @FXML public TextField lowBound;
    @FXML public TextField name;
	@FXML public static String id = "";
	@FXML Label errNoName;

    public void initialize() throws Exception {
        dataSet.clear();
    }

    /**
     * If data set has already been created and cancel is pressed window will go to
     * display data, if dataset has not been created it will go to main page.
     * @param actionEvent
     * @throws IOException
     */
    public void btnCancelClk(javafx.event.ActionEvent actionEvent) throws IOException {

        //If data has been created go to DisplayData, Else go back to start Menu
        if(success == false) {
            Parent parent = FXMLLoader.load(getClass().getResource("./MainPage.fxml"));
            Scene parentScene = new Scene(parent);

            // This line gets the stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(parentScene);
            window.show();
        }
        else {
            Parent parent = FXMLLoader.load(getClass().getResource("./DisplayData.fxml"));
            Scene parentScene = new Scene(parent);

            // This line gets the stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(parentScene);
            window.show();
        }
    }

    /**
     * If user attempts to create an empty data set they will get a warning
     * making sure that is what they are attempting
     * @param actionEvent
     * @return int: will make sure that user inputs a name before they can create a data set
     * @throws IOException
     */
    public int btnCreateClk(javafx.event.ActionEvent actionEvent) throws IOException {
        if(name.getText().isEmpty()){
            errNoName.setText("Please enter a name for the Data Set");
            return 0;
        }
        else {
            if(success == false){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Empty Data Set Warning");
                alert.setHeaderText("You are creating an empty data set");
                alert.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
                    Scene newDataSetScene = new Scene(newDataSetParent);
                    // Sets success = true because we have created a dataset, it might be empty.
                    success = true;

                    // This line gets the stage information
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    window.setScene(newDataSetScene);
                    window.show();

                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
            else {
                Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
                Scene newDataSetScene = new Scene(newDataSetParent);
                // Sets success = true because we have created a dataset, it might be empty.
                success = true;

                // This line gets the stage information
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                window.setScene(newDataSetScene);
                window.show();

            }
        }

        return 1;
    }

    /**
     * Opens a browse window and asks user to find a file
     * the file browser is limited to .txt and .csv files only.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * We check for out of bounds entries and make sure the user will
     * be able to create data set with entries in a file.
     * returns success boolean variable to indicate if dataset has been created
     * @param file
     * @return success
     * @throws FileNotFoundException
     */
    //TODO: Error handling for out of bounds data entries
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
