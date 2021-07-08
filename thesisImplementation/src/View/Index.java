package View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.testng.TestRunner;

import Logics.TestCaseStatusTableView;
import Logics.TestNGRunner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
public class Index extends Application{
	File selectedDirectory;
	String filedata;
	private static String Path = "E:\\EC-WORKSPACE-thesis\\thesisImplementation\\src\\InputSuite\\";
	//private Desktop desktop = Desktop.getDesktop();
	String s2;
	GridPane grid_pane=new GridPane();
	Text select;
	String fname;
	BorderPane pane = new BorderPane();
	VBox paneCenter = new VBox();
	BorderPane pane_file = new BorderPane();
	VBox paneCenter_file = new VBox();
	BorderPane pane_browser = new BorderPane();
	HBox paneCenter_browser = new HBox();
	BorderPane pane_execute = new BorderPane();
	VBox paneCenter_execute = new VBox();
	VBox paneCenter_application = new VBox();
	Text file_name;
	Text application_name;
	String selected_dir;
	String selected_browser;
	static List<String> parabrowser;
	String selected_files;
	Button b_execute_file;
	@SuppressWarnings("rawtypes")
	TableView table;

	ObservableList<String> input_data =FXCollections.observableArrayList();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(final Stage stage) throws Exception 
	{	
        parabrowser=new ArrayList<String>();
		Label selectedapp =new Label(" Selected WebApplication: ");
		Label Success=new Label("Suite has been Executed Successfully");
		Label selectedtestSuite =new Label(" Selected TestSuite: ");
		Label selectedbrowser =new Label(" Selected Browsers:  ");
		stage.setTitle("Automated Approach for Cross Browser Functional Testing of Web Applications");
		Label lf=new Label(" Web Application: ");		
		final Button openMultipleButton = new Button("Browse");
		openMultipleButton.setOnAction(e -> {
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Web Application");
			File defaultDirectory = new File("c:/xampp/htdocs");
			chooser.setInitialDirectory(defaultDirectory);
			File selectedApplication = chooser.showDialog(stage);
			if(selectedApplication==null) {
				file_name=new Text("No Application is selected");
			}
			else {
				file_name=new Text(selectedApplication.getName().toString());
			}	
			paneCenter_file.getChildren().addAll(file_name);
		});
		//BrowserSelection
		Label l = new Label("Select Browser/Browsers: \n\n"); 
		ObservableList<String> st = FXCollections.observableArrayList("Chrome", "Firefox", "Edge" ,"Safari"); 
		ListView<String> listView = new ListView<>(st);	
		ComboBox c = new ComboBox(st); 
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//browser event
		EventHandler<ActionEvent> browserEvent = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 	
				s2=c.getSelectionModel().getSelectedItem().toString();
				if(c.isPressed()==false) {
					select=new Text(s2+",");
					selected_browser=s2;
					parabrowser.add(s2);
					setParabrowser(parabrowser);
					System.out.println("Get para browser: "+getParabrowser());
					paneCenter_browser.getChildren().addAll(select);
				}
				else {
					c.setDisable(true);
				}
			}
		}; 	
		c.setOnAction(browserEvent); 	 
		//TestsuiteSelection
		DirectoryChooser directoryChooser = new DirectoryChooser();
		Label l_application=new Label("Select TestSuite:");
		Button button = new Button("Browse");
		button.setOnAction(e -> {
			selectedDirectory = directoryChooser.showDialog(stage);
			//setSelectedDirectory(selectedDirectory);
			System.out.println("getfolder"+getFolder());
			if(selectedDirectory==null) {
				application_name=new Text("No directory is selected");
			}
			else {
				System.out.println(selectedDirectory.getAbsolutePath());
				try {
					listFilesForFolder(selectedDirectory);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				application_name=new Text(selectedDirectory.getName().toString());
				paneCenter_application.getChildren().addAll(application_name);
			}	
		});
		Button ViewReport = new Button("ViewReport");
        ViewReport.setOnAction(e -> {
        	try {
        		Stage s = new Stage();
        	    new TestCaseStatusTableView().start(s);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		b_execute_file=new Button("Execute");
		b_execute_file.setOnAction(e -> {
			try {
				TestNGRunner tnr=new TestNGRunner();
				tnr.testRunner();
				grid_pane.add(Success, 1, 9);
				grid_pane.add(ViewReport, 1,10);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});		
		grid_pane.setAlignment(Pos.CENTER);
		grid_pane.setHgap(5);
		grid_pane.setVgap(5);
		grid_pane.add(lf, 0, 1);
		grid_pane.add(openMultipleButton, 1, 1);
		grid_pane.add(l, 0, 2);
		grid_pane.add(c, 1, 2);	
		grid_pane.add(l_application,0,3);
		grid_pane.add(button,1,3);
		grid_pane.add(selectedtestSuite, 0, 5);
		grid_pane.add(paneCenter_application,1,5);
		grid_pane.add(selectedbrowser, 0, 6);
		grid_pane.add(paneCenter_browser, 1,6);	
		grid_pane.add(selectedapp, 0, 7);
		grid_pane.add(paneCenter_file,1,7);
		grid_pane.add(b_execute_file,1,8);
		
		ScrollPane scrollPane = new ScrollPane(grid_pane);
		scrollPane.setPrefHeight(400);
		paneCenter.setSpacing(20);
		paneCenter.setPadding(new javafx.geometry.Insets(100));
		pane.setCenter(paneCenter);
		paneCenter.getChildren().addAll(grid_pane);
		Scene scene= new Scene(pane,1000, 500);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
	public static List<String> getParabrowser() {
		return parabrowser;
	}
	public void setParabrowser(List<String> parabrowser) {
		this.parabrowser = parabrowser;
	}
	public static void main(String[] args) {
		Application.launch(args);
		
	}
	//create new file
	public String CreateFile(String filename) {
		File myObj = new File(filename);
		try {     
			if(!myObj.exists()) {
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} 
			}else {
				boolean deletedstatus= myObj.delete();
				if(deletedstatus==true) {
					System.out.println("Deleting already existed file with same name....");
					//myObj.deleteOnExit();
					File myObj1 = new File(filename);
					myObj1.createNewFile();
					System.out.println("NewFile created...." + myObj.getName());
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return myObj.getName();
	}
	public String ReadFile(File file,String destfile) throws IOException {

		String data="";
		try {
			Scanner myReader = new Scanner(file);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();		
				WriteToFile(destfile,data);	
			}
			myReader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;
	}
	public void WriteToFile(String file,String data) throws FileNotFoundException {
		try {
			FileWriter myWriter = new FileWriter(file,true);
			myWriter.append(data);
			myWriter.append('\n');
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public File getFolder() {
		return selectedDirectory;
	}
	public void listFilesForFolder(File folder) throws IOException {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
				CreateFile(Path+fileEntry.getName().toString());	
				ReadFile(fileEntry,Path+fileEntry.getName().toString());
				OverWrite(Path+fileEntry.getName().toString());
			}
		}
		System.out.println("Suite has been Imported Successfully......");
	}
	public void OverWrite(String filePath) throws IOException {
	      Scanner sc = new Scanner(new File(filePath));
	      StringBuffer buffer = new StringBuffer();
	      while (sc.hasNextLine()) {
	         buffer.append(sc.nextLine()+System.lineSeparator());
	      }
	      String fileContents = buffer.toString();
	      sc.close();
	  	if(filePath.contains(".java")) {	
	      String oldLine = Files.readAllLines(Paths.get(filePath)).get(0);;
	      String newLine = "package InputSuite;";
	      fileContents = fileContents.replaceAll(oldLine, newLine);
	      @SuppressWarnings("resource")
		FileWriter writer = new FileWriter(filePath);
	      writer.flush();
	      writer.append(fileContents);
	      writer.flush();
	}
	}
}
