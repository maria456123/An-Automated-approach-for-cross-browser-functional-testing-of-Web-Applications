package Logics;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import Drivers.BrowsersWebDrivers;
import View.Index;
public class TestNGRunnerTailoredSuite {
	Index index=new Index();

	public void testRunner() throws IOException {
		List<String>paramsListbrowser=new ArrayList<String>(Index.getParabrowser());
//		TestNGListeners tl=new TestNGListeners();
//		tl.createNewFile();
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		TestNG testNG = new TestNG();
		XmlSuite TailoredsuiteChrome = getXmlSuiteChrome();
		XmlSuite TailoredsuiteFirefox = getXmlSuiteFirefox();
		XmlSuite TailoredsuiteEdge = getXmlSuiteEdge();
		XmlSuite TailoredsuiteInternetExplorer = getXmlSuiteInternetExplorer();
		List<Class<? extends ITestNGListener>> ListenerclassTailored=new ArrayList<Class<? extends ITestNGListener>>();
		ListenerclassTailored.add(TailoredTestNGListeners.class);
		XmlTest test1 = getXmlTestChrome(TailoredsuiteChrome);
		XmlTest testFF1 = getXmlTestFF(TailoredsuiteFirefox);
		XmlTest testEdge1 = getXmlTestEdge(TailoredsuiteEdge);
		XmlTest testIE=getXmlTestIE(TailoredsuiteInternetExplorer);
		for(String pb:paramsListbrowser) {
			if(pb.equalsIgnoreCase("chrome")){
				System.out.println("CHROME TEST:");
				List<XmlTest> tailoredtestsChrome = new ArrayList<XmlTest>();
				List<XmlClass> TailoredclassesChrome = getXmlClassesTailoredChrome();
				test1.setXmlClasses(TailoredclassesChrome);
				tailoredtestsChrome.add(test1);
				TailoredsuiteChrome.setTests(tailoredtestsChrome);	
				tailoredtestsChrome=TailoredsuiteChrome.getTests();	
				suites.add(TailoredsuiteChrome);
				Map<String, String> paramsMap = new HashMap<>();
				paramsMap.put("browser",pb);
				for (int i = 0; i <tailoredtestsChrome.size(); i++) {			
					tailoredtestsChrome.get(i).setParameters(paramsMap);
					System.out.println("browser is RunnerTestNG:"+tailoredtestsChrome.get(i).getParameter("browser"));		
				}
				
			}
			if(pb.equalsIgnoreCase("firefox")) {
				System.out.println("FIREFOXX TEST:");
				List<XmlTest> tailoredtestsFirefox = new ArrayList<XmlTest>();
				List<XmlClass> TailoredclassesFirefox = getXmlClassesTailoredFirefox();
				testFF1.setXmlClasses(TailoredclassesFirefox);
				tailoredtestsFirefox.add(testFF1);	
				TailoredsuiteFirefox.setTests(tailoredtestsFirefox);	
				tailoredtestsFirefox=TailoredsuiteFirefox.getTests();	
				suites.add(TailoredsuiteFirefox);
				Map<String, String> paramsMap = new HashMap<>();
				paramsMap.put("browser",pb);
				for (int i = 0; i <tailoredtestsFirefox.size(); i++) {			
					tailoredtestsFirefox.get(i).setParameters(paramsMap);
					System.out.println("browser is RunnerTestNG:"+tailoredtestsFirefox.get(i).getParameter("browser"));		
				}
				
			}
			if(pb.equalsIgnoreCase("edge")) {
				System.out.println("EDGE TEST:");
				List<XmlTest> tailoredtestsEdge= new ArrayList<XmlTest>();
				List<XmlClass> TailoredclassesEdge = getXmlClassesTailoredEdge();
				testEdge1.setXmlClasses(TailoredclassesEdge);
				tailoredtestsEdge.add(testEdge1);
				TailoredsuiteEdge.setTests(tailoredtestsEdge);	
				tailoredtestsEdge=TailoredsuiteEdge.getTests();	
				suites.add(TailoredsuiteEdge);
				Map<String, String> paramsMap = new HashMap<>();
				paramsMap.put("browser",pb);
				for (int i = 0; i <tailoredtestsEdge.size(); i++) {			
					tailoredtestsEdge.get(i).setParameters(paramsMap);
					System.out.println("browser is RunnerTestNG:"+tailoredtestsEdge.get(i).getParameter("browser"));		
				}
				

			}
			if(pb.equalsIgnoreCase("internetexplorer")) {
				System.out.println("EXPLORER TEST:");
				List<XmlTest> tailoredtestsIE= new ArrayList<XmlTest>();
				List<XmlClass> TailoredclassesInternetExplorer = getXmlClassesTailoredInternetExplorer();
				testIE.setXmlClasses(TailoredclassesInternetExplorer);
				tailoredtestsIE.add(testIE);	
				TailoredsuiteInternetExplorer.setTests(tailoredtestsIE);	
				tailoredtestsIE=TailoredsuiteInternetExplorer.getTests();	
				suites.add(TailoredsuiteInternetExplorer);
				Map<String, String> paramsMap = new HashMap<>();
				paramsMap.put("browser",pb);
				for (int i = 0; i <tailoredtestsIE.size(); i++) {			
					tailoredtestsIE.get(i).setParameters(paramsMap);
					System.out.println("browser is RunnerTestNG:"+tailoredtestsIE.get(i).getParameter("browser"));		
				}
			

			}
		}
		testNG.setXmlSuites(suites);
		testNG.setListenerClasses(ListenerclassTailored);		
		testNG.run();
		
	}	
	private XmlTest getXmlTestIE(XmlSuite suite) {
		XmlTest testIE = new XmlTest(suite);
		testIE.setName("InternetExplorerTest");
		return testIE;
	}

