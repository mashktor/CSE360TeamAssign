package teamAssign;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;






public class MainPage extends Main{

    @FXML MenuBar myMenuBar;
    @FXML MenuItem displayData;
    @FXML MenuItem displayGraph;
    @FXML MenuItem displayDist;
    @FXML MenuItem appendData;
    @FXML MenuItem addSingle;
    @FXML MenuItem deleteSingle;
    @FXML MenuItem userGuide;
    @FXML MenuItem errorLog;

    /**
     * Initializes the first scene by disabling the menuitems when there is no dataset
     */
    public void initialize() throws IOException{
        if(success == false){
            displayData.setDisable(true);
            displayGraph.setDisable(true);
            displayDist.setDisable(true);
            appendData.setDisable(true);
            addSingle.setDisable(true);
            deleteSingle.setDisable(true);
        }
        
        ErrorLog.clearError();
    }


    /**
     * Opens error log from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    protected void errorLogMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ErrorLog.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    /**
     * Displays Graph from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    protected void displayGraphMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayGraph.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    /**
     * Displays Distribution from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    protected void displayDistMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayDist.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    /**
     * Displays Data from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    protected void displayDataMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    /**
     * Displays create a new dataset from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    protected void onLoadFileMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
    	ErrorLog.clearError();
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    };

    /**
     * opens window to delete single entry from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void deleteSingleMenuBtn(javafx.event.ActionEvent actionEvent) throws  IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteSingle.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    /**
     * opens window to add single entry from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    protected void AddSingleMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSingle.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    };

    /**
     * opens window to append single entry from the menubar
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    protected void OnSetAppendDataMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppendData.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    };

    /**
     * Opens our user manual from the menubar
     * @param actionEvent
     */
    @FXML
    protected void userGuide(javafx.event.ActionEvent actionEvent) throws IOException{
        File userMan = new File("UserDocument.pdf");
        Desktop.getDesktop().open(userMan);
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * opens NewDataSet page to create a net data set
     * @param actionEvent
     * @throws IOException
     */
    public void newDataSetBtnClk(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }
}
