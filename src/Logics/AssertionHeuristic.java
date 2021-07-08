package Logics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.testng.Assert;

public class AssertionHeuristic {

	public static String Get_Failure_point ()throws IOException {
		String[] Split_line;
		if(TestNGListeners.getSuiteName().equalsIgnoreCase("Test Suite")) {
			String line_info=TestNGListeners.getLine();
			Split_line=line_info.split("\\(");
			String browser=TestNGListeners.getBrowserName();
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
			String assertedTextNew=AssertionParser(Line);
			System.out.println("AssertedTextNew"+assertedTextNew);
			UpdateAssertion(tailoredPath,assertedTextNew,Line);
			return Line;
		}
		else {
			String line_info=TailoredTestNGListeners.getLine();
			Split_line=line_info.split("\\(");	
			String browser=TestNGListeners.getBrowserName();
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
			String assertedTextNew=AssertionParser(Line);
			System.out.println("AssertedTextNew"+assertedTextNew);
			UpdateAssertion(tailoredPath,assertedTextNew,Line);
			return Line;
		}
	
	}


public static String AssertionParser(String expected) throws IOException {
	System.out.println("Assertion parser function called....");
	String assertNew = "";
	if(expected.contains("driver.findElement(By.xpath")) {
		String[] exp=expected.split("Assert. assertEquals\\(");
		exp=exp[1].split(",");
		String ex=exp[0];
		return assertNew=("assertEquals("+ex+","+ex+");");
	}
	else if(expected.contains(" Assert.assertTrue\\(" )&&(expected.contains(".matches"))) {
		String[] exp=expected.split(" Assert.assertTrue");
		exp=exp[1].split(";");
		String ex=exp[0]+");";
		return assertNew=("assertTrue("+ex);
	}
	else if(expected.contains(" Assert.assertTrue\\(" )) {
		String[] exp=expected.split(" Assert.assertTrue");
		exp=exp[1].split(";");
		String ex=exp[0]+");";
		return assertNew=("assertFalse("+ex);
	}
	else if(expected.contains(" Assert.assertFalse\\(" )) {
		String[] exp=expected.split(" Assert.assertTrue");
		exp=exp[1].split(";");
		String ex=exp[0]+");";
		return assertNew=("assertTrue("+ex);
	}
	else if(expected.contains("Assert.assertTrue") && (expected.contains("=="))) {
		
		String[] exp=expected.split("\\(");
		exp=exp[1].split("==");
		String ex=exp[0];
		return assertNew=("assertTrue("+ex+"=="+ex+");");
	}
	else if(expected.contains("Assert.assertEquals")){
		expected=expected.replace("Assert.assertEquals", "assertEquals");
		String[] exp=expected.split("\\(");
		exp=exp[1].split(",");
		String ex=exp[0];
		
		if(ex.equalsIgnoreCase("true")) {
			//ex="false";
			
		//return assertNew=("assertEquals("+ex+","+exp[1]+");");
			return assertNew=expected.replace("true","false");
	}
		else if(ex.equalsIgnoreCase("false")) {
			//ex="true";
			//return assertNew=("assertEquals("+ex+","+exp[1]+");");
			return assertNew=expected.replace("false","true");
		}
		

	}
	return assertNew;
}
public static void UpdateAssertion(String filePath,String assertnew,String oldLine) throws IOException {
	BufferedReader file = new BufferedReader(new FileReader(filePath));
	StringBuffer inputBuffer = new StringBuffer();
	String line;
	while ((line = file.readLine()) != null) {
		inputBuffer.append(line);
		inputBuffer.append('\n');
	}
	file.close();
	String inputStr = inputBuffer.toString();
	inputStr=inputStr.replace(oldLine, assertnew);
	FileOutputStream fileOut = new FileOutputStream(filePath);
	fileOut.write(inputStr.getBytes());
	fileOut.close();
} 
}



