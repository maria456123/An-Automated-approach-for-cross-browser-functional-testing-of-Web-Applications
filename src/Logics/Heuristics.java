package Logics;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom2.JDOMException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Drivers.BrowsersWebDrivers;

public class Heuristics {
	FetchingDOM fetchdom=new FetchingDOM();
	BrowsersWebDrivers dwd=new BrowsersWebDrivers();
	WebDriver driver;
	public void TimeHeuristic() throws IOException {
	TimeOutHeuristic.Get_Failure_point();
	}
	public void locatorHeuristic() throws IOException, JDOMException, InterruptedException {
		XpathLocatorFixer lf=new XpathLocatorFixer();
		lf.getAllpassedTestcases();

	}
	public void CSSlocatorHeuristic() throws IOException, JDOMException, InterruptedException {	
		String err=FetchingDOM.Get_Line_Info();
		System.out.println(err);
	}
	public void AssertionHeuristic() throws IOException, JDOMException, InterruptedException {
		Logics.AssertionHeuristic.Get_Failure_point();
		System.out.println("Assertion heuristic called..");
	}
	public void ElementNotInteractable() throws IOException {
		ElementNotInteractableHeuristic in=new ElementNotInteractableHeuristic();
		in.ParsingforNonInteractableElement();
		
	}
}
