package Logics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import View.Index;
public class TestNGListeners implements ITestListener{
	TestCaseDetailsGettersSetters testdetails=new TestCaseDetailsGettersSetters();
	Index i=new Index();
	String browserdetail;
	String Testcasenamedetail;
	String testcasestatusdetail;
	String Exception;
	String browserDetails;
	String failureReason;
	String failurePoint;
	static File folder= new File("E:\\EC-WORKSPACE-thesis\\thesisImplementation\\src\\InputSuite");;
	String suitepath="E:\\EC-WORKSPACE-thesis\\thesisImplementation\\src\\Logics\\Suit.txt";
	String tailoredpath="E:\\EC-WORKSPACE-thesis\\thesisImplementation\\src\\Logics\\TailoredTestSuit.txt";
	String reason;
	static String line;
	public void onTestStart(ITestResult result) {
		System.out.println("Test started- "+result.getName());
		System.out.println("Parameter browser check--"+result.getTestClass().getXmlTest().getParameter("browser"));
		browserdetail=result.getTestClass().getXmlTest().getParameter("browser");
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test completed successfully- "+result.getName());	
		testdetails.setTestcaseName(result.getName());
		testdetails.setTestcaseStatus("Passed");
		testdetails.setFailureReason("                -               ");
		testdetails.setFailurePoint("                -               ");
		try {
			writeToFile();
			System.out.println("tailored testsuite file function write");
			FileWriter myWriter = new FileWriter(tailoredpath,true);
			myWriter.append(browserdetail+"--"+result.getName()+"--"+"Passed");
			myWriter.append("\n");
			myWriter.close();		
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
		}
	}
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed- "+result.getName()+"------");	
		boolean called=displayStackTraceInformation(result.getThrowable(),true);
		System.out.println("new func of getting loc error"+called);
		testdetails.setTestcaseName(result.getName());
		testdetails.setTestcaseStatus("Failed");	
		testdetails.setFailureReason(result.getThrowable().getLocalizedMessage().toString().trim());
		testdetails.setFailurePoint(line);
		
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
		System.out.println("Started- "+context.getName());

	}

	public void onFinish(ITestContext context) {
		System.out.println("Finished- "+context.getName());

	}
	public void writeToFile() throws IOException {
		String res = null;
		//browserdetail
		Testcasenamedetail=testdetails.TestcaseName();
		testcasestatusdetail=testdetails.TestcaseStatus();
		failureReason=testdetails.getFailureReason();
		failurePoint=testdetails.getFailurePoint();
		String[] parts = failureReason.split("}");
		parts=parts[0].split("For");
		parts=parts[0].split("\\(Session");
		parts=parts[0].split("Build");
		failureReason=parts[0];	
		failureReason=failureReason.trim();
		testdetails.setFailureReason(failureReason);
		failureReason=testdetails.getFailureReason();
		res=heuristicsforIssues(failureReason);
		System.out.println("Result of heuristic method:"+res);
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

		String reasondisplayed = null;
		try {
			//Locator 
			if(actualException.contains("Unable to locate element")) {
				reasondisplayed="NoSuchElementException:Unable to Locate Element";
				System.out.println(reasondisplayed);
				if(actualException.contains("css selector")) {
					reasondisplayed="NoSuchElementException:Unable to Locate Element Method CSS selector";
				}
				else if(actualException.contains("xpath")) {
					reasondisplayed="NoSuchElementException:Unable to Locate Element Method Xpath";
				}
				else {
					reasondisplayed="NoSuchElementException:Unable to Locate Element simple";
				}
			}
			else if(actualException.contains("waiting for an element to be clickable")){
				reasondisplayed="TimeOutException";
				System.out.println(reasondisplayed);
			}
			else if(actualException.contains("stale element")){
				reasondisplayed="StaleElementReferenceException";
				System.out.println(reasondisplayed);
			}	
		}
		catch (Exception e) {
			e.getCause().getStackTrace();
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

	//
	public static boolean displayStackTraceInformation (Throwable ex,
			boolean displayAll)
	{
		String[] pathnames;
		if (null == ex)
		{
			System.out.println ("Null stack trace reference!");
			return false;
		}
		System.out.println ("The stack according to printStackTrace():\n");
		ex.printStackTrace();
		System.out.println ("");
		StackTraceElement[] stackElements = ex.getStackTrace();
		if (displayAll)
		{
			System.out.println ("The " + stackElements.length +
					" element" +
					((stackElements.length == 1) ? "": "s") +
					" of the stack trace:\n");
		}
		else
		{
			System.out.println ("The top element of a " +
					stackElements.length +
					" element stack trace:\n");
		}
		pathnames = folder.list();
		for (String pathname : pathnames) {
			for (int lcv = 0; lcv < stackElements.length; lcv++)
			{
				if(pathname.equalsIgnoreCase(stackElements[lcv].getFileName())) {
					System.out.println ("Filename: " +
							stackElements[lcv].getFileName());
					System.out.println ("Line number: " +
							stackElements[lcv].getLineNumber());
					String className = stackElements[lcv].getClassName();
					System.out.println ("Full class name: " + className);
					System.out.println ("Method name: " +
							stackElements[lcv].getMethodName());
					System.out.println ("Native method?: " +
							stackElements[lcv].isNativeMethod());
					System.out.println ("toString(): " +
							stackElements[lcv].toString());
					System.out.println ("");
					line=stackElements[lcv].toString();
					if (!displayAll)
						return true;
				}
			}
			System.out.println(pathname);
		}
		System.out.println ("");
		return true;
	}     

}
