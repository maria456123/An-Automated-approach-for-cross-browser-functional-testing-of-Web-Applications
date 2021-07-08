package Logics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class TailoredTestNGListeners implements ITestListener{
	TestCaseDetailsGettersSetters testdetails=new TestCaseDetailsGettersSetters();
	//Index i=new Index();
	static WebDriver driver;
	static String testcase_name;
	String browserdetail;
	String Testcasenamedetail;
	String testcasestatusdetail;
	String Exception;
	static int passedCount;
	static int failedCount;
	String browserDetails;
	String failureReason;
	String failurePoint;
	static ArrayList <String> urlOfWebApplication=new ArrayList<String>();
	static File folder;
	String suitepath=System.getProperty("user.dir")+"\\src\\Logics\\Suit.txt";
	String tailoredSuiteReport=System.getProperty("user.dir")+"\\src\\Logics\\TailoredSuiteReport";
	String reason;
	static String url;

	static String line;
	public void onTestStart(ITestResult result) {
		System.out.println("***********************************************");
		System.out.println("Test started- "+result.getName());
		System.out.println("browser--"+result.getTestClass().getXmlTest().getParameter("browser"));
		browserdetail=result.getTestClass().getXmlTest().getParameter("browser");
		folder= new File(System.getProperty("user.dir")+"\\src\\TailoredSuite"+browserdetail);
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test completed successfully- "+result.getName());	
		testdetails.setTestcaseName(result.getName());
		testdetails.setTestcaseStatus("Passed");
		testdetails.setFailureReason("                -               ");
		testdetails.setFailurePoint("                -               ");
		try {		
			FileWriter myWriter = new FileWriter(tailoredSuiteReport,true);
			myWriter.append(browserdetail+"__"+result.getName()+"__"+testdetails.TestcaseStatus()+"__"+testdetails.getFailureReason()+"__"+testdetails.getFailurePoint());
			myWriter.append("\n");
			myWriter.close();	
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
		}
	}
	public void onTestFailure(ITestResult result) {	
		this.setTestcase_name(result.getName());
		String Line=displayStackTraceInformation(result.getThrowable(),true);	
		System.out.println("Test Failed- "+result.getName()+"------");	
		String res=heuristicsforIssues(result.getThrowable().getMessage());
		System.out.println("Result :" + res);
		testdetails.setTestcaseName(result.getName());
		testdetails.setTestcaseStatus("Failed");	
		testdetails.setFailureReason(result.getThrowable().getLocalizedMessage().toString());
		testdetails.setFailurePoint(Line);
		try {
			writeToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	}

	public void onFinish(ITestContext context) {
		System.out.println("Finished- "+context.getSuite().getName());
	}
	public void writeToFile() throws IOException {
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
		try {
			FileWriter myWriter = new FileWriter(tailoredSuiteReport,true);
			myWriter.append(browserdetail+"__"+Testcasenamedetail+"__"+testcasestatusdetail+"__"+failureReason+"__"+failurePoint);
			myWriter.append("\n");
			myWriter.close();
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
		}
	}	
	//	private String displayReason(String actualException) {
	//		String reasondisplayed = null;
	//		try {
	//			if(actualException.contains("Unable to locate element")) {
	//				if(actualException.contains(("css selector"))||actualException.contains("*** Element info: {Using=css selector")){
	//					reasondisplayed="NoSuchElementException: Unable to Locate Element Method CSS selector";			
	//				}
	//				else if(actualException.contains("xpath")) {
	//					reasondisplayed="NoSuchElementException:Unable to Locate Element Method Xpath";		
	//				}
	//				else {
	//					reasondisplayed="NoSuchElementException:Unable to Locate Element simple";
	//				}
	//			}
	//			else if(actualException.contains("waiting for an element to be clickable")){			
	//				reasondisplayed="TimeOutException";
	//				System.out.println(reasondisplayed);
	//			}
	//			else if(actualException.contains("stale element")){
	//				reasondisplayed="StaleElementReferenceException";
	//			}
	//			else if(actualException.contains("FileInputStream")) {
	//				reasondisplayed="File not found at specified path";
	//			}
	//			else if(actualException.contains("but found")) {
	//				reasondisplayed="Assertion Failed";
	//			}
	//			else if(actualException.contains("The system cannot find the path specified")) {
	//				reasondisplayed="File path is Incorrect";
	//			}
	//		}
	//		catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//		
	//		return reasondisplayed;
	//	}
	public String heuristicsforIssues(String actualException) {
		Heuristics h=new Heuristics();
		String reasondisplayed = null;
		try {

			if(actualException.contains(("css selector"))||actualException.contains("*** Element info: {Using=css selector")){
				reasondisplayed="NoSuchElementException: Unable to Locate Element Method CSS selector";	
				//h.CSSlocatorHeuristic();
				//h.InvalidElementStateHeuristic();
			}
			if(actualException.contains("xpath")) {
				reasondisplayed="NoSuchElementException:Unable to Locate Element Method Xpath";
				//h.locatorHeuristic();
			}
			if(actualException.contains("ElementNotInteractableException")||actualException.contains("could not be scrolled into view")||actualException.contains("element not interactable")) {
				reasondisplayed="Element not Interactable";
				h.ElementNotInteractable();
			}
			if(actualException.contains("waiting for an element to be clickable")){			
				reasondisplayed="TimeOutException";
				h.TimeHeuristic();
			}
			if(actualException.contains("stale element")){
				reasondisplayed="StaleElementReferenceException";
			}
			if(actualException.contains("FileInputStream")) {
				reasondisplayed="File not found at specified path";
			}
			if(actualException.contains("but found")||actualException.contains("Expected:<true> but was:<false>")||actualException.contains("Expected:<false> but was:<true>")){
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
		public void createNewFile() throws IOException {
			File file_tailored=new File(tailoredSuiteReport);
			boolean fvar = file_tailored.createNewFile();
			if (fvar){
				System.out.println("Tailored File has been created successfully");
			}
			else {
				file_tailored.delete();
				file_tailored=new File(tailoredSuiteReport);
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
					line=stackElements[lcv].toString();
					setLine(line);
					System.out.println("print stacktrace line of TailoredTestNGListener : "+line);
					if (!displayAll)
						return line;
				}
			}

		}
		return line;
	}   
	public static String getLine() {
		return line;
	}
	public static void setLine(String line) {
		TailoredTestNGListeners.line = line;
	}
	public String getTestcase_name() {
		return testcase_name;
	}
	public void setTestcase_name(String testcase_name) {
		this.testcase_name = testcase_name;
	}
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		TailoredTestNGListeners.url = url;
	}
	public static int getPassedCount() {
		return passedCount;
	}


}
