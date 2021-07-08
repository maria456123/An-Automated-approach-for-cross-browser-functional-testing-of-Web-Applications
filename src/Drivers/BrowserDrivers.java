package Drivers;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
public class BrowserDrivers {
	static WebDriver driver;
	@Parameters("browser")	
	@BeforeClass()
	public static void Setup(@Optional("chrome") String browser) throws Exception{	
		System.out.println("browser is:"+browser);
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Usman\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Usman\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = null;
			ChromeOptions chromeoptions=new ChromeOptions();
			chromeoptions.addExtensions(new File("F:\\Xpath generator Crx.crx"));
			DesiredCapabilities desiredcapabilities=DesiredCapabilities.chrome();
			desiredcapabilities.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
			driver=new ChromeDriver(desiredcapabilities);
			driver.get("http://localhost/addressbook/");
			driver.findElement(By.name("user")).sendKeys("admin");
			driver.findElement(By.name("pass")).sendKeys("secret");
			driver.findElement(By.xpath("/html/body/div/div[4]/form/input[3]")).click();
			driver.findElement(By.xpath("//*[@id=\"nav\"]/ul/li[3]/a")).click();
			Assert.assertEquals("Groups | Address Book", driver.getTitle());
			
			//driver.get("chrome-extension://dphfifdfpfabhbkghlmnkkdghbmocfeb/panel.html");	
			//driver.findElement(By.xpath("/html/body/div")).click();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver","C:\\Users\\Usman\\Downloads\\edgedriver_win64 (2)\\msedgedriver.exe");
			driver = new EdgeDriver();	
		}
		else {
			throw new Exception("Browser is not correct");
		}

	}
	public static void main(String args[]) throws Exception {
		String chrome="chrome";
		Setup(chrome);
	}
}
