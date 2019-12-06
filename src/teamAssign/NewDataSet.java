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
    @FXML public Label entryNotValid;
	@FXML public static String id = "";
	@FXML Label errNoName;
	public static float max, min;

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
            Parent parent = FXMLLoader.load(getClass().getResource("./MainPage.fxml"));
            Scene parentScene = new Scene(parent);

            // This line gets the stage information
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            window.setScene(parentScene);
            window.show();
       
    }

    /**
     * If user attempts to create an empty data set they will get a warning
     * making sure that is what they are attempting
     * @param actionEvent
     * @return int: will make sure that user inputs a name before they can create a data set
     * @throws IOException
     */
    public int btnCreateClk(javafx.event.ActionEvent actionEvent) throws IOException {
    	//empty name check
        if(name.getText().isEmpty()){
            errNoName.setText("Please enter a name for the Data Set");
            return 0;
        }
        else {
        	
        	//check for empty entry
            if(dataSet.size() == 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Empty Data Set Warning");
                alert.setHeaderText("You are creating an empty data set");
                alert.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                	
                	
                	//error checking 
                	if(error()) {
                		return 0;
                	}
                	
                	
                	
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
            	
            	if(error()) {
            		return 0;
            	}

            	if(outOfBounds()) {
            		return 0;
            	}
            	
            	
            	
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
    
    

    //check bounds and numerical value
    public boolean outOfBounds() throws IOException{
    	for(int index = 0; index < dataSet.size(); index++) {
    		if(dataSet.get(index) < min || dataSet.get(index) > max){
                //Send error to error list and cancel import
    			ErrorLog.addError("File contains out of bounds value.  " +
                        ":  Change bounds or remove invalid input.");
    			entryNotValid.setText("File closed. Remove invalid entry or change bounds and reload." +
                        " Error found at entry: " + (index + 1));
                dataSet.clear();
                return true;
        	}
    	}
    	return false;
    }
    
    
    //check for valid float input 
    public boolean error() {
    	max = 100.0f;
    	if(!topBound.getText().isEmpty() && !lowBound.getText().isEmpty()) {
    		try {
    			max = Float.parseFloat(topBound.getText());
    			min = Float.parseFloat(lowBound.getText());
    			if(max <= min) {
    				try {
    					ErrorLog.addError("Max must be larger than min  :  Try entering new bounds values.");
    					entryNotValid.setText("Upper bound must be larger than lower bound.");
    					return true;
    				}catch(IOException ex) {
    					System.out.println("Should never happen");
    				}
    				
    			}
    		}catch(NumberFormatException e) {
    			try{
    					ErrorLog.addError("Bounds contain non numerical value.  :  Try entering numerical values.");
    					 Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
    		                alert1.setTitle("Please enter numerical values for your bounds.");
    		                alert1.setHeaderText("If numerical values are not provided default values will be used.");
    		                alert1.setContentText("Click okay to continue with default bounds or cancel to reset bounds.");

    		                Optional<ButtonType> result1 = alert1.showAndWait();
    		                if (result1.get() == ButtonType.OK){
    		                	
    		                } else {
    		                    return true;
    		                }
    				}catch(IOException ex) {
    					System.out.println("Should never happen");
    				}
    			}
        }else if(!topBound.getText().isEmpty() && lowBound.getText().isEmpty()) {
        	try {
        		max = Float.parseFloat(topBound.getText());
        	}catch(NumberFormatException e) {
    			try{
					ErrorLog.addError("Upper Bound contains non numerical value.  :  Try entering a numerical value.");
					 Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
		                alert2.setTitle("Please enter numerical value for your upper bound.");
		                alert2.setHeaderText("If a numerical value is not provided the default value will be used.");
		                alert2.setContentText("Click okay to continue with default upper bound or cancel to reset bounds.");

		                Optional<ButtonType> result2 = alert2.showAndWait();
		                if (result2.get() == ButtonType.OK){
		                	
		                } else {
		                    return true;
		                }
				}catch(IOException ex) {
					System.out.println("Should never happen");
				}
			}
        }else if(topBound.getText().isEmpty() && !lowBound.getText().isEmpty()) {
        	try {
        		min = Float.parseFloat(lowBound.getText());
        	}catch(NumberFormatException e) {
    			try{
					ErrorLog.addError("Lower Bound contains non numerical value.  :  Try entering a numerical value.");
					 Alert alert3 = new Alert(Alert.AlertType.CONFIRMATION);
		                alert3.setTitle("Please enter numerical value for your lower bound.");
		                alert3.setHeaderText("If a numerical value is not provided the default value will be used.");
		                alert3.setContentText("Click okay to continue with default lower bound or cancel to reset bounds.");

		                Optional<ButtonType> result3 = alert3.showAndWait();
		                if (result3.get() == ButtonType.OK){
		                	
		                } else {
		                    return true;
		                }
				}catch(IOException ex) {
					System.out.println("Should never happen");
				}
			}
        }
    	
    	return false;
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
        }else {
        	
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
    private boolean openFile(File file) throws FileNotFoundException, IOException {
    	dataSet.clear();
    	id = name.getText();

        Scanner data = new Scanner(file);
        String [] temp;
        int i = 0;
        while(data.hasNextLine()) {
              temp = data.nextLine().split(",");
            for(int j = 0; j < temp.length; j++){
            	try {
            		Float.parseFloat(temp[j]);
            	}catch(NumberFormatException e) {
            		ErrorLog.addError("File contained non numerical value.  :  Erase non numerical values from file.");
                    entryNotValid.setText("File contains non numerical value. File unloaded.");
                    dataSet.clear();
                    return false;
            	}
                dataSet.add(Float.parseFloat(temp[j]));
                System.out.println(dataSet.get(i));
                i++;
            }
        }
        success = true;
        return success;
    }

}
