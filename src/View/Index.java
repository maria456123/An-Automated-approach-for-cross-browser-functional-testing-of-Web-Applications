package View;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.testng.TestRunner;

//import Logics.TailoredTestNGListeners;
import Logics.TestCaseStatusTableView;
import Logics.TestCaseStatusTableViewForTailoredSuite;
import Logics.TestNGListeners;
import Logics.TestNGRunner;
import Logics.TestNGRunnerTailoredSuite;
//import Logics.WebCrawler;
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
	static File tailoredDirectory=new File(System.getProperty("user.dir")+"\\src\\TailoredSuite");
	static File InputSuiteDirectory=new File(System.getProperty("user.dir")+"\\src\\InputSuite");	
	static File IntermediateSuiteDirectory=new File(System.getProperty("user.dir")+"\\src\\IntermediateSuite");
	static File selectedDirectory;
	static String NewTailoredPackageName;
	static String Tailoredpath;
	String filedata;
	private static String Path =  System.getProperty("user.dir")+"\\src\\InputSuite\\";
	private static String TailoredPath =  System.getProperty("user.dir")+"\\src\\";
	private static String IntermediatePath=(System.getProperty("user.dir")+"\\src\\IntermediateSuite\\");
	
	//private Desktop desktop = Desktop.getDesktop();
	String s2;
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
	static String ApplicationPath;
	

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
		openMultipleButton.setMaxWidth(Double.MAX_VALUE);
		openMultipleButton.setOnAction(e -> {
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Web Application");
			File defaultDirectory = new File("F:\\xampppp\\htdocs");
			chooser.setInitialDirectory(defaultDirectory);
			File selectedApplication = chooser.showDialog(stage);
			if(selectedApplication==null) {
				file_name=new Text("No Application is selected");
			}
			else {
				file_name=new Text(selectedApplication.toString());	
				ApplicationPath=selectedApplication.toString();
				String app[]=ApplicationPath.split("htdocs");	
				String path_application=app[1].replace("\\", "/");
				setApplicationPath("http://localhost"+path_application);
				System.out.println("index get app path "+getApplicationPath());
			   // new WebCrawler().getPageLinks("http://localhost"+path_application, 0);
			}	
			paneCenter_file.getChildren().addAll(file_name);
		});
		//BrowserSelection
		Label l = new Label("Select Browser/Browsers: \n\n"); 
		ObservableList<String> st = FXCollections.observableArrayList("Chrome", "Firefox", "Edge" ,"InternetExplorer"); 
		ListView<String> listView = new ListView<>(st);	
		ComboBox c = new ComboBox(st); 
		c.setMaxWidth(Double.MAX_VALUE);
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
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(e -> {
			selectedDirectory = directoryChooser.showDialog(stage);
			setSelectedDirectory(selectedDirectory);
			if(selectedDirectory==null) {
				application_name=new Text("No directory is selected");
			}
			else {
//				System.out.println(selectedDirectory.getAbsolutePath());
				try {
					//listFilesForFolder(selectedDirectory);
					FileUtils.copyDirectory(selectedDirectory,InputSuiteDirectory);
					FileUtils.copyDirectory(InputSuiteDirectory, IntermediateSuiteDirectory);
					listFilesForFolder(InputSuiteDirectory);
					listFilesForIntermediateSuite(IntermediateSuiteDirectory);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				application_name=new Text(selectedDirectory.getName().toString());
				paneCenter_application.getChildren().addAll(application_name);
			}	
		});
		Button ViewReport = new Button("ViewReport");
		ViewReport.setMaxWidth(Double.MAX_VALUE);
		ViewReport.setOnAction(e -> {
			try {
				Stage s = new Stage();
				new TestCaseStatusTableView().start(s);				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	
		Button execute=new Button("Execute");
		execute.setMaxWidth(700);
		GridPane grid_pane=new GridPane();
		execute.setOnAction(e -> {
			
			try {
				TestNGRunner tnr=new TestNGRunner();
				tnr.testRunner();
				List<String>paramsList=new ArrayList<String>(Index.getParabrowser());
//				for(int i =0;i<paramsList.size();i++) {
//			  //  Text SuiteExecutionStatus=new Text("TestSuite Execution Statistics for "+ TestNGListeners.getSuiteStats());
//				Text passedCount=new Text("Passed Testcases count: "+TestNGListeners.getPassedCount());
//				Text failedCount=new Text("Failed Testcases count: "+TestNGListeners.getFailedCount());
//				grid_pane.add(SuiteExecutionStatus, 1, 10+i);
//				grid_pane.add(passedCount, 0, 11+i);
//				grid_pane.add(failedCount, 0, 12+i);
//				}
				grid_pane.add(Success,0, 9);
				grid_pane.add(ViewReport, 1,13);
				
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
		grid_pane.add(execute,1,8);	
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
	
	private void setSelectedDirectory(File selectedDirectory2) {
		this.selectedDirectory=selectedDirectory2;
		
	}
	public static List<String> getParabrowser() {
		return parabrowser;
	}
	public void setParabrowser(List<String> parabrowser) {
		Index.parabrowser = parabrowser;
	}
	
	//create new file
	public String CreateFile(String filename) {
		File myObj = new File(filename);
		try {     
			if(!myObj.exists()) {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
				} 
			}
			else {
				boolean deletedstatus= myObj.delete();
				if(deletedstatus==true) {
					System.out.println("Deleting already existed file with same name....");
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
//	public void listFilesForFolder(File folder) throws IOException {
//		for (File fileEntry : folder.listFiles()) {
//			if (fileEntry.isDirectory()) {
//				listFilesForFolder(fileEntry);
//			} else {
//				System.out.println(fileEntry.getName());
//				File TailoredDirectory = null;
//				CreateFile(Path+fileEntry.getName().toString());	
//				ReadFile(fileEntry,Path+fileEntry.getName().toString());
//				OverWrite(Path+fileEntry.getName().toString());
//				copyToTailored();
//				List<String>paramsList=new ArrayList<String>(getParabrowser());		
//				for(int i =0;i<paramsList.size();i++) {
//					Tailoredpath=TailoredPath+"TailoredSuite"+paramsList.get(i);
//					TailoredDirectory=new File(TailoredPath+"TailoredSuite"+paramsList.get(i));
//					listFilesForTailoredFolder(TailoredDirectory);
//				}
//				
//				
//				System.out.println("Suite has been Imported Successfully......");
//			}
//		}
//				
//	}
	public void listFilesForFolder(File folder) throws IOException {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				
			} else {
//				System.out.println("Files are:"+fileEntry.getName());
				OverWrite(Path+fileEntry.getName().toString());
				copyToTailored();
				File tailored_directory=getTailoredDirectory();
				listFilesForTailoredFolder(tailored_directory);		
			}
		}
		System.out.println("Suite has been Imported Successfully......");
	}
	private void listFilesForIntermediateSuite(File folder) throws IOException {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				
			} else {
				
				OverWriteIntermediateSuitePackagename(IntermediatePath+fileEntry.getName().toString());
			}
		}
		
	}
public static File getTailoredDirectory() {
	File td = null;
	List<String>paramsList=new ArrayList<String>(getParabrowser());		
	for(int i =0;i<paramsList.size();i++) {
	   String TailoredDirectory=TailoredPath+"TailoredSuite"+paramsList.get(i);
	   td=new File(TailoredDirectory);
	}
	return td;
}
	public void listFilesForTailoredFolder(File folder) throws IOException {
		String TailoredDirectory = null;
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForTailoredFolder(fileEntry);
			} else {			
				List<String>paramsList=new ArrayList<String>(getParabrowser());		
				for(int i =0;i<paramsList.size();i++) {
				    TailoredDirectory=TailoredPath+"TailoredSuite"+paramsList.get(i);
					//System.out.println("TailoredPath"+Tailoredpath);
					OverWritetailoredSuitePackageName(TailoredDirectory+"\\"+fileEntry.getName().toString(),"package"+"\t"+"TailoredSuite"+paramsList.get(i)+";");
					
			}
//				Files.newOutputStream(Paths.get(TailoredDirectory), StandardOpenOption.DELETE_ON_CLOSE);
			}
		}
	}
	//overwrite original imported suite package name
	public void OverWrite(String filePath) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(filePath));
		StringBuffer inputBuffer = new StringBuffer();
		String fileline;
		while ((fileline = file.readLine()) != null) {
			if (!fileline.isEmpty()) {
				inputBuffer.append(fileline);
				inputBuffer.append("\n");
            }	
		}
		file.close();	
		String  fileContents= inputBuffer.toString();	
		if(filePath.contains(".java")) {	
			String oldLine = Files.readAllLines(Paths.get(filePath)).get(0);
			String newLine = "package InputSuite;";
			if(oldLine.contains("package")|| oldLine.isEmpty()) {
				fileContents = fileContents.replaceAll(oldLine, newLine+"\n"+"import java.io.FileWriter;"+"\n"+"import org.openqa.selenium.JavascriptExecutor;"+"\n"+"import org.openqa.selenium.WebElement;"+"\n"+"import java.io.FileWriter;"+"\n"+"import org.openqa.selenium.support.ui.ExpectedCondition;\r\n" + 
						"\r\n" + "import java.io.FileWriter;\r\n" + 
								"import java.io.IOException;"+
						"import java.util.concurrent.TimeUnit;"+"\n"+"import org.openqa.selenium.support.ui.WebDriverWait;"+"\n"+"import org.openqa.selenium.TimeoutException;"+"\n");
				@SuppressWarnings("resource")
				FileWriter writer = new FileWriter(filePath);
				writer.flush();
				writer.append(fileContents);
				writer.flush();
			}
		}
	}
	//overwrite Intermediatesuite package name
	public void OverWriteIntermediateSuitePackagename(String filePath) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(filePath));
		StringBuffer inputBuffer = new StringBuffer();
		String fileline;
		while ((fileline = file.readLine()) != null) {
			if (!fileline.isEmpty()) {
				inputBuffer.append(fileline);
				inputBuffer.append("\n");
            }	
		}
		file.close();	
		String  fileContents= inputBuffer.toString();
		if(filePath.contains(".java")) {	
			String oldLine = Files.readAllLines(Paths.get(filePath)).get(0);
			String newLine = "package IntermediateSuite;";
			if(oldLine.contains("package")|| oldLine.isEmpty()) {
				fileContents = fileContents.replaceAll(oldLine, newLine+"\n"+"import java.io.FileWriter;"+"\n"+"import org.openqa.selenium.JavascriptExecutor;"+"\n"+"import org.openqa.selenium.WebElement;"+"\n"+"import java.io.FileWriter;"+"\n"+"import org.openqa.selenium.support.ui.ExpectedCondition;\r\n" + 
						"\r\n" + "import java.io.FileWriter;\r\n" + 
								"import java.io.IOException;"+
						"import java.util.concurrent.TimeUnit;"+"\n"+"import org.openqa.selenium.support.ui.WebDriverWait;"+"\n"+"import org.openqa.selenium.TimeoutException;"+"\n");
				@SuppressWarnings("resource")
				FileWriter writer = new FileWriter(filePath);
				writer.flush();
				writer.append(fileContents);
				writer.flush();
			}
		}
	}
	//overwrite tailored
	public void OverWritetailoredSuitePackageName(String filePath,String newLine) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(filePath));
		StringBuffer inputBuffer = new StringBuffer();
		String fileline;
		while ((fileline = file.readLine()) != null) {
			if (!fileline.isEmpty()) {
				inputBuffer.append(fileline);
				inputBuffer.append("\n");
            }	
		}
		file.close();	
		String  fileContents= inputBuffer.toString();
		if(filePath.contains(".java")) {	
			String oldLine = Files.readAllLines(Paths.get(filePath)).get(0);
		
			if(oldLine.contains("package")|| oldLine.isEmpty()) {
				fileContents = fileContents.replaceAll(oldLine,newLine);
				@SuppressWarnings("resource")
				FileWriter writer = new FileWriter(filePath);
				writer.flush();
				writer.append(fileContents);
				writer.flush();
			}
		}
	}
	public void copyToTailored() throws IOException {
		File source = new File(Path);
		String Tailoredpath="";
		List<String>paramsList=new ArrayList<String>(Index.getParabrowser());
		for(int i =0;i<paramsList.size();i++) {
			Tailoredpath=TailoredPath+"TailoredSuite"+paramsList.get(i);
		File dest = new File(Tailoredpath);	
		try {
			FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		}
		
	}
//	public void copyToTailored() throws IOException {
//		File source = new File(Path);
//		String Tailoredpath="";
//		List<String>paramsList=new ArrayList<String>(Index.getParabrowser());
//		for(int i =0;i<=paramsList.size();i++) {
//			Tailoredpath=TailoredPath+"TailoredSuite"+paramsList.get(i);
//		File dest = new File(Tailoredpath);	
//		try {
//			FileUtils.copyDirectory(source, dest);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Scanner sc = new Scanner(new File(Tailoredpath));
//		StringBuffer buffer = new StringBuffer();
//		while (sc.hasNextLine()) {
//			buffer.append(sc.nextLine()+System.lineSeparator());
//		}
//		String fileContents = buffer.toString();
//		sc.close();
//		if(Tailoredpath.contains(".java")) {	
//			String oldLine = Files.readAllLines(Paths.get(Tailoredpath)).get(0);
//			String newLine = "package TailoredSuite"+paramsList.get(i)+";";
//			if(oldLine.contains("package")|| oldLine.isEmpty()) {
//				fileContents = fileContents.replaceAll(oldLine, newLine);
//				@SuppressWarnings("resource")
//				FileWriter writer = new FileWriter(TailoredPath);
//				writer.flush();
//				writer.append(fileContents);
//				writer.flush();
//			}
//		}
//		
//		}
//	}
	
	public static File getSelectedDirectory() {
		return selectedDirectory;
	}
	public static String getApplicationPath() {
		return ApplicationPath;
	}
	public static void setApplicationPath(String applicationPath) {
		ApplicationPath = applicationPath;
	}
	public static boolean CheckTestCaseDependency() {
		 int[] ns = null;
		String filePath = System.getProperty("user.dir")+"\\src\\Logics\\Suit.txt";	 
		try {
		    LineNumberReader lineReader = new LineNumberReader(new FileReader(filePath));
		    String lineText = null;		 
		    while ((lineText = lineReader.readLine()) != null) {
		        System.out.println(lineReader.getLineNumber() + ": " + lineText);
		     ns =  new int[]{lineReader.getLineNumber() };
		        		 Arrays.sort(ns);	
		    }
		    lineReader.close();
		} catch (IOException ex) {
		    System.err.println(ex);
		}
		 return ( ns[ns.length-1] - ns[0] == ns.length-1 );
	}
	public static void main(String[] args) throws IOException {
		String wd=System.getProperty("user.dir");
		 File tailoredDirectorychrome=new File(System.getProperty("user.dir")+"\\src\\TailoredSuiteChrome");
		 File tailoredDirectoryfirefox=new File(System.getProperty("user.dir")+"\\src\\TailoredSuiteFirefox");
		 File tailoredDirectoryedge=new File(System.getProperty("user.dir")+"\\src\\TailoredSuiteEdge");
		 File tailoredDirectoryinternetexplorer=new File(System.getProperty("user.dir")+"\\src\\TailoredSuiteInternetExplorer");
		 if(tailoredDirectorychrome.exists()==true) {
			FileUtils.deleteDirectory(tailoredDirectorychrome);
		 }
		 if(tailoredDirectoryfirefox.exists()==true) {
			 FileUtils.deleteDirectory(tailoredDirectoryfirefox);
		 }
		 if(tailoredDirectoryedge.exists()==true) {
		FileUtils.deleteDirectory(tailoredDirectoryedge);
		 }
		 if(tailoredDirectoryinternetexplorer.exists()==true) {
			FileUtils.deleteDirectory(tailoredDirectoryinternetexplorer);
		 }
		String DOMfilesDirectoryChrome=(System.getProperty("user.dir")+"\\src\\XMLFilesChrome");
		String DOMfilesDirectoryff=(System.getProperty("user.dir")+"\\src\\XMLFilesFirefox");
		String DOMfilesDirectoryedge=(System.getProperty("user.dir")+"\\src\\XMLFilesEdge");
		Arrays.stream(new File(IntermediatePath).listFiles()).forEach(File::delete);
		Arrays.stream(new File(Path).listFiles()).forEach(File::delete);
		Arrays.stream(new File(DOMfilesDirectoryedge).listFiles()).forEach(File::delete);
		Arrays.stream(new File(DOMfilesDirectoryChrome).listFiles()).forEach(File::delete);
		Arrays.stream(new File(DOMfilesDirectoryff).listFiles()).forEach(File::delete);
		PrintWriter writer = new PrintWriter(wd+"\\src\\webeledetails");
        writer.print("");
        writer.flush();
        writer.close();
        PrintWriter writerTailoredClear = new PrintWriter(wd+"\\src\\Logics\\TailoredSuiteReport");
        writerTailoredClear.print("");
        writerTailoredClear.flush();
        writerTailoredClear.close();
        PrintWriter writerFailedTestcasereportClear = new PrintWriter(wd+"\\src\\FailedTestsDetails");
        writerFailedTestcasereportClear.print("");
        writerFailedTestcasereportClear.flush();
        writerFailedTestcasereportClear.close();
		Application.launch(args);
	}
	
	}


