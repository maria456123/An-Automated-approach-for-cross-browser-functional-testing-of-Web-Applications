package Logics;

import java.io.BufferedReader;
import java.io.File;
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
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TimeOutHeuristic {
	
	static ArrayList<String>tailoredFilepaths=new ArrayList<String>();
	static long startTime = System.currentTimeMillis();
	public static String Get_Failure_point ()throws IOException {
		//String line_info="";
		if(TestNGListeners.getSuiteName().equalsIgnoreCase("Test Suite")) {
			String line_info=TestNGListeners.getLine();
			String[] Split_line=line_info.split("\\(");
			String browser=TestNGListeners.getBrowserName();
			String tailoredPath = null;
			String path=Split_line[0];
			System.out.println("MayBe Browser"+path);
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
			tailoredPath=filepath.replace("InputSuite", "TailoredSuite"+browser);	
			System.out.println("Tailored Path is :"+ tailoredPath);
			String Line = Files.readAllLines(Paths.get(wd+"\\src\\"+actual_path)).get(actual_line_number-1);
			System.out.println("LINE of file is :"+Line);
			String assertedTextNew=AssertionParser(Line);
			System.out.println("AssertedTextNew:  "+assertedTextNew);
			UpdateAssertion(tailoredPath,assertedTextNew,Line);
			//}
			return Line;	
			
		}
		else {
			String line_info=TailoredTestNGListeners.getLine();
			String[] Split_line=line_info.split("\\(");
			String browser=TestNGListeners.getBrowserName();
			String tailoredPath = null;
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
			tailoredPath=filepath.replace("InputSuite", "TailoredSuite"+browser);	
			System.out.println("Tailored Path is :"+ tailoredPath);
			String Line = Files.readAllLines(Paths.get(wd+"\\src\\"+actual_path)).get(actual_line_number-1);
			System.out.println("LINE of file is :"+Line);
			String assertedTextNew=AssertionParser(Line);
			System.out.println("AssertedTextNew:  "+assertedTextNew);
			UpdateAssertion(tailoredPath,assertedTextNew,Line);
			
			//}
			return Line;	
		}
//		new WebDriverWait(driver, 30).until(
//			      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}
	public static String AssertionParser(String line) throws IOException {
		String browser=TestNGListeners.getBrowserName();
		System.out.println("StartTime"+startTime);
		return "try{"+line+"}"+"catch(TimeoutException te"+browser+")"+"{"+" driver.manage().timeouts().pageLoadTimeout("+"1000"+",TimeUnit.MILLISECONDS);"+"}";
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
	public static ArrayList<String> getTailoredFilepaths() {
		return tailoredFilepaths;
	}
	public static void setTailoredFilepaths(ArrayList<String> tailoredFilepaths) {
		TimeOutHeuristic.tailoredFilepaths = tailoredFilepaths;
	}
}



