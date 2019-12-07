package teamAssign;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.application.Platform.exit;
import static teamAssign.Main.dataSet;
import static teamAssign.NewDataSet.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DisplayGraph {

	@FXML NumberAxis xAxis;
	@FXML CategoryAxis yAxis;
	@FXML BarChart graph;

	public static int set1 = 0;
	public static int set2 = 0;
	public static int set3 = 0;
	public static int set4 = 0;
	public static int set5 = 0;
	public static int set6 = 0;
	public static int set7 = 0;
	public static int set8 = 0;
	public static int set9 = 0;
	public static int set10 = 0;
	
	public void initialize() throws Exception
	{
		setData();
		createChart();
	}
	
	public static void setData()//splits the data into 10 sets (for each column)
	{	
		if(min < 0)
		{
			min = -1 * min; //converts to positive
		}
				
		int range = (int) (max - min);
		
		set1 = 0;
		set2 = 0;
		set3 = 0;
		set4 = 0;
		set5 = 0;
		set6 = 0;
		set7 = 0;
		set8 = 0;
		set9 = 0;
		set10 = 0;
		
		int ninety = (int) (range * 0.90);
		int eighty = (int) (range * 0.80);
		int seventy = (int) (range * 0.70); 
		int sixty = (int) (range * 0.60);
		int fifty = (int) (range * 0.50);
		int forty = (int) (range * 0.40);
		int thirty = (int) (range * 0.30);
		int twenty = (int) (range * 0.20);
		int ten = (int) (range * 0.10);
		
		for(int i = 0; i < dataSet.size(); i++)
		{
			if(dataSet.get(i) < ten)//0-9%
			{
				set1++; 
			}
			else if(dataSet.get(i) >= ten && dataSet.get(i) < twenty)//10-19%
			{
				set2++; 
			}
			else if(dataSet.get(i) >= twenty && dataSet.get(i) < thirty)//20-29%
			{
				set3++; 
			}
			else if(dataSet.get(i) >= thirty && dataSet.get(i) < forty)//30-39%
			{
				set4++; 
			}
			else if(dataSet.get(i) >= forty && dataSet.get(i) < fifty)//40-49%
			{
				set5++; 
			}
			else if(dataSet.get(i) >= fifty && dataSet.get(i) < sixty)//50-59%
			{
				set6++; 
			}
			else if(dataSet.get(i) >= sixty && dataSet.get(i) < seventy)//60-69%
			{
				set7++; 
			}
			else if(dataSet.get(i) >= seventy && dataSet.get(i) < eighty)//70-79%
			{
				set8++; 
			}
			else if(dataSet.get(i) >= eighty && dataSet.get(i) < ninety)//80-89%
			{
				set9++; 
			}
			else if(dataSet.get(i) >= ninety)//top 90%
			{
				set10++; 
			}
		}

	}

	public void createChart() {
		//for setting the xAxis and yAxis labels.
		xAxis.setLabel("Occurrances");
		yAxis.setLabel("Grades");


		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Data");

		series1.getData().add(new XYChart.Data(set1, "0 to 9%"));
		series1.getData().add(new XYChart.Data(set2, "10 to 19%"));
		series1.getData().add(new XYChart.Data(set3, "20 to 29%"));
		series1.getData().add(new XYChart.Data(set4, "30 to 39%"));
		series1.getData().add(new XYChart.Data(set5, "40 to 49%"));
		series1.getData().add(new XYChart.Data(set6, "50 to 59%"));
		series1.getData().add(new XYChart.Data(set7, "60 to 69%"));
		series1.getData().add(new XYChart.Data(set8, "70 to 79%"));
		series1.getData().add(new XYChart.Data(set9, "80 to 89%"));
		series1.getData().add(new XYChart.Data(set10, "90 to 100%"));

		graph.getData().addAll(series1);
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
    }

    @FXML
    protected void displayGraphMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayGraph.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    }

    @FXML
    protected void displayDistMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayDist.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    }

    @FXML
    protected void displayDataMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
        Scene parentScene = new Scene(parent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(parentScene);
        window.show();
    }

    @FXML
    protected void onLoadFileMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
    	ErrorLog.clearError();
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("NewDataSet.fxml"));
        Scene newDataSetScene = new Scene(newDataSetParent);

        // This line gets the stage information
        Stage window  = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(newDataSetScene);
        window.show();
    }

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
    }

    @FXML
    protected void OnSetAppendDataMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AppendData.fxml"));
        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

	@FXML
	protected void userGuideBtn(javafx.event.ActionEvent actionEvent) throws IOException  {
		String path = System.getProperty("user.dir")+"\\src\\teamAssign\\files\\UserDocument.pdf";
		File userMan = new File(path);
		Desktop.getDesktop().open(userMan);
	}

	@FXML
	protected void closeMenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
		exit();
	}



//////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void displayDataBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
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
}
