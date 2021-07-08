package View;
import javafx.beans.property.SimpleStringProperty;
public class InputDetailsGetterSetters {
public SimpleStringProperty Testsuite;
public SimpleStringProperty browser;
public SimpleStringProperty Webapplication;
public InputDetailsGetterSetters(String sname, String bname, String aname) {
	this.Testsuite = new SimpleStringProperty(sname);
	this.browser = new SimpleStringProperty(bname);
	this.Webapplication = new SimpleStringProperty(aname);
}
public SimpleStringProperty getTestsuite() {
	return Testsuite;
}
public void setTestsuite(SimpleStringProperty testsuite) {
	Testsuite = testsuite;
}
public SimpleStringProperty getBrowser() {
	return browser;
}
public void setBrowser(SimpleStringProperty browser) {
	this.browser = browser;
}
public SimpleStringProperty getWebapplication() {
	return Webapplication;
}
public void setWebapplication(SimpleStringProperty webapplication) {
	Webapplication = webapplication;
}


}
