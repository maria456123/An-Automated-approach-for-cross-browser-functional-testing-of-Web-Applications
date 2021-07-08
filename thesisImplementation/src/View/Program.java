package View;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
public class Program {
    private void testRunner() {

        // Running TestNG programmatically
        // http://testng.org/doc/documentation-main.html#running-testng-programmatically

        //Create an instance on TestNG
        TestNG testNG = new TestNG();

        //Create an instance of XML Suite and assign a name for it.
        XmlSuite suite = getXmlSuite();

        //Create an instance of XmlTest and assign a name for it.
        XmlTest testChrome = getXmlTestChrome(suite);
        XmlTest testFF = getXmlTestFF(suite);
        XmlTest testEdge = getXmlTestEdge(suite);
        //Add any parameters that you want to set to the Test.
        

        //Create a list which can contain the classes that you want to run.
        List<XmlClass> classes = getXmlClasses();

        //Assign that to the XmlTest Object created earlier.
        testChrome.setXmlClasses(classes);
        testFF.setXmlClasses(classes);
        testEdge.setXmlClasses(classes);
        //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> tests = new ArrayList<XmlTest>();
        tests.add(testChrome);
        tests.add(testFF);
tests.add(testEdge);
        //add the list of tests to your Suite.
        suite.setTests(tests);
        tests=suite.getTests();
        //Add the suite to the list of suites.
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        testNG.setParallel(XmlSuite.ParallelMode.TESTS);
        List<String> paramsList = Arrays.asList("Chrome", "Edge","Firefox");
        for (int i = 0; i <tests.size(); i++) {
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("browser", paramsList.get(i));
            tests.get(i).setParameters(paramsMap);
        }
        //Set the list of Suites to the testNG object you created earlier.
        testNG.setXmlSuites(suites);

        //invoke run() - this will run your class.
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
   
    public static void main(String args[]) {
    	
        Program program = new Program();
        program.testRunner();
        
    }
    }
	