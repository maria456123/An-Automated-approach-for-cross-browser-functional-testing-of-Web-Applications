package Logics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.record.cf.FontFormatting;
import org.testng.ITestResult;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class TestCaseStatusTableViewForTailoredSuite extends Application{
	String tname;
	String tstatus;
	String bname;
	String treason;
	String fpoint;
	VBox vbox = new VBox();

	TestCaseDetailsGettersSetters details=new TestCaseDetailsGettersSetters();
//	TableColumn  browsername,testcasename,testcasestatus;
	ObservableList<TestData> data =FXCollections.observableArrayList();
	TestData td;
	String[] parts ;
	String line;
	String applied="Applied";
	String tailoredSuiteReport=System.getProperty("user.dir")+"\\src\\Logics\\TailoredSuiteReport";
	@SuppressWarnings("unchecked")
	@Override
	public void start(final Stage stage) throws IOException 
	{
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 50, 50, 60));
		Label label = new Label("Tailored Suite Execution Report");
		Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20);
		label.setFont(font);
		TableView<TestData> table = new TableView<TestData>();
		BufferedReader reader = new BufferedReader(new FileReader(tailoredSuiteReport));
		while (true) {
			line = reader.readLine();
			if (line == null) {
				break;
			}	
			parts = line.split("__");	
			bname=parts[0];
			tname=parts[1];
			tstatus=parts[2];
			treason=parts[3];
			fpoint=parts[4];
			String heuristicStatus=applied;
			data.add(new TestData(bname,tname,tstatus,treason,fpoint,heuristicStatus));  
			//System.out.println("bname:"+bname+"testcasename:"+tname+"tstatus:"+tstatus+"treason:"+treason+"fpoint:"+fpoint);
			
		}
		for (Object o : data) {
			table.setItems(data);

		}
		TableColumn fileNameCol = new TableColumn("Browser Name");
		fileNameCol.setCellValueFactory(new PropertyValueFactory<>("browserName"));
		TableColumn pathCol = new TableColumn("TestCase Name");
		pathCol.setCellValueFactory(new PropertyValueFactory("testCaseName"));
		TableColumn sizeCol = new TableColumn("TestCase Status");
		sizeCol.setCellValueFactory(new PropertyValueFactory("testCaseStatus"));
		TableColumn sizeCol2 = new TableColumn("Reason of Failure");
		sizeCol2.setCellValueFactory(new PropertyValueFactory("size"));
		TableColumn sizeCol21 = new TableColumn("Heuristic Status");
		sizeCol21.setCellValueFactory(new PropertyValueFactory("heuristicStatus"));
		TableColumn sizeCol31 = new TableColumn("FailurePoint");
		sizeCol31.setCellValueFactory(new PropertyValueFactory("Failure_LOC"));
		table.getColumns().addAll(fileNameCol, pathCol, sizeCol,sizeCol2,sizeCol21,sizeCol31);
		ObservableList<String> list = FXCollections.observableArrayList();
		table.setItems(data);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.setMaxSize(1650, 1300);
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 50, 50, 60));
		vbox.getChildren().addAll(label, table);
		Scene scene = new Scene(vbox, 800, 400);
		stage.setTitle("Tailored Test Suite Execution Report");
		stage.setScene(scene);
		stage.show();
		reader.close();
	}
//	public static void main(String[] args) {
//		Application.launch(args);
//	}
}
