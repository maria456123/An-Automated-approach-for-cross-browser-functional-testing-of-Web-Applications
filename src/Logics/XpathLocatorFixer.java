package Logics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
public class XpathLocatorFixer {
	static String WebElementtestcaseName,WebElementTagName,WebElementAttributeName,WebElementAttributeValue;
	static String WebElementText,WebElementLocation;
	static String WebElementAttributeId,WebElementAttributeClass,WebElementType;

	static ArrayList<String>tailoredFilepaths=new ArrayList<String>();

	static String webEleFilename;

	public static String getWebEleFilename() {
		return webEleFilename;
	}
	public void setWebEleFilename(String webEleFilename) {
		XpathLocatorFixer.webEleFilename = webEleFilename;
	}
	//get location from failed testcase
	public void getAllpassedTestcases() throws IOException {
		TestNGListeners tl=new TestNGListeners();
		String failed_tcName=tl.getTestcase_name();
		System.out.println("TestNGListener getFailed Testcase name: "+failed_tcName);
		FetchingDOM FD=new FetchingDOM();
		FD.Get_Line_Info(); //must because down method will get values from them i.e they are set in them
		int lineNumber=FD.getLineNumber();
		String Linetext=FD.getLineContent();
		System.out.println("Line text: "+Linetext);
		if(Linetext.contains("xpath")) {
			ParseFileTogetWebElement(Linetext,failed_tcName,lineNumber);
			String tailoredFilepath=FetchingDOM.getTailoredFilePath();
			tailoredFilepaths.add(tailoredFilepath);
			setTailoredFilepaths(tailoredFilepaths);
			System.out.println("tailored file path in locator fixer:"+tailoredFilepath);
			Map<String, String> map = new HashMap<String, String>();
			map=FetchingDOM.getTestcaseLOCmap();
			for (String key: map.keySet()) {
				System.out.println("TestcaseName : " + key);
				System.out.println("Line of code : " + map.get(key));
			}
		}
		System.out.println("**************************************WRITTEN IN INTERMEDIATESUITE*********************************");
	}

