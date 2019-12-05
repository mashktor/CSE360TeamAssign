package teamAssign;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import java.io.*;
import java.util.*;
import java.text.*;

public class ErrorLog extends Main{
	
	

    @FXML
    MenuBar myMenuBar;
    @FXML
    MenuItem displayData;
    @FXML MenuItem displayGraph;
    @FXML MenuItem displayDist;
    @FXML MenuItem appendData;
    @FXML MenuItem addSingle;
    @FXML MenuItem deleteSingle;
    @FXML TextArea errorArea;
    @FXML
    public void initialize() throws Exception {
    	initErrorArea();
        if(success == false){
            displayData.setDisable(true);
            displayGraph.setDisable(true);
            displayDist.setDisable(true);
            appendData.setDisable(true);
            addSingle.setDisable(true);
            deleteSingle.setDisable(true);
        }
    }
    
    
    //Initializes error text area
    public void initErrorArea() throws IOException{
    	Scanner scn = new Scanner(new File("errorMsg.txt"));
    		while(scn.hasNextLine()) {
    			errorArea.appendText(scn.nextLine() + "\n");
    		}
    		scn.close();
    	
    	
    }
    
    
    //addError method to create new error cases
    public static void addError(String errorMsg) throws IOException {
    	
    	//update errorMsg text file
    	BufferedWriter writer = new BufferedWriter(new FileWriter("errorMsg.txt", true));
    	writer.append(java.time.LocalDateTime.now().toString());
    	writer.append(" - " + errorMsg + "\n");
    	writer.close();
    	
    }
    
    //clear error log text file
    public static void clearError() throws IOException{
    	BufferedWriter writer = new BufferedWriter(new FileWriter("errorMsg.txt", false));
    	writer.flush();
    	writer.close();
    }

    @FXML
    protected void errorLogMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("ErrorLog.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    @FXML
    protected void displayGraphMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayGraph.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    @FXML
    protected void displayDistMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayDist.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    @FXML
    protected void displayDataMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    };

    @FXML
    protected void onLoadFileMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    };

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


//////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void newDataSetBtnClk(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }
    
    @FXML
    public void clearErrorLog(javafx.event.ActionEvent actionEvent) throws IOException{
    	
    	clearError();
    	
    	
    	Parent newErrorLogParent = FXMLLoader.load(getClass().getResource("ErrorLog.fxml"));
    	Scene newErrorLogScene = new Scene(newErrorLogParent);
    	
    	Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
    	
    	window.setScene(newErrorLogScene);
        window.show();
    }
}
