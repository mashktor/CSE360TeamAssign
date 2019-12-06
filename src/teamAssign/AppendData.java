package teamAssign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.stage.Stage;

import static teamAssign.Main.dataSet;



public class AppendData
{
	
	@FXML Label entryNotValid1;
	@FXML Label entryNotValid2;
    @FXML
    private Button browse;
    @FXML
    private Button cancelButton;
    @FXML
    MenuBar myMenuBar;

    /**
     * @param actionEvent
     * @throws Exception
     * This method opens a file chooser to allow the user to select a file
     */
    @FXML
    public int openBrowseBtn( ActionEvent actionEvent) throws Exception
    {
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
            if(!openFile(file)) {
            	return 0;
            }
        }
        stage.show();
        stage.close();

        Collections.sort(dataSet, Collections.reverseOrder());
        //"closes" the window after file is chosen
        browse.getScene().getWindow().hide();
        return 1;
        

    }

    /**
     * @param file
     * @throws Exception
     * This method opens the file the user has chosen and appends the data from the file
     * into the "dataSet" ArrayList
     */
    private boolean openFile(File file) throws Exception
    {
    	List<Float> dataSetTemp = new ArrayList<Float>();
        Scanner data = new Scanner(file);
        String [] temp;
        int i = 0;
        int countLines = 1;
        while(data.hasNextLine()) {
            temp = data.nextLine().split(",");
            for(int x = 0; x < temp.length; x++) {
            	try {
            		Float.parseFloat(temp[x]);
            	}catch(NumberFormatException e) {
            		ErrorLog.addError("File contained non numerical value.  :  Erase non numerical values from file.");
                    entryNotValid1.setText("File contains non numerical value,");
                    entryNotValid2.setText("remove non numerical values to append.");
                    return false;
            	}
            	if(Float.parseFloat(temp[x]) > NewDataSet.max || 
            			Float.parseFloat(temp[x]) < NewDataSet.min) {
            		ErrorLog.addError("File contains out of bounds float.  :  Remove invalid input."+
                            " Error found on line: " + countLines + " entry : " +(x + 1));
            		entryNotValid1.setWrapText(true);
            		entryNotValid1.setTextAlignment(TextAlignment.JUSTIFY);
            		entryNotValid1.setText("File closed. Remove invalid entry, ");
            		entryNotValid2.setText("or change bounds and reload.");
            		return false;
            	}
            }
            
            for(int j = 0; j < temp.length; j++){
            		dataSetTemp.add(Float.parseFloat(temp[j]));
                    System.out.println(dataSetTemp.get(i));
                    i++;
            }
            countLines++;
        }
        dataSet.addAll(dataSetTemp);
        return true;
    }

    /**
     * @param event
     * @throws IOException
     * This method closes the window when the cancel button is clicked
     */
    public void cancelButton(ActionEvent event) throws IOException
    {
        //closes out the window when cancel button is hit
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }
}