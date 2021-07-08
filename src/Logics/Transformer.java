package Logics;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transformer implements IAnnotationTransformer{
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
annotation.setRetryAnalyzer(RetryAnalyser.class);
List<String> lines = null;
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
	System.out.println("failedTestcases in retry"+failedTestcasename);
	annotation.setTestName(failedTestcasename);
    System.out.println("Annotation get TestName"+annotation.getTestName().toString());
		}
}
}
