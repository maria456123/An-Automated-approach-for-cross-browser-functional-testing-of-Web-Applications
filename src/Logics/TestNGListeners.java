package Logics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.HttpConnection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import Drivers.BrowsersWebDrivers;
import View.Index;
public class TestNGListeners implements ITestListener{
	TestCaseDetailsGettersSetters testdetails=new TestCaseDetailsGettersSetters();
	static WebDriver driver;
	static String testcase_name;
	String browserdetail;
	static String SuiteName;

	static String browserName;
	static ArrayList<String>AllpassedTestcases=new ArrayList<String>();	
	static  ArrayList<String> passedbrowserName;
	static String line;
	String Testcasenamedetail;
	String testcasestatusdetail;
	String Exception;
	String browserDetails;
	ITestContext context;
	String failureReason;
	String failurePoint;
	static String freason;
	public static String getFreason() {
		return freason;
	}
	public static void setFreason(String freason) {
		TestNGListeners.freason = freason;
	}
	static File folder= new File(System.getProperty("user.dir")+"\\src\\InputSuite");
	String suitepath=System.getProperty("user.dir")+"\\src\\Logics\\Suit.txt";
	String tailoredpath=System.getProperty("user.dir")+"\\src\\Logics\\TailoredTestSuit.txt";
	String tailoredSuiteReport=System.getProperty("user.dir")+"\\src\\Logics\\TailoredSuiteReport";
	String reason;
	static String url;

	
	public void onTestStart(ITestResult result) {
		System.out.println("***********************************************");
		System.out.println("Test started- "+result.getName());
		System.out.println("browser--"+result.getTestClass().getXmlTest().getParameter("browser"));
		browserdetail=result.getTestClass().getXmlTest().getParameter("browser");
		setBrowserName(browserdetail);
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test completed successfully- "+result.getName());	
		testdetails.setTestcaseName(result.getName());
		testdetails.setTestcaseStatus("Passed");
		testdetails.setFailureReason("                -               ");
		testdetails.setFailurePoint("                -               ");	
		try {
			writeToFile();	
		
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
		}
		AllpassedTestcases.add(result.getName());
		setAllpassedTestcases(AllpassedTestcases);
		
		
	}
	public void onTestFailure(ITestResult result) {	
		driver=BrowsersWebDrivers.getDriver();
		String failedURL=driver.getCurrentUrl();
		System.out.println("This is the URL where testcase failed: "+failedURL);
		TestNGListeners.setUrl(driver.getCurrentUrl());
		setTestcase_name(result.getName());
		String Line=displayStackTraceInformation(result.getThrowable(),true);
		String reason=heuristicsforIssues(result.getThrowable().getMessage());
		System.out.println("Reason:"+reason);
		System.out.println("Test Failed- "+result.getName()+"------");		
		testdetails.setTestcaseName(result.getName());
		testdetails.setTestcaseStatus("Failed");	
		testdetails.setFailureReason(result.getThrowable().getLocalizedMessage().toString().trim());
		testdetails.setFailurePoint(Line);

		try {
			writeToFile();
		
		
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//		try {
		//			
		//			Connection.Response loginaddressbookApplication = Jsoup.connect("http://localhost/addressbook").method(Connection.Method.GET).execute();	
		//			Document document= Jsoup.connect(failedURL)
		//					.data("cookieexists", "false")
		//					.data("user","admin")
		//					.data("pass","secret")
		//					.data("submit","Login")
		//					.cookies(loginaddressbookApplication .cookies())
		//					.get();
		//			getXml(document.toString(),result.getName(),browserdetail);
		//		}catch (IOException e1) {
		//		}
	
	}
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped- "+result.getName());	
		testdetails.setTestcaseName(result.getName());
		testdetails.setTestcaseStatus("Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub


	}

	public void onStart(ITestContext context) {
		
		System.out.println("Started- "+context.getSuite().getName());
		setSuiteName(context.getSuite().getName());
		
	}

	public void onFinish(ITestContext context) {
		System.out.println("Finished- "+context.getSuite().getName());
//		ArrayList <String>passed=new ArrayList<String>();
//		passed=getAllpassedTestcases();
//		for(String pass:passed) {
//			System.out.println("Excluded to be testcases are :"+ pass);
//		}
//		String tailoredSuiteReport=System.getProperty("user.dir")+"\\src\\Logics\\TailoredSuiteReport";
//		if(context.getSuite().getName().equalsIgnoreCase("TailoredSuiteChrome")|| context.getSuite().getName().equalsIgnoreCase("TailoredSuiteFirefox")||context.getSuite().getName().equalsIgnoreCase("TailoredSuiteEdge")||context.getSuite().getName().equalsIgnoreCase("TailoredSuiteInternetExplorer")){
//			if(context.getFailedTests().getAllResults() != null || context.getPassedTests().getAllResults()!=null) {
//			try {
//				FileWriter myWriter = new FileWriter(tailoredSuiteReport,true);
//				myWriter.append(browserdetail+"__"+testdetails.TestcaseName()+"__"+testdetails.TestcaseStatus()+"__"+testdetails.getFailureReason()+"__"+testdetails.getFailurePoint());
//				myWriter.append("\n");
//				myWriter.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//	}
	}

	public void writeToFile() throws IOException {

		//browserdetail
		Testcasenamedetail=testdetails.TestcaseName();
		testcasestatusdetail=testdetails.TestcaseStatus();
		failureReason=testdetails.getFailureReason();
		failurePoint=testdetails.getFailurePoint();
		//String FR= displayReason(failureReason);
		String[] parts = failureReason.split("}");
		parts=parts[0].split("For");
		parts=parts[0].split("\\(Session");
		parts=parts[0].split("Build");
		parts=parts[0].split("for:");	
		failureReason=parts[0];	
		failureReason=failureReason.trim();
		testdetails.setFailureReason(failureReason);
		failureReason=testdetails.getFailureReason();
		setFreason(failureReason);
		//		testdetails.setFailureReason(FR);
		//		failureReason=testdetails.getFailureReason();
		try {
			FileWriter myWriter = new FileWriter(suitepath,true);
			myWriter.append(browserdetail+"--"+Testcasenamedetail+"--"+testcasestatusdetail+"--"+failureReason+"--"+failurePoint);
			myWriter.append("\n");
			myWriter.close();
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
		}
	}	
	public String heuristicsforIssues(String actualException) {
		//int countReExecutionAttemps;
		Heuristics h=new Heuristics();
		String reasondisplayed = null;
		try {

			if(actualException.contains(("css selector"))||actualException.contains("*** Element info: {Using=css selector")){
				reasondisplayed="NoSuchElementException: Unable to Locate Element Method CSS selector";	
			}
			if((actualException.contains("xpath"))||(actualException.contains("unable to find element with xpath"))||(actualException.contains("Unable to locate Element"))) {
				reasondisplayed="NoSuchElementException:Unable to Locate Element Method Xpath";
				h.locatorHeuristic();
			}
			if(actualException.contains("ElementNotInteractableException")||actualException.contains("could not be scrolled into view")||actualException.contains("element not interactable")) {
				reasondisplayed="Element not Interactable";
				h.ElementNotInteractable();
			}
			if(actualException.contains("Timed out")||actualException.contains("TimeoutException:")||actualException.contains("waiting for page to load")||actualException.contains("org.openqa.selenium.TimeoutException: ")||actualException==null||actualException=="") {
				h.TimeHeuristic();
				System.out.println("browser firefox timeout exception"+getBrowserName());
			}
			if(actualException.contains("stale element")){
				reasondisplayed="StaleElementReferenceException";
			}
			if(actualException.contains("FileInputStream")) {
				reasondisplayed="File not found at specified path";
			}
			if(actualException.contains("but found")||actualException.contains("Expected:<true> but was:<false>")||actualException.contains("Expected:<false> but was:<true>")||actualException.contains("but was:")||actualException.contains("Expected:")){
				reasondisplayed="Assertion Failed";
				h.AssertionHeuristic();
			}
			if(actualException.contains("The system cannot find the path specified")) {
				reasondisplayed="File path is Incorrect";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return reasondisplayed;
	}
	//creating Tailored and Suite Files
	public void createNewFile() throws IOException {
		File file = new File(suitepath);
		File file_tailored=new File(tailoredpath);
		boolean fvar = file.createNewFile();
		file_tailored.createNewFile();
		if (fvar){
			System.out.println("File has been created successfully");
		}
		else {
			file.delete();
			file=new File(suitepath);
			file.createNewFile();
			file_tailored.delete();
			file_tailored=new File(tailoredpath);
			file_tailored.createNewFile();
		}
	}
	public static String displayStackTraceInformation (Throwable ex,
			boolean displayAll)
	{
		String[] pathnames;
		if (null == ex)
		{
			System.out.println ("Null stack trace reference!");
		}
		//		System.out.println ("The stack according to printStackTrace():\n");
		//		ex.printStackTrace();
		//		System.out.println ("");
		StackTraceElement[] stackElements = ex.getStackTrace();
		if (displayAll)
		{
			//			System.out.println ("The " + stackElements.length +
			//					" element" +
			//					((stackElements.length == 1) ? "": "s") +
			//					" of the stack trace:\n");
		}
		else
		{
			//			System.out.println ("The top element of a " +
			//					stackElements.length +
			//					" element stack trace:\n");
		}
		pathnames = folder.list();
		for (String pathname : pathnames) {
			for (int lcv = 0; lcv < stackElements.length; lcv++)
			{
				if(pathname.equalsIgnoreCase(stackElements[lcv].getFileName())) {
					//					//System.out.println ("Filename: " +
					//							stackElements[lcv].getFileName());
					//					System.out.println ("Line number: " +
					//							stackElements[lcv].getLineNumber());
					//					String className = stackElements[lcv].getClassName();
					//					System.out.println ("Full class name: " + className);
					//					System.out.println ("Method name: " +
					//							stackElements[lcv].getMethodName());
					//					System.out.println ("Native method?: " +
					//							stackElements[lcv].isNativeMethod());
					//					System.out.println ("toString(): " +
					//							stackElements[lcv].toString());
					//					System.out.println ("");
					line=stackElements[lcv].toString();
					setLine(line);
					System.out.println("print stacktrace line of TestNGListener simple: "+line);
					if (!displayAll)
						return line;
				}
			}
			//System.out.println(pathname);
		}
		//System.out.println ("");
		return line;
	}   
	public static String getLine() {
		return line;
	}
	public static void setLine(String line) {
		TestNGListeners.line = line;
	}
	public static String getTestcase_name() {
		return testcase_name;
	}
	public void setTestcase_name(String testcase_name) {
		this.testcase_name = testcase_name;
	}
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		TestNGListeners.url = url;
	}
	
	public static OutputStream getXml(String mycontent,String path_name, String browserdetail2) {
		String Current_dir=System.getProperty("user.dir");
		FileOutputStream fos = null;
		File file = null;
		if(browserdetail2.equalsIgnoreCase("chrome")) {
			try {
				//Specify the file path here
				file = new File(Current_dir +"\\src\\XMLFilesChrome\\"+path_name+".xml");
				fos = new FileOutputStream(file,false);
				if (!file.exists()) {
					file.createNewFile();
				}
				else if(file.exists()){
					boolean deleted= file.delete();
					if(deleted==true) {
						System.out.println("Deleting already existed file with same name....");
						file.createNewFile();
						System.out.println("NewFile created...." + file.getName());
					}
				}
				byte[] bytesArray = mycontent.getBytes();		
				fos.write(bytesArray);
				fos.flush();
				//System.out.println("File "+file.getName()+" has been written Successfully...");
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		}
		//ff
		if(browserdetail2.equalsIgnoreCase("firefox")) {
			try {
				//Specify the file path here
				file = new File(Current_dir +"\\src\\XMLFilesFirefox\\"+path_name+".xml");
				fos = new FileOutputStream(file,false);
				if (!file.exists()) {
					file.createNewFile();
				}
				else if(file.exists()){
					boolean deleted= file.delete();
					if(deleted==true) {
						System.out.println("Deleting already existed file with same name....");
						file.createNewFile();
						System.out.println("NewFile created...." + file.getName());
					}
				}
				byte[] bytesArray = mycontent.getBytes();		
				fos.write(bytesArray);
				fos.flush();
				//System.out.println("File "+file.getName()+" has been written Successfully...");
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		}
		//edge
		if(browserdetail2.equalsIgnoreCase("Edge")) {
			try {
				//Specify the file path here
				file = new File(Current_dir +"\\src\\XMLFilesEdge\\"+path_name+".xml");
				fos = new FileOutputStream(file,false);
				if (!file.exists()) {
					file.createNewFile();
				}
				else if(file.exists()){
					boolean deleted= file.delete();
					if(deleted==true) {
						System.out.println("Deleting already existed file with same name....");
						file.createNewFile();
						System.out.println("NewFile created...." + file.getName());
					}
				}
				byte[] bytesArray = mycontent.getBytes();		
				fos.write(bytesArray);
				fos.flush();
				//System.out.println("File "+file.getName()+" has been written Successfully...");
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		}
		//IE

		if(browserdetail2.equalsIgnoreCase("InternetExplorer")) {
			try {
				//Specify the file path here
				file = new File(Current_dir +"\\src\\XMLFilesEdge\\"+path_name+".xml");
				fos = new FileOutputStream(file,false);
				if (!file.exists()) {
					file.createNewFile();
				}
				else if(file.exists()){
					boolean deleted= file.delete();
					if(deleted==true) {
						System.out.println("Deleting already existed file with same name....");
						file.createNewFile();
						System.out.println("NewFile created...." + file.getName());
					}
				}
				byte[] bytesArray = mycontent.getBytes();		
				fos.write(bytesArray);
				fos.flush();
				//System.out.println("File "+file.getName()+" has been written Successfully...");
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			} 
		}
		return fos;
	}
	public static ArrayList<String> getPassedbrowserName() {
		return passedbrowserName;
	}
	public static void setPassedbrowserName(ArrayList<String> passedbrowserName) {
		TestNGListeners.passedbrowserName = passedbrowserName;
	}
	public static String getBrowserName() {
		return browserName;
	}
	public static void setBrowserName(String browserName) {
		TestNGListeners.browserName = browserName;
	}
	public static ArrayList<String> getAllpassedTestcases() {
		return AllpassedTestcases;
	}
	public static void setAllpassedTestcases(ArrayList<String> allpassedTestcases) {
		AllpassedTestcases = allpassedTestcases;
	}
	public static String getSuiteName() {
		return SuiteName;
	}
	public static void setSuiteName(String suiteName) {
		SuiteName = suiteName;
	}
}
