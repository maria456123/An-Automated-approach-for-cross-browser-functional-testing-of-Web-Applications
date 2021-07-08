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
public class IntermediateSuiteTestNGListener implements ITestListener{
	TestCaseDetailsGettersSetters testdetails=new TestCaseDetailsGettersSetters();
	static WebDriver driver;
	static String testcase_name;
	String browserdetail;
	static ArrayList<String>AllpassedTestcases=new ArrayList<String>();
	public static ArrayList<String> getAllpassedTestcases() {
		return AllpassedTestcases;
	}
	public static void setAllpassedTestcases(ArrayList<String> allpassedTestcases) {
		AllpassedTestcases = allpassedTestcases;
	}
	static String browserName;
	public static String getBrowserName() {
		return browserName;
	}
	public static void setBrowserName(String browserName) {
		IntermediateSuiteTestNGListener.browserName = browserName;
	}
	String Testcasenamedetail;
	String testcasestatusdetail;
	String Exception;
	String browserDetails;
	String failureReason;
	String failurePoint;
	static String SuiteStats;
	public static String getSuiteStats() {
		return SuiteStats;
	}
	public static void setSuiteStats(String suiteStats) {
		SuiteStats = suiteStats;
	}
	static int passedCount;
	static int failedCount;
	static File folder= new File(System.getProperty("user.dir")+"\\src\\InputSuite");
	String suitepath=System.getProperty("user.dir")+"\\src\\Logics\\Suit.txt";
	String tailoredpath=System.getProperty("user.dir")+"\\src\\Logics\\TailoredTestSuit.txt";
	String reason;
	static String url;

	static String line;
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
		AllpassedTestcases.add(result.getName());
		setAllpassedTestcases(AllpassedTestcases);
			
		}
