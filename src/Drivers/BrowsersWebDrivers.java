package Drivers;

import org.openqa.selenium.WebDriver;

public class BrowsersWebDrivers {
	static WebDriver driver;
	static String url;

	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		BrowsersWebDrivers.url = url;
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		BrowsersWebDrivers.driver = driver;
	}
	
}
