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

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
        stage.show();
        stage.close();

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
        //puts the file into a buffer
        BufferedReader buffer = new BufferedReader(new FileReader(file));

        String input = "";
        //reads each line until the file is empty
        while((input = buffer.readLine()) != null)
        {
            //adds each line into the "dataSet" ArrayList
            dataSet.add(Float.parseFloat(input));
            //resorts the ArrayList with the new inputs
            Collections.sort(dataSet, Collections.reverseOrder());
        }
    }

    public void cancelButton(ActionEvent event) throws IOException
    {
        //closes out the window when cancel button is hit
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
