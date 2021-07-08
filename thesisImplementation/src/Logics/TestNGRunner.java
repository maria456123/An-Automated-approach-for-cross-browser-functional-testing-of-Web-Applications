package Logics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import View.Index;
public class TestNGRunner {
	public void testRunner() throws IOException {
		TestNG testNG = new TestNG();
		Index index=new Index();
		TestNGListeners tl=new TestNGListeners();
		tl.createNewFile();
		XmlSuite suite = getXmlSuite();
		List<Class<? extends ITestNGListener>> Listenerclass=new ArrayList<Class<? extends ITestNGListener>>();
		XmlTest testChrome = getXmlTestChrome(suite);
		XmlTest testFF = getXmlTestFF(suite);
		XmlTest testEdge = getXmlTestEdge(suite);
		List<XmlClass> classes = getXmlClasses();
		testChrome.setXmlClasses(classes);
		testFF.setXmlClasses(classes);
		testEdge.setXmlClasses(classes);
		Listenerclass.add(TestNGListeners.class);
		List<XmlTest> tests = new ArrayList<XmlTest>();
		tests.add(testChrome);
		tests.add(testFF);
		tests.add(testEdge);
		suite.setTests(tests);
		tests=suite.getTests();
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		testNG.setParallel(XmlSuite.ParallelMode.TESTS);
		List<String>paramsList=new ArrayList(Index.getParabrowser());
		int j=0;
		for (int i = 0; i <tests.size(); i++) {	
			if (j>= paramsList.size()) {
	               j = 0;
	            }
			System.out.println("paramList"+paramsList.get(j));
			Map<String, String> paramsMap = new HashMap<>();
			paramsMap.put("browser", paramsList.get(j));
			tests.get(i).setParameters(paramsMap);
			System.out.println("browser is RunnerTestNG:"+tests.get(i).getParameter("browser"));
			j++;
		}
		
		testNG.setXmlSuites(suites);
		testNG.setListenerClasses(Listenerclass);
		testNG.run();


	}

	private XmlSuite getXmlSuite() {
		XmlSuite suite = new XmlSuite();
		suite.setName("Test Suite");
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

	private List<XmlClass> getXmlClasses() {
		List<XmlClass> classez = new ArrayList<XmlClass>();
		String cname;
		String fname;
		String ac[] = null;
		String binPath = "E:\\EC-WORKSPACE-thesis\\thesisImplementation\\src\\InputSuite";
		File dir=new File(binPath);
		for (File fileEntry : dir.listFiles()) {		
			fname=fileEntry.getName().toString();
			if(fname.contains(".java")) {
				cname="InputSuite."+fname;
				ac= cname.split(".java");
				classez.add(new XmlClass(ac[0]));
			}      
		}

		return classez;
	}
	
}