//		catch (IOException e) {
//			System.out.println("An error occurred.");
//		}
//	}
//	public void onTestFailure(ITestResult result) {	
//		driver=BrowsersWebDrivers.getDriver();
//	String failedURL=driver.getCurrentUrl();
////		urlOfWebApplication.add(driver.getCurrentUrl());
////		ArrayList<String>urls=new ArrayList<String>();
////		setUrlOfWebApplication(urlOfWebApplication);
//		System.out.println("This is the URL where testcase failed: "+failedURL);
//		//Connection.Response loginclarolineApplication = null;
//		try {
//			//For Claroline Application
//			//			loginclarolineApplication = Jsoup.connect("http://localhost/claroline-1.11.10-1/claroline-1.11.10-1/").method(Connection.Method.GET).execute();	
//			//			System.out.println("Login login...");
//			//			Document document= Jsoup.connect(failedURL)
//			//					.data("cookieexists", "false")
//			//					.data("login", "admin")
//			//					.data("password", "admin")
//			//					.data("submit", "Ok")
//			//					.followRedirects(false)
//			//					.cookies(loginclarolineApplication .cookies())
//			//					.get();
//			//for addressbook
//			Connection.Response loginaddressbookApplication = Jsoup.connect("http://localhost/addressbook").method(Connection.Method.GET).execute();	
//			//			System.out.println("Login login...");
//			Document document= Jsoup.connect(failedURL)
//					.data("cookieexists", "false")
//					.data("user","admin")
//					.data("pass","secret")
//					.data("submit","Login")
//					.cookies(loginaddressbookApplication .cookies())
//					.get();
//			getXml(document.toString(),result.getName(),browserdetail);
////			System.out.println("JSoup to JDOM convert"); 
////			document = Jsoup.parse(document.html(),"UTF-8");
////			document.outputSettings().prettyPrint(false);
////			SAXBuilder sb = new SAXBuilder();
////			org.jdom2.Document docjdom = sb.build(new StringReader(document.html()));
////			String mycontent=docjdom.toString();
////			XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());	
////			//writing jdom on XML file 
////			xout.output(docjdom.getContent(),System.out);
//
//			//			         ArrayList<String> getSpecificElement=LocatorHeuristic.getSpecificElement(document.html(),"claroBody");
//			//			     	LocatorHeuristic.getPageElements(document.html());
//			//			         DomComparision dc=new DomComparision();
//			//			         dc.compareDOMofAllBrowsers();
//		}catch (IOException e1) {
//		}
//		IntermediateSuiteTestNGListener.setUrl(driver.getCurrentUrl());
//		setTestcase_name(result.getName());
//		String Line=displayStackTraceInformation(result.getThrowable(),true);	
//		System.out.println("Test Failed- "+result.getName()+"------");	
//		String res=heuristicsforIssues(result.getThrowable().getMessage());
//		System.out.println("Result :" + res);
//		testdetails.setTestcaseName(result.getName());
//		testdetails.setTestcaseStatus("Failed");	
//		testdetails.setFailureReason(result.getThrowable().getLocalizedMessage().toString().trim());
//		testdetails.setFailurePoint(Line);
////		try {
////			writeToFile();
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}	
//	}
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

	}

	public void onFinish(ITestContext context) {
		System.out.println("Finished- "+context.getSuite().getName());
			ArrayList<String> passedtestcases = IntermediateSuiteTestNGListener.getAllpassedTestcases();
//			for (String indtestcases : passedtestcases) {   
//			    System.out.println("passed testcases "+indtestcases);
//			}
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
		Heuristics h=new Heuristics();
		String reasondisplayed = null;
		try {
			if(actualException.contains("Unable to locate element")) {
				if(actualException.contains(("css selector"))||actualException.contains("*** Element info: {Using=css selector")){
					reasondisplayed="NoSuchElementException: Unable to Locate Element Method CSS selector";	
					//h.CSSlocatorHeuristic();
					//h.InvalidElementStateHeuristic();
				}
				else if(actualException.contains("xpath")) {
					reasondisplayed="NoSuchElementException:Unable to Locate Element Method Xpath";
//					h.locatorHeuristic();
				}

				else {
					reasondisplayed="NoSuchElementException:Unable to Locate Element simple";
					//h.locatorHeuristic();
				}
			}
			else if(actualException.contains("ElementNotInteractableException")||actualException.contains("could not be scrolled into view")||actualException.contains("element not interactable")) {
				reasondisplayed="Element not Interactable";
			    //h.ElementNotInteractable();
			}

			else if(actualException.contains("waiting for an element to be clickable")){			
				reasondisplayed="TimeOutException";
				System.out.println(reasondisplayed);
				//h.TimeHeuristic();
			}
			else if(actualException.contains("stale element")){
				reasondisplayed="StaleElementReferenceException";
			}
			else if(actualException.contains("FileInputStream")) {
				reasondisplayed="File not found at specified path";
			}
			else if(actualException.contains("but found")) {
				reasondisplayed="Assertion Failed";
				h.AssertionHeuristic();
			}
			else if(actualException.contains("The system cannot find the path specified")) {
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
					System.out.println("print stacktrace line: "+line);
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
		IntermediateSuiteTestNGListener.line = line;
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
		IntermediateSuiteTestNGListener.url = url;
	}
	public static int getPassedCount() {
		return passedCount;
	}
	public static void setPassedCount(int passedCount) {
		IntermediateSuiteTestNGListener.passedCount = passedCount;
	}
	public static int getFailedCount() {
		return failedCount;
	}
	public static void setFailedCount(int failedCount) {
		IntermediateSuiteTestNGListener.failedCount = failedCount;
	}

	static ArrayList <String> urlOfWebApplication=new ArrayList<String>();
	public static ArrayList<String> getUrlOfWebApplication() {
		return urlOfWebApplication;
	}
	public void setUrlOfWebApplication(ArrayList<String> urlOfWebApplication) {
		this.urlOfWebApplication = urlOfWebApplication;
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

	
}
