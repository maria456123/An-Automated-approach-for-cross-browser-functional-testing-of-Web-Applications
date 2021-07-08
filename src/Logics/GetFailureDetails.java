package Logics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class GetFailureDetails {
	int lineNumber;
public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
public String ParseFailureTestCaseMethod() throws IOException {
	TestNGListeners testlistenerobj=new TestNGListeners ();
	String testcasename=testlistenerobj.getTestcase_name();
	String url=TestNGListeners.getUrl();
	System.out.println("Failed Testcase name is:"+testcasename);
	System.out.println("Url where testcase failed:"+ url);
	TestNGListeners tl=new TestNGListeners();
	String line_info=tl.getLine();
	String path_info=line_info;
	String filepathoftestcasefailure=ParsingforPath(path_info);
	System.out.println("Failure testcase file Path"+filepathoftestcasefailure);
	Document doc=getDom(url);
	System.out.println("Document of "+url+"\n"+doc.toString());
	return testcasename;	
}
public String ParsingforPath(String line_info) throws IOException {
		String actual_path="";
		String[] Split_line=line_info.split("\\(");
		String path=Split_line[0];
		path=path.replace(".", "\\");
		int last_index=path.lastIndexOf("\\");
		path=path.substring(0, last_index);
		actual_path="\\"+path;
		String wd=System.getProperty("user.dir");			 
		actual_path=actual_path+".java";
		actual_path=actual_path.replace("null\\","");
		actual_path=actual_path.trim();
		String[] actual_line=Split_line[1].split(":");
		String[] actual=actual_line[1].split("\\)");
		int actual_line_number=Integer.parseInt(actual[0]);	
		setLineNumber(actual_line_number-1);
		System.out.println("Actual Line NUMEBR IS: "+actual_line_number);
		System.out.println("Actual File path IS: " +wd+"\\src\\"+actual_path);
		String Line = Files.readAllLines(Paths.get(wd+"\\src\\"+actual_path)).get(actual_line_number-1);
		System.out.println("LINE of file is :"+Line);
		return actual_path;	
	}

public String ReadFile(File file,String destfile) throws IOException {

	String data="";
	try {
		Scanner myReader = new Scanner(file,"utf-8");
		while (myReader.hasNextLine()) {
			data = myReader.nextLine();	
			
			WriteToFile(destfile,data);	
		}
		myReader.close();
	} 
	catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	}
	return data;
}
public void WriteToFile(String file,String data) throws FileNotFoundException {
	try {
		FileWriter myWriter = new FileWriter(file,true);
		myWriter.append(data);
		myWriter.append('\n');
		myWriter.close();
	} catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	}
}
public Document getDom(String url) throws IOException {
	Connection.Response loginFormclarolineApplication = Jsoup.connect("http://localhost/claroline-1.11.10-1/claroline-1.11.10-1/").method(Connection.Method.GET).execute();				
	Document doc= Jsoup.connect(url)
			.data("cookieexists", "false")
			.data("user", "admin")
			.data("pass", "admin")
			.data("submit", "Login")
			.cookies(loginFormclarolineApplication.cookies())
			.get();
	doc = Jsoup.parse(doc.html(), "", Parser.xmlParser());
	doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml); 
	Elements links = doc.select("a[href]");
	for (Element link : links) {
		System.out.println("\nlink : " + link.absUrl("href"));
		
	}
	//System.out.println("Document of url: "+doc.toString());
	return doc;
}
}