	//get web element from browser where testcase passed
	private void ParseFileTogetWebElement(String line,String tcname,int LineNumber) throws IOException {	
		int min = 1;
		int max = 5;
		int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		String elename=tcname+"ele"+random_int+LineNumber;
		setWebEleFilename(elename);
		System.out.println("RandomElement Name: "+elename);
		String wd=System.getProperty("user.dir").replace("\\", "\\\\");
		String tobeinserted = null;
		if(line.startsWith("driver.find")) {  //removing send and click condition..
			String[]splitted=line.split("\\)\\)");
			tobeinserted="WebElement"+"\t"+elename+"="+splitted[0]+"));"+"try{"+
					"FileWriter myWriter = new FileWriter("+"\""+wd+"\\\\src\\\\webeledetails"+"\""+",true);"+"myWriter.append("+"\""+tcname+"\""+"+\"___\"+"+elename+".getAttribute(\"class\")+\"___\"+"+elename+".getTagName()+\"___\"+"+elename+".getAttribute(\"title\")+\"___\"+"+elename+".getText().toString()+\"___\"+"
					+elename+".getAttribute(\"name\")+\"___\"+"+elename+".getLocation().toString()+\"___\"+"+elename+".getAttribute(\"id\")+\"___\"+"+elename+".getAttribute(\"id\")+\"___\"+"+elename+".getAttribute(\"value\"));"
					+"myWriter.append(\"\\n\");"+"myWriter.close();"+"}"+"catch (IOException e) {"+"e.printStackTrace();}";

			System.out.println("To be inserted:"+ tobeinserted);
			String filePathToGetWebElement=FetchingDOM.getFilePathToGetWebElement();	
			String IntermediatePath=filePathToGetWebElement.replace("InputSuite", "IntermediateSuite");
			System.out.println("Intermediate FilePath"+IntermediatePath);
			BufferedReader file = new BufferedReader(new FileReader(IntermediatePath));
			StringBuffer inputBuffer = new StringBuffer();
			String fileline;
			while ((fileline = file.readLine()) != null) {
				if (!fileline.isEmpty()) {
					inputBuffer.append(fileline);
					inputBuffer.append("\n");
				}
			}
			file.close();	
			String inputStr = inputBuffer.toString();	
			System.out.println("Line to be replaced:"+line);
			inputStr=inputStr.replace(line, tobeinserted);
			FileOutputStream fileOut = new FileOutputStream(IntermediatePath);
			fileOut.write(inputStr.getBytes());
			fileOut.close();
		}
		else {
			setWebEleFilename(elename);
			System.out.println("RandomElement Name: "+elename);
			String[]varSeperation=line.split("driver");
			String varname=varSeperation[0];
			//System.out.println("var name:"+varname);
			String var=varSeperation[1];
			var="driver"+var;
			//System.out.println("variable is:"+var);
			String[]splitted=var.split("\\)\\)");
			tobeinserted="WebElement"+"\t"+elename+"="+splitted[0]+"));"+"try{"+
					"FileWriter myWriter = new FileWriter("+"\""+wd+"\\\\src\\\\webeledetails"+"\""+",true);"+"myWriter.append("+"\""+tcname+"\""+"+\"___\"+"+elename+".getAttribute(\"class\")+\"___\"+"+elename+".getTagName()+\"___\"+"+elename+".getAttribute(\"title\")+\"___\"+"+elename+".getText().toString()+\"___\"+"
					+elename+".getAttribute(\"name\")+\"___\"+"+elename+".getLocation().toString()+\"___\"+"+elename+".getAttribute(\"id\")+\"___\"+"+elename+".getAttribute(\"id\")+\"___\"+"+elename+".getAttribute(\"value\"));"
					+"myWriter.append(\"\\n\");"+"myWriter.close();"+"}"+"catch (IOException e) {"+"e.printStackTrace();}";
			//System.out.println("To be inserted:"+ tobeinserted);
			String filePathToGetWebElement=FetchingDOM.getFilePathToGetWebElement();	
			String IntermediatePath=filePathToGetWebElement.replace("InputSuite", "IntermediateSuite");
			//System.out.println("Intermediate FilePath"+IntermediatePath);
			BufferedReader file = new BufferedReader(new FileReader(IntermediatePath));
			StringBuffer inputBuffer = new StringBuffer();
			String fileline;
			while ((fileline = file.readLine()) != null) {
				if (!fileline.isEmpty()) {
					inputBuffer.append(fileline);
					inputBuffer.append("\n");
				}	
			}
			file.close();	
			String inputStr = inputBuffer.toString().trim();	
			//System.out.println("Line to be replaced:"+line);
			inputStr=inputStr.replace(line, tobeinserted);
			FileOutputStream fileOut = new FileOutputStream(IntermediatePath);
			fileOut.write(inputStr.getBytes());
			fileOut.close();
		}
	}

	//getter setters
	public static String getWebElementtestcaseName() {
		return WebElementtestcaseName;
	}
	public static void setWebElementtestcaseName(String webElementtestcaseName) {
		WebElementtestcaseName = webElementtestcaseName;
	}
	public static String getWebElementTagName() {
		return WebElementTagName;
	}
	public static void setWebElementTagName(String webElementTagName) {
		WebElementTagName = webElementTagName;
	}
	public static String getWebElementAttributeName() {
		return WebElementAttributeName;
	}
	public static void setWebElementAttributeName(String webElementAttributeName) {
		WebElementAttributeName = webElementAttributeName;
	}
	public static String getWebElementAttributeValue() {
		return WebElementAttributeValue;
	}
	public static void setWebElementAttributeValue(String webElementAttributeValue) {
		WebElementAttributeValue = webElementAttributeValue;
	}
	public static String getWebElementText() {
		return WebElementText;
	}
	public static void setWebElementText(String webElementText) {
		WebElementText = webElementText;
	}
	public static String getWebElementLocation() {
		return WebElementLocation;
	}
	public static void setWebElementLocation(String webElementLocation) {
		WebElementLocation = webElementLocation;
	}

