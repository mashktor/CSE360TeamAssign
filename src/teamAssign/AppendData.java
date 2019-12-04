package teamAssign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

import javafx.stage.Stage;

import static teamAssign.Main.dataSet;



public class AppendData
{
    @FXML
    private Button browse;
    @FXML
    private Button cancelButton;

    /**
     * @param actionEvent
     * @throws Exception
     * This method opens a file chooser to allow the user to select a file
     */
    public void openBrowseBtn( ActionEvent actionEvent) throws Exception
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
            openFile(file);
        }
        stage.show();
        stage.close();

        Collections.sort(dataSet, Collections.reverseOrder());
        //"closes" the window after file is chosen
        browse.getScene().getWindow().hide();

    }

    /**
     * @param file
     * @throws Exception
     * This method opens the file the user has chosen and appends the data from the file
     * into the "dataSet" ArrayList
     */
    private void openFile(File file) throws Exception
    {
        Scanner data = new Scanner(file);
        String [] temp;
        int i = 0;
        while(data.hasNextLine()) {
            temp = data.nextLine().split(",");
            for(int j = 0; j < temp.length; j++){
                dataSet.add(Float.parseFloat(temp[j]));
                System.out.println(dataSet.get(i));
                i++;
            }
        }
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