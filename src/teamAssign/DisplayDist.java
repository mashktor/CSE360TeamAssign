package teamAssign;

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

import static teamAssign.Main.dataSet;
import static teamAssign.NewDataSet.max;
import static teamAssign.NewDataSet.min;

import teamAssign.NewDataSet;

import java.io.IOException;
import java.text.DecimalFormat;

public class DisplayDist {
	
	@FXML NumberAxis xAxis;
	@FXML CategoryAxis yAxis;
	@FXML BarChart graph;

	public void initialize()
	{
		displayDISTData();
	}
	
	public void displayDISTData()
	{
		if(min < 0)
		{
			min = -1 * min; //converts to positive
		}
				
		int range = (int) (max - min);
		
		int set1 = 0; 
		int set2 = 0; 
		int set3 = 0; 
		int set4 = 0; 
		int set5 = 0; 
		int set6 = 0; 
		int set7 = 0; 
		int set8 = 0; 
		int set9 = 0; 
		int set10 = 0; 
		
		int ninety = (int) (range * 0.90);
		int eighty = (int) (range * 0.80);
		int seventy = (int) (range * 0.70); 
		int sixty = (int) (range * 0.60);
		int fifty = (int) (range * 0.50);
		int forty = (int) (range * 0.40);
		int thirty = (int) (range * 0.30);
		int twenty = (int) (range * 0.20);
		int ten = (int) (range * 0.10);
		
		float sum1 = 0;
		float sum2 = 0;
		float sum3 = 0;
		float sum4 = 0;
		float sum5 = 0;
		float sum6 = 0;
		float sum7 = 0;
		float sum8 = 0;
		float sum9 = 0;
		float sum10 = 0;
		
		
		for(int i = 0; i < dataSet.size(); i++)
		{
			if(dataSet.get(i) < ten)//0-9%
			{
				sum1 += dataSet.get(i); 
				set1++; 
			}
			else if(dataSet.get(i) >= ten && dataSet.get(i) < twenty)//10-19%
			{
				sum2 += dataSet.get(i); 
				set2++; 
			}
			else if(dataSet.get(i) >= twenty && dataSet.get(i) < thirty)//20-29%
			{
				sum3 += dataSet.get(i); 
				set3++; 
			}
			else if(dataSet.get(i) >= thirty && dataSet.get(i) < forty)//30-39%
			{
				sum4 += dataSet.get(i); 
				set4++; 
			}
			else if(dataSet.get(i) >= forty && dataSet.get(i) < fifty)//40-49%
			{
				sum5 += dataSet.get(i); 
				set5++; 
			}
			else if(dataSet.get(i) >= fifty && dataSet.get(i) < sixty)//50-59%
			{
				sum6 += dataSet.get(i); 
				set6++; 
			}
			else if(dataSet.get(i) >= sixty && dataSet.get(i) < seventy)//60-69%
			{
				sum7 += dataSet.get(i); 
				set7++; 
			}
			else if(dataSet.get(i) >= seventy && dataSet.get(i) < eighty)//70-79%
			{
				sum8 += dataSet.get(i); 
				set8++; 
			}
			else if(dataSet.get(i) >= eighty && dataSet.get(i) < ninety)//80-89%
			{
				sum9 += dataSet.get(i); 
				set9++; 
			}
			else if(dataSet.get(i) >= ninety)//top 90%
			{
				sum10 += dataSet.get(i); 
				set10++; 
			}
		}
		
		
		float average1 = (sum1/set1) % 10;
		float average2 = (sum2/set2) % 10;
		float average3 = (sum3/set3) % 10;
		float average4 = (sum4/set4) % 10;
		float average5 = (sum5/set5) % 10;
		float average6 = (sum6/set6) % 10;
		float average7 = (sum7/set7) % 10;
		float average8 = (sum8/set8) % 10;
		float average9 = (sum9/set9) % 10;
		float average10 = (sum10/set10) % 10;
		
		xAxis.setLabel("Average Distribution (10% range)");
		yAxis.setLabel("10 percent Intervals"); 
		
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Data");

	   series1.getData().add(new XYChart.Data(average1, "0 to 9%"));
	   series1.getData().add(new XYChart.Data(average2, "10 to 19%"));
	   series1.getData().add(new XYChart.Data(average3, "20 to 29%"));
	   series1.getData().add(new XYChart.Data(average4, "30 to 39%"));
	   series1.getData().add(new XYChart.Data(average5, "40 to 49%"));
	   series1.getData().add(new XYChart.Data(average6, "50 to 59%"));
	   series1.getData().add(new XYChart.Data(average7, "60 to 69%"));
	   series1.getData().add(new XYChart.Data(average8, "70 to 79%"));
	   series1.getData().add(new XYChart.Data(average9, "80 to 89%"));
	   series1.getData().add(new XYChart.Data(average10, "90 to 100%"));
	   
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

    public void displayDataBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent newDataSetParent = FXMLLoader.load(getClass().getResource("DisplayData.fxml"));
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
}
