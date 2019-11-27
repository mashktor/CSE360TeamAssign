package teamAssign;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage {
    @FXML

    public void newDataSetBtnClk(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

//    public void newDataSetMenuClk(javafx.event.ActionEvent actionEvent) throws IOException {
//        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
//        Scene newDataSetScene = new Scene(newDataSetParent);
//
//        // This line gets the stage information
//        Stage window  = (Stage) (myMenuBar).getScene().getWindow();
//
//        window.setScene(newDataSetScene);
//        window.show();
//    }
}
