package teamAssign;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import teamAssign.DisplayData;

public class NewDataSet extends Main{


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

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
        stage.show();
        stage.close();
    }

    // todo: What happens to file goes here
    private void openFile(File file) throws FileNotFoundException {
        Scanner data = new Scanner(file);
        String [] temp = new String[0];
        int i = 0;
        while(data.hasNextLine()) {
              temp = data.nextLine().split(",");
            for(int j = 0; j < temp.length; j++){
                dataSet.add(Float.parseFloat(temp[j]));
                System.out.println(dataSet.get(i));
                i++;
            }
        }
        Collections.sort(dataSet, Collections.reverseOrder());
    }

}
