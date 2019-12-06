package teamAssign;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import teamAssign.NewDataSet;

import static teamAssign.Main.dataSet;
import static teamAssign.Main.success;
import static teamAssign.NewDataSet.*;

public class DisplayData {

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
    @FXML   Label realName;

    public void initialize() throws Exception {
        Collections.sort(dataSet, Collections.reverseOrder());
        fillTable();
        fillAnalysis();
        realName.setText(id);
        teamAssign.DisplayGraph.setData();
        teamAssign.DisplayDist.displayDISTData();
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

    public void exportReport() throws IOException {
        DecimalFormat df = new DecimalFormat("###0.00");
        String title = "Grade Analysis Report";
        String name = realName.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt"));
        writer.write(title);
        writer.newLine();
        writer.write("-------------------------------------------------------------");
        writer.newLine();
        writer.write("Name:   " + realName.getText() +
                "    Boundary From:  " + teamAssign.NewDataSet.min +
                "   To:  " + teamAssign.NewDataSet.max);
        writer.newLine();
        writer.write("-------------------------------------------------------------");
        writer.newLine();
        writer.write("Data Set Analysis:");
        writer.newLine();
        writer.newLine();
        writer.write("Number of entries:  " + numOfEntries.getText());
        writer.newLine();
        writer.write("High Grade:         " + high.getText());
        writer.newLine();
        writer.write("Low Grade:          " + low.getText());
        writer.newLine();
        writer.write("Mean of Data Set:   " + mean.getText());
        writer.newLine();
        writer.write("Median of Data Set: " + median.getText());
        writer.newLine();
        writer.write("Mode of Data Set:   " + mode.getText());
        writer.newLine();
        writer.write("-------------------------------------------------------------");
        writer.newLine();
        writer.write("Grade Entries:");
        writer.newLine();
        int count = 0;
        for(int index = 0; index < dataSet.size(); index++){
            if(count / 5 == 1){
                count = 0;
                writer.newLine();
            }
            if(!(index == dataSet.size() - 1)) {
                writer.write(dataSet.get(index) + ",  ");
            }
            else{
                writer.write(dataSet.get(index) + "");
            }
            count++;
        }
        writer.newLine();
        writer.write("-------------------------------------------------------------");
        writer.newLine();
        writer.write("Occurrences of grade per 10%:");
        writer.newLine();
        writer.write("0% - 9%   :   " + DisplayGraph.set1);
        writer.newLine();
        writer.write("10% - 19% :   " + DisplayGraph.set2);
        writer.newLine();
        writer.write("20% - 29% :   " + DisplayGraph.set3);
        writer.newLine();
        writer.write("30% - 39% :   " + DisplayGraph.set4);
        writer.newLine();
        writer.write("40% - 49% :   " + DisplayGraph.set5);
        writer.newLine();
        writer.write("50% - 59% :   " + DisplayGraph.set6);
        writer.newLine();
        writer.write("60% - 69% :   " + DisplayGraph.set7);
        writer.newLine();
        writer.write("70% - 79% :   " + DisplayGraph.set8);
        writer.newLine();
        writer.write("80% - 89% :   " + DisplayGraph.set9);
        writer.newLine();
        writer.write("90% - 100%:   " + DisplayGraph.set10);
        writer.newLine();
        writer.newLine();
        writer.write("-------------------------------------------------------------");
        writer.newLine();
        writer.write("Average per interval of 10%:");
        writer.newLine();
        writer.write("0% - 9%   :   " + df.format(DisplayDist.average1));
        writer.newLine();
        writer.write("10% - 19% :   " + df.format(DisplayDist.average2));
        writer.newLine();
        writer.write("20% - 29% :   " + df.format(DisplayDist.average3));
        writer.newLine();
        writer.write("30% - 39% :   " + df.format(DisplayDist.average4));
        writer.newLine();
        writer.write("40% - 49% :   " + df.format(DisplayDist.average5));
        writer.newLine();
        writer.write("50% - 59% :   " + df.format(DisplayDist.average6));
        writer.newLine();
        writer.write("60% - 69% :   " + df.format(DisplayDist.average7));
        writer.newLine();
        writer.write("70% - 79% :   " + df.format(DisplayDist.average8));
        writer.newLine();
        writer.write("80% - 89% :   " + df.format(DisplayDist.average9));
        writer.newLine();
        writer.write("90% - 100%:   " + df.format(DisplayDist.average10));
        writer.newLine();
        writer.newLine();
        writer.write("-------------------------------------------------------------");

        writer.close();

        //text file, should be opening in default text editor
        String path = System.getProperty("user.dir")+"\\src\\report.txt";
        File file = new File("report.txt");

        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);

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
    	ErrorLog.clearError();
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    };

    @FXML
    public void deleteSingleMenuBtn(javafx.event.ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteSingle.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
        fillTable();
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

        fillTable();

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

        fillTable();
    };

    @FXML
    protected void exportReportBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        exportReport();
    }



//////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void newDataSetBtnClk(javafx.event.ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("If you continue, current data set will be " +
                "deleted and a new data set needs to be created");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
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

        //this next block of code refreshes the "DisplayData" window after "enter" is clicked in AddSingle
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);
        // This line gets the stage information
        Stage window  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(newDataSetScene);
        window.show();

    }

    public void appendDataBtn(javafx.event.ActionEvent actionEvent) throws  IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppendData.fxml"));
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
}