	public static String getWebElementAttributeId() {
		return WebElementAttributeId;
	}
	public static void setWebElementAttributeId(String webElementAttributeId) {
		WebElementAttributeId = webElementAttributeId;
	}
	public static String getWebElementAttributeClass() {
		return WebElementAttributeClass;
	}
	public static void setWebElementAttributeClass(String webElementAttributeClass) {
		WebElementAttributeClass = webElementAttributeClass;
	}
	public static String getWebElementType() {
		return WebElementType;
	}
	public static void setWebElementType(String webElementType) {
		WebElementType = webElementType;
	}
	public static ArrayList<String> getTailoredFilepaths() {
		return tailoredFilepaths;
	}
	public static void setTailoredFilepaths(ArrayList<String> tailoredFilepaths) {
		XpathLocatorFixer.tailoredFilepaths = tailoredFilepaths;
	}
	public static void getWebElementdetails() throws IOException {
		String wd=System.getProperty("user.dir");
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(wd+"\\src\\webeledetails"));
		String line;
		String []parts;
		String testcaseName,TagName,OwnText,AttributeName,AttributeValue,Location,id,Class,title;
		while (true) {
			line = reader.readLine();
			if (line == null) {
				break;
			}

			parts = line.split("___");	
			testcaseName=parts[0];
			Class=parts[1];
			TagName=parts[2];
			title=parts[3];
			OwnText=parts[4];
			AttributeName=parts[5];
			Location=parts[6];
			id="";
			AttributeValue="";
			setWebElementtestcaseName(testcaseName);
			setWebElementTagName(TagName);
			setWebElementAttributeName(AttributeName);
			setWebElementAttributeValue(AttributeValue);
			setWebElementText(OwnText);
			setWebElementLocation(Location);
			setWebElementAttributeId(id);
			setWebElementAttributeClass(Class);
			setWebElementType(title);//title
			System.out.println("Testcase name: "+getWebElementtestcaseName());
			System.out.println("Tag name: "+getWebElementTagName());
			System.out.println("Attribute Text :"+getWebElementText());
			System.out.println("Attribute name: "+getWebElementAttributeName());
			System.out.println("Attribute value: "+getWebElementAttributeValue());
			System.out.println("Location : "+getWebElementLocation());
			System.out.println("Attribute id: "+getWebElementAttributeId());
			System.out.println("Attribute class: "+getWebElementAttributeClass());
			System.out.println("Attribute Title is: "+getWebElementType());
			System.out.println("****************************************************");
			BufferedReader readerforFailedTcFile = new BufferedReader(new FileReader(wd+"\\src\\FailedTestsDetails"));
			String lines;
			String []part;
			String tcname,failedLineText,LineNumber,TailoredFilePath;
			while (true) {
				lines = readerforFailedTcFile.readLine();
				if (lines == null) {
					break;
				}	
				lines=lines.trim();
				part = lines.split("--");	
				tcname=part[0];
				failedLineText=part[1];
				LineNumber=part[2];
				TailoredFilePath=part[3];
				//				System.out.println("Failed testcase name: "+tcname);
				//				System.out.println("Failed line text is: "+failedLineText);
				//				System.out.println("Failed line numebr: "+LineNumber);
				//				System.out.println("Tailored path of failed line: "+TailoredFilePath);
				if(getWebElementtestcaseName().equalsIgnoreCase(tcname)) {
					//System.out.println("getwebelementtcname"+getWebElementtestcaseName()+"failedtcname"+tcname);
					String newXpath=XpathLocatorFixer.UpdateTailoredSuiteNewXpath(failedLineText);
					//System.out.println("newXpath is: "+newXpath);
					//System.out.println("******************************");
					XpathLocatorFixer.updateXpathInTailoredSuite(TailoredFilePath,newXpath,failedLineText);
				}
			}
		}
	}
	private static String GetXpathUsingAttributeId(String AttributeName, String tagname,String Attributeid) {
		String xpathString;
		if(AttributeName==null||AttributeName.length()==0) {
			xpathString="driver.findElement(By.xpath("+"\""+"//*[@id="+"\'"+Attributeid+"\'"+"]"+"\""+"))";
		}
		else {
			xpathString="driver.findElement(By.xpath("+"\""+"//*[@name="+"\'"+AttributeName+"\'"+"and @id="+"\'"+Attributeid+"\'"+"]"+"\""+"))";
		}
		return xpathString;
	}

	//getting xpath of web element USING attribute name
	public static String GetXpathUsingAttributeName(String AttributeName,String tagname,String Attributeclass) {
		String xpathString;
		if(Attributeclass==null||Attributeclass.length()==0) {
			xpathString="driver.findElement(By.name("+"\""+AttributeName+"\""+"))";
		}
		else {
			xpathString="driver.findElement(By.xpath("+"\""+"//*[@name="+"\'"+AttributeName+"\'"+"and @class="+"\'"+Attributeclass+"\'"+"]"+"\""+"))";

		}
		return xpathString;
	}
	//get xpath using text
	private static String GetXpathUsingText(String text,String tagname) {
		String xpath = null;
		if(text.length()!=0 && tagname.length()!=0) {
			if(text.contains("\"")) {
				text.replace("\"", "\\\"");
				xpath="driver.findElement(By.xpath(\"//"+tagname+"[text()="+"\'"+text+"\'"+"or contains(text(),"+"\'"+text+"\'"+")]"+"\""+"))";	
			}
			else {
				xpath="driver.findElement(By.xpath(\"//"+tagname+"[text()="+"\'"+text+"\'"+"or contains(text(),"+"\'"+text+"\'"+")]"+"\""+"))";	
			}
		}
		return xpath;
	}
	//get xpath using title
	private static String GetXpathUsingTitle(String text) {
		String xpath;
		xpath="driver.findElement(By.xpath(\"//*"+"[@title="+"\'"+text+"\'"+"]"+"\""+"))";
		return xpath;	
	}
	public static String UpdateTailoredSuiteNewXpath(String line) throws IOException {
		String newXpath = null;
		String[]splittednewXpath = null;
		if(line.contains("xpath")) {
			if(line.contains("Assert")) {
				String[]splittedAssertPart=line.split("driver");
				String assertpart=splittedAssertPart[0];
				//System.out.println("AssertPart:"+assertpart);
				splittednewXpath=line.split("\\)\\)");
//				System.out.println("Splitted 0 : "+splittednewXpath[0]);
//				System.out.println("Splitted 1 : "+splittednewXpath[1]);
				if(getWebElementText().isEmpty()==false&&getWebElementText()!="null") {
					String xpath=GetXpathUsingText(getWebElementText(),getWebElementTagName());
					newXpath=assertpart+xpath+splittednewXpath[1];
				}
				else if(getWebElementAttributeName().isEmpty()==false && getWebElementAttributeName()!="null") {
					String xpath=GetXpathUsingAttributeName(getWebElementAttributeName(),getWebElementTagName(),getWebElementAttributeClass());
					newXpath=assertpart+xpath+splittednewXpath[1];
				}
				else if(getWebElementAttributeId().isEmpty()==false&&getWebElementAttributeId()!="null") {
					String xpath=GetXpathUsingAttributeId(getWebElementAttributeName(),getWebElementTagName(),getWebElementAttributeId());
					newXpath=assertpart+xpath+splittednewXpath[1];
				}
				else if(getWebElementType().isEmpty()==false&&getWebElementType()!="null") {
					String xpath=GetXpathUsingTitle(getWebElementType());
					newXpath=assertpart+xpath+splittednewXpath[1];
				}
				else {
					newXpath=line;
				}

			}
			else {
				splittednewXpath=line.split("\\)\\)");
//				System.out.println("Splitted 0 : "+splittednewXpath[0]);
//				System.out.println("Splitted 1 : "+splittednewXpath[1]);
				String []varName=splittednewXpath[0].split("driver");
				String var=varName[0];
				//System.out.println("variable is:"+var);
				if(var.isEmpty()==true) {
					//System.out.println("variable vefore driver is not present");
				
				if(getWebElementText().isEmpty()==false&&getWebElementText()!="null") {
					String xpath=GetXpathUsingText(getWebElementText(),getWebElementTagName());
					newXpath=xpath+splittednewXpath[1];

				}
				else if(getWebElementAttributeName().isEmpty()==false && getWebElementAttributeName()!="null") {
					String xpath=GetXpathUsingAttributeName(getWebElementAttributeName(),getWebElementTagName(),getWebElementAttributeClass());
					newXpath=xpath+splittednewXpath[1];

				}
				else if(getWebElementAttributeId().isEmpty()==false&&getWebElementAttributeId()!="null") {
					String xpath=GetXpathUsingAttributeId(getWebElementAttributeName(),getWebElementTagName(),getWebElementAttributeId());
					newXpath=xpath+splittednewXpath[1];

				}

				//for title of web element
				else if(getWebElementType().isEmpty()==false&&getWebElementType()!="null") {
					String xpath=GetXpathUsingTitle(getWebElementType());
					newXpath=xpath+splittednewXpath[1];

				}
				else {
					newXpath=line;

				}
			}
			
			else {
				//System.out.println("variable vefore driver is present");
				if(getWebElementText().isEmpty()==false&&getWebElementText()!="null") {
					String xpath=GetXpathUsingText(getWebElementText(),getWebElementTagName());
					newXpath=var+xpath+splittednewXpath[1];

				}
				else if(getWebElementAttributeName().isEmpty()==false && getWebElementAttributeName()!="null") {
					String xpath=GetXpathUsingAttributeName(getWebElementAttributeName(),getWebElementTagName(),getWebElementAttributeClass());
					newXpath=var+xpath+splittednewXpath[1];

				}
				else if(getWebElementAttributeId().isEmpty()==false&&getWebElementAttributeId()!="null") {
					String xpath=GetXpathUsingAttributeId(getWebElementAttributeName(),getWebElementTagName(),getWebElementAttributeId());
					newXpath=var+xpath+splittednewXpath[1];

				}

				//for title of web element
				else if(getWebElementType().isEmpty()==false&&getWebElementType()!="null") {
					String xpath=GetXpathUsingTitle(getWebElementType());
					newXpath=var+xpath+splittednewXpath[1];

				}
				else {
					newXpath=line;

				}
			}
		}
		}
		return newXpath;
	}
	public static void updateXpathInTailoredSuite(String tailoredpath,String NewLine,String oldLine) throws IOException {
		BufferedReader file = new BufferedReader(new FileReader(tailoredpath));
		StringBuffer inputBuffer = new StringBuffer();
		String line;
		while ((line = file.readLine()) != null) {
			inputBuffer.append(line);
			inputBuffer.append('\n');
		}
		file.close();
		String inputStr = inputBuffer.toString();
		System.out.println("NewLocator:"+NewLine);
		System.out.println("Old Locator was: "+oldLine);
		System.out.println("TailoredPath is:"+tailoredpath);
		if(oldLine.startsWith("WebElement")==false) {
			//System.out.println("Not^ starts with web element");
			inputStr=inputStr.replace(oldLine,NewLine);
			FileOutputStream fileOut = new FileOutputStream(tailoredpath);
			fileOut.write(inputStr.getBytes());
			fileOut.close();
		}
		else {
			//System.out.println("starts with web element");
			inputStr=inputStr.replace(oldLine, oldLine);
			FileOutputStream fileOut = new FileOutputStream(tailoredpath);
			fileOut.write(inputStr.getBytes());
			fileOut.close();
		}
	}

}
