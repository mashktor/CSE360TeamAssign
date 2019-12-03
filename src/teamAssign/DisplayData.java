package teamAssign;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import teamAssign.NewDataSet;


public class DisplayData extends NewDataSet{

    @FXML   ListView table1;
    @FXML   ListView table2;
    @FXML   ListView table3;
    @FXML   ListView table4;
    @FXML   Label numOfEntries;
    @FXML   Label high;
    @FXML   Label low;
    @FXML   Label mean;
    @FXML   Label median;
    @FXML   Label mode;
    public Label realName;

    public void initialize() throws Exception {
        fillTable();
        fillAnalysis();
        realName.setText(id);
        
    }


    int fillAnalysis(){
        int sizeDataSet = dataSet.size();

        if(sizeDataSet == 0){
            return 0;
        }

        numOfEntries.setText(Integer.toString(sizeDataSet));
        high.setText(Float.toString(dataSet.get(0)));
        low.setText(Float.toString(dataSet.get(sizeDataSet - 1)));

        double sum = 0;
        for(int index = 0; index < sizeDataSet; index++){
            sum += dataSet.get(index);
        }
        DecimalFormat df = new DecimalFormat("###0.0");
        mean.setText(df.format(sum / sizeDataSet));

        float dataMedian = 0;
        if(sizeDataSet % 2 == 0){
            dataMedian = (dataSet.get(sizeDataSet / 2 - 1) +
                         dataSet.get(sizeDataSet / 2))
                         / 2;
        }
        else{
            dataMedian = dataSet.get((sizeDataSet/2));
        }
        median.setText(Float.toString(dataMedian));

        mode.setText(Float.toString(mode()));
        return 1;
    }

    private float mode(){
        float dataMode = 0;
        int maxCount = 0;
        int i, j;
        int sizeDataSet = dataSet.size();

        for (i = 0; i < sizeDataSet; ++i) {
            int count = 0;
            for (j = 0; j < sizeDataSet; ++j) {
                if (dataSet.get(j).equals(dataSet.get(i)))
                    ++count;
            }

            if (count > maxCount) {
                maxCount = count;
                dataMode = dataSet.get(i);
            }
        }

        return dataMode;
    }


    void fillTable(){
        int sizeDataSet = dataSet.size();
        int i = (sizeDataSet / 4);
        if(sizeDataSet % 4 > 0 ){
            i++;
        }
        List<Float> temp = new ArrayList<Float>();
        int j = 0;
        for(int k = 0; k < i; k++){
            temp.add(dataSet.get(j));
            j++;
        }
        ObservableList<Float> items1 = (ObservableList<Float>) FXCollections.observableArrayList(temp);
        table1.setItems(items1);
        temp.clear();

        if(sizeDataSet % 4 == 1 ){
            i--;
        }
        for(int k = 0; k < i; k++) {
            temp.add(dataSet.get(j));
            j++;
        }
        ObservableList<Float> items2 = (ObservableList<Float>) FXCollections.observableArrayList(temp);
        table2.setItems(items2);
        temp.clear();

        if(sizeDataSet % 4 == 2 ){
            i--;
        }
        for(int k = 0; k < i; k++) {
            temp.add(dataSet.get(j));
            j++;
        }
        ObservableList<Float> items3 = (ObservableList<Float>) FXCollections.observableArrayList(temp);
        table3.setItems(items3);
        temp.clear();

        while(j < sizeDataSet) {
            temp.add(dataSet.get(j));
            j++;
        }
        ObservableList<Float> items4 = (ObservableList<Float>) FXCollections.observableArrayList(temp);
        table4.setItems(items4);
        temp.clear();

    }

    @FXML
    MenuBar myMenuBar;

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
    public void newDataSetBtnClk(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

    public void displayGraphBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayGraph.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

    public void displayDistBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayDist.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

    public void errorLogBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("ErrorLog.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

    public void addSingleBtn(javafx.event.ActionEvent actionEvent) throws  IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddSingle.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        //this next block of code refreshes the "DisplayData" window after "enter" is clicked in AddSingle
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);
        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newDataSetScene);
        window.show();
    }

    public void deleteSingleBtn(javafx.event.ActionEvent actionEvent) throws  IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteSingle.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void appendDataBtn(javafx.event.ActionEvent actionEvent) throws  IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppendData.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
