package Logics;

public class HeuristicSuggester {

	public static String heuristicForIssues(String actualException) {

		Heuristics h=new Heuristics();
		String reasondisplayed = null;
		try {
			if(actualException.contains("Unable to locate element")) {
				if(actualException.contains(("css selector"))||actualException.contains("*** Element info: {Using=css selector")){
					reasondisplayed="NoSuchElementException: Unable to Locate Element Method CSS selector";	

				}
				else if(actualException.contains("xpath")||actualException.contains("unable to find element with xpath")) {
					reasondisplayed="NoSuchElementException:Unable to Locate Element Method Xpath";
					h.locatorHeuristic();
				}

				else {
					reasondisplayed="NoSuchElementException:Unable to Locate Element simple";
					//h.locatorHeuristic();
				}
			}
			else if(actualException.contains("ElementNotInteractableException")||actualException.contains("could not be scrolled into view")||actualException.contains("element not interactable")) {
				reasondisplayed="Element not Interactable";
				h.ElementNotInteractable();
			}
			else if(actualException.contains("waiting for an element to be clickable")||actualException.contains("Timed out")||actualException.contains("TimeoutException:")||actualException.contains("waiting for page to load")){			
				reasondisplayed="TimeOutException";
				h.TimeHeuristic();
			}
			else if(actualException.contains("stale element")){
				reasondisplayed="StaleElementReferenceException";
			}
			else if(actualException.contains("FileInputStream")) {
				reasondisplayed="File not found at specified path";
			}
			else if(actualException.contains("but found")) {
				reasondisplayed="Assertion Failed";
				h.AssertionHeuristic();
			}
			else if(actualException.contains("The system cannot find the path specified")) {
				reasondisplayed="File path is Incorrect";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return reasondisplayed;

	}
}
