package Logics;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;

public class ElementNotInteractableHeuristic {

//this will return the new String that is to be inserted string
private String heuristicForSolvingNonInteractiveElement(String lineoftestcasefailure) {
	System.out.println("construct new line method is called..");
	String assertNew=null;
	String tcname=TestNGListeners.getTestcase_name();
	if(lineoftestcasefailure.contains(".click()")) {
		String []splitted=lineoftestcasefailure.split(".cli");
		String linetobeinserted="WebElement "+tcname+"="+splitted[0]+";";
		System.out.println("WebElement Line To Be Inserted"+linetobeinserted);
	   assertNew=linetobeinserted+"((JavascriptExecutor) driver).executeScript(\"arguments[0].scrollIntoView(true);\", "+tcname+");"+tcname+".click();";
	  
	}
	else if((lineoftestcasefailure.contains(".sendKeys"))&&(lineoftestcasefailure.contains("WebElement")==false)) {
		String []splitted=lineoftestcasefailure.split("\\)\\)");
		String linetobeinserted="WebElement "+tcname+"="+splitted[0]+"));";
		System.out.println("WebElement Line To Be Inserted"+linetobeinserted);
	   assertNew=linetobeinserted+"((JavascriptExecutor) driver).executeScript(\"arguments[0].scrollIntoView(true);\", "+tcname+");"+tcname+splitted[1];
	 
	}
	else if(lineoftestcasefailure.contains("selectBy")) {
			String []splitted=lineoftestcasefailure.split(".selectBy");
			String selectName=splitted[0];
			assertNew="JavascriptExecutor je = (JavascriptExecutor) driver;"+"je.executeScript(\"arguments[0].scrollIntoView(true);\","+selectName+".getOptions().get"+"("+selectName+".getOptions().size()-1));"+"Thread.sleep(250);";
			
		}
		else if(lineoftestcasefailure.startsWith("new Select")||lineoftestcasefailure.startsWith("Select")) {
			String []splitted=lineoftestcasefailure.split("selectBy");
			String linetobeinserted="Select "+tcname+"="+splitted[0]+";";
			assertNew=linetobeinserted+"JavascriptExecutor je = (JavascriptExecutor) driver;"+"je.executeScript(\"arguments[0].scrollIntoView(true);\","+tcname+".getOptions().get"+"("+tcname+".getOptions().size()-1));"+"Thread.sleep(250);";
			
		}
		else if((lineoftestcasefailure.contains("JavascriptExecutor je = (JavascriptExecutor) driver;"))&&(lineoftestcasefailure.contains("scrollIntoView")&&lineoftestcasefailure.startsWith("WebElement")==true )) {
			assertNew="";
		
		}
	
	return assertNew;
 
}
//getting line info
public String ParsingforNonInteractableElement() throws IOException {
	System.out.println("ParsingforNonInteractableElement method is called..");
	
	if(TestNGListeners.getSuiteName().equalsIgnoreCase("Test Suite")) {
		String line_info=TestNGListeners.getLine();
		String browser=TestNGListeners.getBrowserName();
		String[] Split_line=line_info.split("\\(");
		String path=Split_line[0];
		path=path.replace(".", "\\");
		int last_index=path.lastIndexOf("\\");
		path=path.substring(0, last_index);
		String actual_path="\\"+path;
		String wd=System.getProperty("user.dir");			 
		actual_path=actual_path+".java";
		actual_path=actual_path.replace("null\\","");
		actual_path=actual_path.trim();
		String[] actual_line=Split_line[1].split(":");
		String[] actual=actual_line[1].split("\\)");
		int actual_line_number=Integer.parseInt(actual[0]);		
		System.out.println("Actual Line NUMEBR IS: "+actual_line_number);
		System.out.println("Actual File path IS: " +wd+"\\src\\"+actual_path);
		String filepath=wd+"\\src\\"+actual_path;
		String tailoredPath=filepath.replace("InputSuite", "TailoredSuite"+browser);
		System.out.println("Tailored Path is :"+ tailoredPath);
		String Line = Files.readAllLines(Paths.get(wd+"\\src\\"+actual_path)).get(actual_line_number-1);
		System.out.println("LINE of file is :"+Line);
		String assertnew=heuristicForSolvingNonInteractiveElement(Line);
		System.out.println("AssertNew in line info "+assertnew);
		UpdateTest(tailoredPath,assertnew,Line);
		return Line;
		
	}
	else {
		String line_info=TailoredTestNGListeners.getLine();
		String browser=TestNGListeners.getBrowserName();
		
		String[] Split_line=line_info.split("\\(");
		String path=Split_line[0];
		path=path.replace(".", "\\");
		int last_index=path.lastIndexOf("\\");
		path=path.substring(0, last_index);
		String actual_path="\\"+path;
		String wd=System.getProperty("user.dir");			 
		actual_path=actual_path+".java";
		actual_path=actual_path.replace("null\\","");
		actual_path=actual_path.trim();
		String[] actual_line=Split_line[1].split(":");
		String[] actual=actual_line[1].split("\\)");
		int actual_line_number=Integer.parseInt(actual[0]);		
		System.out.println("Actual Line NUMEBR IS: "+actual_line_number);
		System.out.println("Actual File path IS: " +wd+"\\src\\"+actual_path);
		String filepath=wd+"\\src\\"+actual_path;
		String tailoredPath=filepath;
		System.out.println("Tailored Path is :"+ tailoredPath);
		String Line = Files.readAllLines(Paths.get(wd+"\\src\\"+actual_path)).get(actual_line_number-1);
		System.out.println("LINE of file is :"+Line);
		String assertnew=heuristicForSolvingNonInteractiveElement(Line);
		System.out.println("AssertNew in line info "+assertnew);
		UpdateTest(tailoredPath,assertnew,Line);
		return Line;
	}
	
	}

//updating in tailored file
private static void UpdateTest(String filePath, String assertnew, String oldLine) throws IOException {
	System.out.println("AssertNew in update test "+assertnew);
	BufferedReader file = new BufferedReader(new FileReader(filePath));
	StringBuffer inputBuffer = new StringBuffer();
	String line;
	while ((line = file.readLine()) != null) {
		inputBuffer.append(line);
		inputBuffer.append("\n");
	}
	file.close();	
	String inputStr = inputBuffer.toString();	
	inputStr=inputStr.replace(oldLine, assertnew);
	FileOutputStream fileOut = new FileOutputStream(filePath);
	fileOut.write(inputStr.getBytes());
	fileOut.close();		
	
}

}