	private XmlSuite getXmlSuiteChrome() {
		XmlSuite suite = new XmlSuite();
		suite.setName("TailoredSuiteChrome");
		return suite;
	}
	private XmlSuite getXmlSuiteFirefox() {
		XmlSuite suite = new XmlSuite();
		suite.setName("TailoredSuiteFirefox");
		return suite;
	}
	private XmlSuite getXmlSuiteEdge() {
		XmlSuite suite = new XmlSuite();
		suite.setName("TailoredSuiteEdge");
		return suite;
	}
	private XmlSuite getXmlSuiteInternetExplorer() {
		XmlSuite suite = new XmlSuite();
		suite.setName("TailoredSuiteInternetExplorer");
		return suite;
	}
	private XmlTest getXmlTestChrome(XmlSuite suite) {
		XmlTest testChrome = new XmlTest(suite);
		testChrome.setName("ChromeTest");
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
	private List<XmlClass> getXmlClassesTailoredChrome() {
		ArrayList<XmlInclude> methodsToRun = getIncludedMethodsChrome();
		List<XmlClass> classez = new ArrayList<XmlClass>();
		String cname;
		String fname;
		String ac[] = null;
		
		String binPath =System.getProperty("user.dir")+"\\src\\TailoredSuiteChrome";
		File dir=new File(binPath);
		for (File fileEntry : dir.listFiles()) {		
			fname=fileEntry.getName().toString();
		
			if(fname.contains(".java")) {
				cname="TailoredSuiteChrome."+fname;
				ac= cname.split(".java");
				XmlClass myClass = new XmlClass(ac[0]);
				 myClass.setIncludedMethods(methodsToRun);
				 classez.add(myClass);

			}      
		}
		return classez;
	}

	private List<XmlClass> getXmlClassesTailoredFirefox() {
		ArrayList<XmlInclude> methodsToRun = getIncludedMethodsFirefox();
		List<XmlClass> classez = new ArrayList<XmlClass>();
		String cname;
		String fname;
		String ac[] = null;
		String binPath =System.getProperty("user.dir")+"\\src\\TailoredSuiteFirefox";
		File dir=new File(binPath);
		for (File fileEntry : dir.listFiles()) {		
			fname=fileEntry.getName().toString();
			if(fname.contains(".java")) {
				cname="TailoredSuiteFirefox."+fname;
				ac= cname.split(".java");
				XmlClass myClass = new XmlClass(ac[0]);
				 myClass.setIncludedMethods(methodsToRun);
				 classez.add(myClass);

			}      
		}
		return classez;
	}
	//edge
	private List<XmlClass> getXmlClassesTailoredEdge() {
		ArrayList<XmlInclude> methodsToRun = getIncludedMethodsEdge();
		List<XmlClass> classez = new ArrayList<XmlClass>();
		String cname;
		String fname;
		String ac[] = null;
		String binPath =System.getProperty("user.dir")+"\\src\\TailoredSuiteEdge";
		File dir=new File(binPath);
		for (File fileEntry : dir.listFiles()) {		
			fname=fileEntry.getName().toString();
			if(fname.contains(".java")) {
				cname="TailoredSuiteEdge."+fname;
				ac= cname.split(".java");
				XmlClass myClass = new XmlClass(ac[0]);
				 myClass.setIncludedMethods(methodsToRun);
				 classez.add(myClass);

			}      
		}
		return classez;
	}
	private List<XmlClass> getXmlClassesTailoredInternetExplorer() {
		ArrayList<XmlInclude> methodsToRun = getIncludedMethodsInternetExplorer();
		List<XmlClass> classez = new ArrayList<XmlClass>();
		String cname;
		String fname;
		String ac[] = null;
		String binPath =System.getProperty("user.dir")+"\\src\\TailoredSuiteInternetExplorer";
		File dir=new File(binPath);
		for (File fileEntry : dir.listFiles()) {		
			fname=fileEntry.getName().toString();
			if(fname.contains(".java")) {
				cname="TailoredSuiteInternetExplorer."+fname;
				ac= cname.split(".java");
				XmlClass myClass = new XmlClass(ac[0]);
				 myClass.setIncludedMethods(methodsToRun);
				 classez.add(myClass);
			}      
		}

		return classez;
	}
	public static ArrayList<String> getFailedTestcasesChrome(){
		List<String> lines = null;
		
		ArrayList<String> failedTestsChrome = new ArrayList<String>();
	String wd=System.getProperty("user.dir");
	try {
		lines = Files.readAllLines(Paths.get(wd+"\\src\\Logics\\Suit.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(String line:lines){
		String [] parts = line.split("--");	
		String browser=parts[0];
		String failedTestcasename=parts[1];
		String status=parts[2];
		if(status.equalsIgnoreCase("Failed")&& browser.equalsIgnoreCase("Chrome")) {
			System.out.println("Failed testcase names:"+failedTestcasename);		
				failedTestsChrome.add(failedTestcasename);
			}
		else {
			
		}
		}
		return failedTestsChrome;
	}
	//ff
	public static ArrayList<String> getFailedTestcasesFF(){
		List<String> lines = null;
		
		ArrayList<String> failedTestsChrome = new ArrayList<String>();
	String wd=System.getProperty("user.dir");
	try {
		lines = Files.readAllLines(Paths.get(wd+"\\src\\Logics\\Suit.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(String line:lines){
		String [] parts = line.split("--");	
		String browser=parts[0];
		String failedTestcasename=parts[1];
		String status=parts[2];
		if(status.equalsIgnoreCase("Failed")&& browser.equalsIgnoreCase("firefox")) {
			System.out.println("Failed testcase names:"+failedTestcasename);		
				failedTestsChrome.add(failedTestcasename);
			}
		else {
			
		}
		}
		return failedTestsChrome;
	}
	//ie
	public static ArrayList<String> getFailedTestcasesIe(){
		List<String> lines = null;
		
		ArrayList<String> failedTestsChrome = new ArrayList<String>();
	String wd=System.getProperty("user.dir");
	try {
		lines = Files.readAllLines(Paths.get(wd+"\\src\\Logics\\Suit.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(String line:lines){
		String [] parts = line.split("--");	
		String browser=parts[0];
		String failedTestcasename=parts[1];
		String status=parts[2];
		if(status.equalsIgnoreCase("Failed")&& browser.equalsIgnoreCase("internetExplorer")) {
			System.out.println("Failed testcase names:"+failedTestcasename);		
				failedTestsChrome.add(failedTestcasename);
			}
		else {
			
		}
		}
		return failedTestsChrome;
	}
	//edege
	public static ArrayList<String> getFailedTestcasesEdge(){
		List<String> lines = null;
		
		ArrayList<String> failedTestsChrome = new ArrayList<String>();
	String wd=System.getProperty("user.dir");
	try {
		lines = Files.readAllLines(Paths.get(wd+"\\src\\Logics\\Suit.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	for(String line:lines){
		String [] parts = line.split("--");	
		String browser=parts[0];
		String failedTestcasename=parts[1];
		String status=parts[2];
		if(status.equalsIgnoreCase("Failed")&& browser.equalsIgnoreCase("Edge")) {
			System.out.println("Failed testcase names:"+failedTestcasename);		
				failedTestsChrome.add(failedTestcasename);
			}
		else {
			
		}
		}
		return failedTestsChrome;
	}
	private ArrayList<XmlInclude> getIncludedMethodsChrome() {
		ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
		ArrayList<String>includedmethods=getFailedTestcasesChrome();
		if(includedmethods.size()!=0) {
		for(String ftc:includedmethods) {
			methodsToRun.add(new XmlInclude(ftc));
			System.out.println("Included failed Testcases in Chrome Execution:"+ftc);
		}
	}
		else {
			methodsToRun.add(new XmlInclude(""));
		}
		return methodsToRun ;
	}
	
	private ArrayList<XmlInclude> getIncludedMethodsFirefox() {
		ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
		ArrayList<String>includedmethods=getFailedTestcasesFF();
		for(String ftc:includedmethods) {
			methodsToRun.add(new XmlInclude(ftc));
			System.out.println("Included failed Testcases in Firefox Execution:"+ftc);
		}
		return methodsToRun ;
	}
	private ArrayList<XmlInclude> getIncludedMethodsEdge() {
		ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
		ArrayList<String>includedmethods=getFailedTestcasesEdge();
		for(String ftc:includedmethods) {
			methodsToRun.add(new XmlInclude(ftc));
			System.out.println("Included failed Testcases in Edge Execution:"+ftc);
		}
		return methodsToRun ;
	}
	private ArrayList<XmlInclude> getIncludedMethodsInternetExplorer() {
		ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
		ArrayList<String>includedmethods=getFailedTestcasesIe();
		for(String ftc:includedmethods) {
			methodsToRun.add(new XmlInclude(ftc));
			System.out.println("Included failed Testcases in InetrnetExplorer Execution:"+ftc);
		}
		return methodsToRun ;
	}
}
