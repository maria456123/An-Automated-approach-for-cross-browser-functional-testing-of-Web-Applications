package Logics;

import javafx.beans.property.SimpleStringProperty;

public class TestData {

	SimpleStringProperty browserName;
	SimpleStringProperty testCaseName;
	SimpleStringProperty testCaseStatus;
	SimpleStringProperty Failure_LOC;
	SimpleStringProperty size;
	SimpleStringProperty heuristicStatus;
	public SimpleStringProperty getHeuristicStatus() {
		return heuristicStatus;
	}
	public void setHeuristicStatus(SimpleStringProperty heuristicStatus) {
		this.heuristicStatus = heuristicStatus;
	}
	TestData(String browserName, String testCaseName, String testCaseStatus,String size,String Failure_LOC, String heuristicStatus) {
		this.browserName = new SimpleStringProperty(browserName);
		this.testCaseName = new SimpleStringProperty(testCaseName);
		this.testCaseStatus = new SimpleStringProperty(testCaseStatus);		
		this.size = new SimpleStringProperty(size);
		this.Failure_LOC=new SimpleStringProperty(Failure_LOC);
		this.heuristicStatus=new SimpleStringProperty(heuristicStatus);
	}
	TestData(String browserName, String testCaseName, String testCaseStatus) {
		this.browserName = new SimpleStringProperty(browserName);
		this.testCaseName = new SimpleStringProperty(testCaseName);
		this.testCaseStatus = new SimpleStringProperty(testCaseStatus);		
	}
	public String getBrowserName(){
		return browserName.get();
	}
	public void setBrowserName(String fname){
		browserName.set(fname);
	}

	public String getTestCaseName(){
		return testCaseName.get();
	}
	public void setTestCaseName(String fpath){
		testCaseName.set(fpath);
	}
	public String getTestCaseStatus(){
		return testCaseStatus.get();
	}
	public void setTestCaseStatus(String fsize){
		testCaseStatus.set(fsize);
	}
	 public String getSize(){
	      return size.get();
	   }
	   public void setSize(String fsize){
	      size.set(fsize);
	   }
		public String getFailure_LOC() {
			return Failure_LOC.get();
		}
		public void setFailure_LOC(String failure_LOC) {
			Failure_LOC.set(failure_LOC);
		}
}

