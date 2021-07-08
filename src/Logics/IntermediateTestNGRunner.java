package Logics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import Drivers.BrowsersWebDrivers;
import View.Index;
public class IntermediateTestNGRunner {
	ArrayList<String> listPassedBrowser;
	Index index=new Index();
	File suiteName=index.getFolder();
	public void testRunner() throws IOException {
		readForPassedTestcasefinding();
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		TestNG testNG = new TestNG();	
		XmlSuite suite = getXmlSuite();
		List<Class<? extends ITestNGListener>> Listenerclass=new ArrayList<Class<? extends ITestNGListener>>();
		XmlTest test = getXmlTestChrome(suite);
		XmlTest testFF = getXmlTestFF(suite);
		XmlTest testEdge = getXmlTestEdge(suite);
		List<XmlClass> classes = getXmlClasses();
		test.setXmlClasses(classes);
		testFF.setXmlClasses(classes);
		testEdge.setXmlClasses(classes);
		
		Listenerclass.add(IntermediateSuiteTestNGListener.class);
		//Listenerclass.add(Transformer.class);
		List<XmlTest> tests = new ArrayList<XmlTest>();
		tests.add(test);
		suite.setTests(tests);
		tests=suite.getTests();
		suites.add(suite);
		List<String>paramsList=new ArrayList<String>(Index.getParabrowser()); 
			Map<String, String> paramsMap = new HashMap<>();
			paramsMap.put("browser", listPassedBrowser.get(0));
			System.out.println("Reference Browser: "+ listPassedBrowser.get(0));
			for (int i = 0; i <tests.size(); i++) {			
				tests.get(i).setParameters(paramsMap);
				System.out.println("passed browser tests running to get web element:"+tests.get(i).getParameter("browser"));	
//				System.out.println("Suite Name: "+suiteName);	
			}	
			
			System.out.println("intermediate runner|********************");
			testNG.setXmlSuites(suites);
			testNG.setListenerClasses(Listenerclass);		
			testNG.run();	
			XpathLocatorFixer.getWebElementdetails();
//			FetchingDOM FD=new FetchingDOM();
//			FD.Get_Line_Info(); //must because down method will get values from them i.e they are set in them
//			int lineNumber=FD.getLineNumber();
//			String Linetext=FD.getLineContent();
			//System.out.println("Line passed from intermediateRunner is:"+Linetext);
	
		}
	private XmlSuite getXmlSuite() {
		XmlSuite suite = new XmlSuite();
		suite.setName("ItermediateTest Suite");
		return suite;
	}
	private XmlTest getXmlTestChrome(XmlSuite suite) {
		XmlTest testChrome = new XmlTest(suite);
//		testChrome.setName("Test is Starting");
		return testChrome;
	}
	private XmlTest getXmlTestFF(XmlSuite suite) {
		XmlTest testFF = new XmlTest(suite);
		testFF.setName("FirefoxTest");
		return testFF;
	}
	private XmlTest getXmlTestEdge(XmlSuite suite) {
		XmlTest testEdge = new XmlTest(suite);
		testEdge.setName("EdgeTest");
		return testEdge;
	}
	private List<XmlClass> getXmlClasses() {
		ArrayList<XmlInclude> methodsToRun = getIncludedMethods();
		List<XmlClass> classez = new ArrayList<XmlClass>();
		
		String cname;
		String fname;
		String ac[] = null;
		String binPath =System.getProperty("user.dir")+"\\src\\IntermediateSuite\\";
		File dir=new File(binPath);
		for (File fileEntry : dir.listFiles()) {		
			fname=fileEntry.getName().toString();
			if(fname.contains(".java")) {
				cname="IntermediateSuite."+fname;
				ac= cname.split(".java");
				XmlClass myClass = new XmlClass(ac[0]);
				 myClass.setIncludedMethods(methodsToRun);
				 classez.add(myClass);
			}      
		}
		return classez;
	}

	private ArrayList<XmlInclude> 	getIncludedMethods() {
		ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
		ArrayList<String>includedmethods=getFailedTestcases();
		for(String ftc:includedmethods) {
			methodsToRun.add(new XmlInclude(ftc));
			System.out.println("Included failed Testcases in Execution:"+ftc);
		}
		return methodsToRun ;
	}
private void readForPassedTestcasefinding() throws IOException {
   // File f1=new File("Suit.txt"); //Creation of File Descriptor for input file
    String[] words=null;  //Intialize the word Array
    String wd=System.getProperty("user.dir");
    listPassedBrowser=new ArrayList<String>();
    String bname;
    //Creation of File Reader object
    String failedtc=TestNGListeners.getTestcase_name();
    ArrayList<String> failedTests=getFailedTestcases();
    List<String> lines = Files.readAllLines(Paths.get(wd+"\\src\\Logics\\Suit.txt"));
    for(String line:lines){
    	  String [] parts = line.split("--");	
			bname=parts[0];
			String tname=parts[1];
			String tstatus=parts[2];
			String reason=parts[3];
			//if(reason.contains("xpath")) {
			for(String failed:failedTests) {
       if(failed.equalsIgnoreCase(tname)&&tstatus.equalsIgnoreCase("passed")){
    	   listPassedBrowser.add(bname);
          System.out.println("Failed testcase :"+failed+"\n"+" Reference Browser is "+line);
          System.out.println("=============================================");
         
      // }
    }
			}
    }

}
public static ArrayList<String> getFailedTestcases(){
	List<String> lines = null;
	ArrayList<String> failedTests = new ArrayList<String>();
String wd=System.getProperty("user.dir");
try {
	lines = Files.readAllLines(Paths.get(wd+"\\src\\FailedTestsDetails"));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
for(String line:lines){
	String [] parts = line.split("--");	
	String failedTestcasename=parts[0];
	failedTests.add(failedTestcasename);
		}
return failedTests;

}

}