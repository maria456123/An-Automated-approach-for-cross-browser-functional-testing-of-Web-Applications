package Logics;
public class TestCaseDetailsGettersSetters {
	private String BrowserName;
	private String TestcaseName;
	private  String TestcaseStatus;
	private String FailureReason;
	private String FailurePoint;

	TestCaseDetailsGettersSetters(String bname, String tname, String tstatus,String FailureReason,String FailurePoint) {
		this.BrowserName = bname;
		this.TestcaseName= tname;
		this.TestcaseStatus = tstatus;
		this.FailureReason=FailureReason;
		this.FailurePoint=FailurePoint;
		
	}
	TestCaseDetailsGettersSetters() {
		this.BrowserName = "";
		this.TestcaseName= "";
		this.TestcaseStatus ="";
		this.FailureReason="";
		this.FailurePoint="";
	}
	public String getBrowserName() {
		return BrowserName;
	}
	public void setBrowserName(String bName) {
		this.BrowserName=bName;
	}

	public String TestcaseName() {
		return TestcaseName;
	}
	public void setTestcaseName(String tName) {
		this.TestcaseName=tName;
	}
	public String TestcaseStatus() {
		return TestcaseStatus;
	}
	public void setTestcaseStatus(String tStatus) {
		this.TestcaseStatus=tStatus;
	}
	public String getFailureReason() {
		return FailureReason;
	}
	public void setFailureReason(String localizedMessage) {
		this.FailureReason=localizedMessage;
	}
	public String getFailurePoint() {
		return FailurePoint;
	}
	public void setFailurePoint(String failurePoint) {
		this.FailurePoint = failurePoint;
	}

}

