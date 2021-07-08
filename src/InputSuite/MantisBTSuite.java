package InputSuite;
import java.io.FileWriter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.io.FileWriter;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.FileWriter;
import java.io.IOException;import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.concurrent.TimeUnit;
import Drivers.BrowsersWebDrivers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Drivers.BrowsersWebDrivers;
public class MantisBTSuite {
	BrowsersWebDrivers bvd=new BrowsersWebDrivers();
	private static WebDriver driver;
	static String browserDetail;
	@Parameters("browser")	
	@BeforeClass()
	public void Setup(String browser) throws Exception{	
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "F:\\geckodriver-v0.29.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			BrowsersWebDrivers.setDriver(driver);
			browserDetail=browser;
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "F:\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			BrowsersWebDrivers.setDriver(driver);
			browserDetail=browser;
		}
		else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver","F:\\edgedriver_win64 (3)\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			BrowsersWebDrivers.setDriver(driver);
			browserDetail=browser;
		
		
		}
		else if(browser.equalsIgnoreCase("Internetexplorer")) {
			System.setProperty("webdriver.ie.driver", "E:\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			BrowsersWebDrivers.setDriver(driver);
			browserDetail=browser;
		}
		else {
			throw new Exception("Browser is not correct");
		}
		if(browser.equalsIgnoreCase("chrome")||browser.equalsIgnoreCase("Edge")) {
			System.out.println("browser is:"+browser);
			driver.get(Constants.BASE_URL);
		}
		else if(browser.equalsIgnoreCase("Firefox")||browser.equalsIgnoreCase("InternetExplorer")) {
			System.out.println("browser is:"+browser);
			driver.get(Constants.INCOMPATIBLE_URL);
		}
		// Login User Administrator
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(Constants.ADMIN_USER_NAME);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Constants.ADMIN_PASSWORD);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
	}
	@Test(priority = 0)
	public static void addProjectTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//input[@value='Create New Project']")).click();
		Thread.sleep(1000);
		// Submit Form
		WebElement dropdown1 = driver.findElement(By.name("status"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("release");
		Thread.sleep(1000);
		WebElement dropdown2 = driver.findElement(By.name("view_state"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("public");
		Thread.sleep(1000);
		driver.findElement(By.name("name")).sendKeys("Project001 New");
		driver.findElement(By.name("description")).sendKeys("Description Description Description");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='Project001 New']")).getText(),
					"Project001 New");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 0)
	public static void addProjectTest001() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//input[@value='Create New Project']")).click();
		Thread.sleep(1000);
		// Submit Form
		WebElement dropdown1 = driver.findElement(By.name("status"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("release");
		Thread.sleep(1000);
		WebElement dropdown2 = driver.findElement(By.name("view_state"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("public");
		Thread.sleep(1000);
		driver.findElement(By.name("name")).sendKeys("Project001");
		driver.findElement(By.name("description")).sendKeys("Description Description Description");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='Project001']")).getText(),
					"Project001");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 1)
	public static void addProjectEmptyTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//input[@value='Create New Project']")).click();
		Thread.sleep(1000);
		// Submit Form
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"Invalid project name specified. Project names cannot be blank.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 2)
	public static void addProjectWrongTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//input[@value='Create New Project']")).click();
		Thread.sleep(1000);
		// Submit Form
		WebElement dropdown1 = driver.findElement(By.name("status"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("release");
		Thread.sleep(1000);
		WebElement dropdown2 = driver.findElement(By.name("view_state"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("public");
		Thread.sleep(1000);
		driver.findElement(By.name("name")).sendKeys("Project001 New");
		driver.findElement(By.name("description")).sendKeys("Description Description Description");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A project with that name already exists. Please go back and enter a different name.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 3)
	public static void addCategoryEmptyTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		driver.findElement(By.xpath("//input[@value='Add Category']")).click();
		//try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A necessary field \"\" was empty. Please recheck your inputs.");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
}
	@Test(priority = 4)
	public static void addCategoryTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		driver.findElement(By.name("category")).sendKeys("Category1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Category']")).click();
		//updated xpath
		WebElement e=driver.findElement(By.xpath("//td[contains(text(),'Category1')]"));
		System.out.println("text is :"+e.getText());
		System.out.println("tag is :"+e.getTagName());
		System.out.println("name is :"+e.getAttribute("name"));
		System.out.println("value is :"+e.getAttribute("value"));
		System.out.println("id is :"+e.getAttribute("id"));
		System.out.println("title is :"+e.getAttribute("title"));
		System.out.println("classname is :"+e.getAttribute("class"));
		System.out.println("Location is :"+e.getLocation());
		//try {
		//AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Category1')]")).getText(),"Category1");
		AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[text()='Category1'or contains(text(),'Category1')]")).getText(),"Category1");		
		//} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	@Test(priority = 5)
	public static void updateCategoryTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		
		try {
			driver.findElement(By.xpath("//input[@value='Edit']")).click();
			driver.findElement(By.name("new_category")).clear();
			driver.findElement(By.name("new_category")).sendKeys("Category1");
			WebElement dropdown1 = driver.findElement(By.name("assigned_to"));
			Select dropdownEle1 = new Select(dropdown1);
			dropdownEle1.selectByVisibleText("administrator");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update Category']")).click();
		Thread.sleep(3000);
		//try {
			AssertJUnit.assertEquals(
					driver.findElement(By.xpath("/html/body/div[7]/a[1]/table/tbody/tr[3]/td[1]")).getText(),
					"Category1");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	@Test(priority = 6)
	public static void addCategoryWrongTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(By.name("category")).sendKeys("Category1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Category']")).click();
		
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A category with that name already exists.");
					
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 7)
	public static void assignCategoryToUser() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		try {
			driver.findElement(By.xpath("//input[@value='Edit']")).click();
			driver.findElement(By.name("new_category")).clear();
			driver.findElement(By.name("new_category")).sendKeys("Category1");
			Thread.sleep(2000);
			WebElement dropdown1 = driver.findElement(By.name("assigned_to"));
			Select dropdownEle1 = new Select(dropdown1);
			dropdownEle1.selectByVisibleText("administrator");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update Category']")).click();
		Thread.sleep(3000);
		//try {
			AssertJUnit.assertEquals(
				
					driver.findElement(By.xpath("/html/body/div[7]/a[1]/table/tbody/tr[3]/td[1]")).getText(),
					"Category1");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	@Test(priority = 8)
	public static void deletCategoryTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		try {
			driver.findElement(By.xpath("//input[@value='Delete']")).click();
		} catch (Exception e) {
			Thread.sleep(4000);
			driver.findElement(By.xpath("//input[@value='Delete']")).click();
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Delete Category']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Edit Project");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 9)
	public static void addNewsTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("headline")).clear();
		driver.findElement(By.name("headline")).sendKeys("News HeadLine");
		driver.findElement(By.name("body")).clear();
		driver.findElement(By.name("body")).sendKeys("News HeadLine Body");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Post News']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='news-body']")).getText(),
					"News HeadLine Body");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 10)
	public static void editNewsTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		WebElement dropdown1 = driver.findElement(By.name("news_id"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("News HeadLine");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='edit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("headline")).clear();
		driver.findElement(By.name("headline")).sendKeys("News HeadLine");
		driver.findElement(By.name("body")).clear();
		driver.findElement(By.name("body")).sendKeys("News HeadLine Body Edit");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update News']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='news-body']")).getText(),
					"News HeadLine Body Edit");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 11)
	public static void addNewsTestMultiple() throws Exception {
		// Add Multiple News One
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("headline")).clear();
		driver.findElement(By.name("headline")).sendKeys("News HeadLine1");
		driver.findElement(By.name("body")).clear();
		driver.findElement(By.name("body")).sendKeys("News HeadLine Body1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Post News']")).click();
		Thread.sleep(5000);
		// Add Multiple News Two
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("headline")).clear();
		driver.findElement(By.name("headline")).sendKeys("News HeadLine2");
		driver.findElement(By.name("body")).clear();
		driver.findElement(By.name("body")).sendKeys("News HeadLine Body2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Post News']")).click();
		Thread.sleep(5000);
		try {
			try {
				AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='news-body']")).getText(),
						"News HeadLine Body2");
			} catch (Exception e) {
				try {
					AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='news-body']")).getText(),
							"News HeadLine Body1");
				} catch (Exception e2) {
					AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='news-body']")).getText(),
							"News HeadLine Body");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 12)
	public static void addNegativeNEwsTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Post News']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A necessary field \"Headline\" was empty. Please recheck your inputs.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 13)
	public static void editNegativeNewsTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		WebElement dropdown1 = driver.findElement(By.name("news_id"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("News HeadLine");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='edit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("headline")).clear();
		driver.findElement(By.name("body")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update News']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A necessary field \"Headline\" was empty. Please recheck your inputs.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 14)
	public static void DeleteNews() throws Exception {
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		WebElement dropdown1 = driver.findElement(By.name("news_id"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("News HeadLine");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='delete']")).click();
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Delete News Item']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Add News");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 15)
	public static void DeleteNewsMultipletest() throws Exception {
		// Delete News One
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		WebElement dropdown1 = driver.findElement(By.name("news_id"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("News HeadLine1");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='delete']")).click();
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Delete News Item']")).click();
		Thread.sleep(5000);
		// Delete News Two
		driver.findElement(By.xpath("//a[@href='news_menu_page.php']")).click();
		Thread.sleep(2000);
		WebElement dropdown2 = driver.findElement(By.name("news_id"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("News HeadLine2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='delete']")).click();
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Delete News Item']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Add News");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 16)
	public static void addUserTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Create New Account']")).click();
		Thread.sleep(1000);
		// Submit Form
		Thread.sleep(1000);
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Demo User New");
		driver.findElement(By.name("realname")).clear();
		driver.findElement(By.name("realname")).sendKeys("Demo User Real");
		driver.findElement(By.name("email")).sendKeys("demo@gmail.com");
		driver.findElement(By.xpath("//input[@value='Create User']")).click();
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(3000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='Demo User New']")).getText(),
					"Demo User New");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 17)
	public static void addUserEmptyTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Create New Account']")).click();
		Thread.sleep(1000);
		// Submit Form
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Create User']")).click();
		Thread.sleep(4000);
		// driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A necessary field \"\" was empty. Please recheck your inputs.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 18)
	public static void addMultipleUsersTest() throws Exception {
		// User One
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Create New Account']")).click();
		Thread.sleep(1000);
		// Submit Form
		// User Two
		Thread.sleep(1000);
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Demo User1");
		driver.findElement(By.name("realname")).clear();
		driver.findElement(By.name("realname")).sendKeys("Demo User1");
		driver.findElement(By.name("email")).sendKeys("demo1@gmail.com");
		driver.findElement(By.xpath("//input[@value='Create User']")).click();
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Create New Account']")).click();
		Thread.sleep(1000);
		// Submit Form
		Thread.sleep(1000);
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Demo User2");
		driver.findElement(By.name("realname")).clear();
		driver.findElement(By.name("realname")).sendKeys("Demo User2");
		driver.findElement(By.name("email")).sendKeys("demo2@gmail.com");
		driver.findElement(By.xpath("//input[@value='Create User']")).click();
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(3000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='Demo User2']")).getText(), "Demo User2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 19)
	public static void addUserWrongTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Create New Account']")).click();
		Thread.sleep(1000);
		// Submit Form
		Thread.sleep(1000);
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Demo User New");
		driver.findElement(By.name("realname")).clear();
		driver.findElement(By.name("realname")).sendKeys("Demo User Real");
		driver.findElement(By.name("email")).sendKeys("demo@gmail.com");
		driver.findElement(By.xpath("//input[@value='Create User']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"That username is already being used. Please go back and select another one.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 22)
	public static void deleteMultipleUserTest() throws Exception {
		// Delete User One
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Demo User1']")).click();
		driver.findElement(By.xpath("//input[@value='Delete User']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Delete Account']")).click();
		Thread.sleep(3000);
		// Delete User Two
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Demo User2']")).click();
		driver.findElement(By.xpath("//input[@value='Delete User']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Delete Account']")).click();
		Thread.sleep(3000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='administrator']")).getText(),
					"administrator");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 22)
	public static void deleteUserTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//a[text()='Demo User']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//a[text()='Demo User New']")).click();
		}
		driver.findElement(By.xpath("//input[@value='Delete User']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Delete Account']")).click();
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='administrator']")).getText(),
					"administrator");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 21)
	public static void updateUserTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Demo User New']")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("Demo User");
		driver.findElement(By.xpath("//input[@value='Update User']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Edit User");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 21)
	public static void updateUserEmptyTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Demo User New']")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Update User']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"The username is invalid. Usernames may only contain Latin letters, numbers, spaces, hyphens, and underscores.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 24)
	public static void resetUserPreferencesTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='account_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='account_prefs_page.php']")).click();
		driver.findElement(By.xpath("//input[@value='Reset Prefs']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Account Preferences");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 25)
	public static void addSubProjectTest() throws Exception {
		// Sub Project One
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Create New Subproject']")).click();
		Thread.sleep(2000);
		WebElement dropdown1 = driver.findElement(By.name("status"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("release");
		Thread.sleep(1000);
		WebElement dropdown2 = driver.findElement(By.name("view_state"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("public");
		Thread.sleep(1000);
		driver.findElement(By.name("name")).sendKeys("SubProject1");
		driver.findElement(By.name("description")).sendKeys("Description Description Description");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Thread.sleep(2000);
		// Sub Project Two
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Create New Subproject']")).click();
		Thread.sleep(2000);
		WebElement dropdown3 = driver.findElement(By.name("status"));
		Select dropdownEle3 = new Select(dropdown3);
		dropdownEle3.selectByVisibleText("release");
		Thread.sleep(1000);
		WebElement dropdown4 = driver.findElement(By.name("view_state"));
		Select dropdownEle4 = new Select(dropdown4);
		dropdownEle4.selectByVisibleText("public");
		Thread.sleep(1000);
		driver.findElement(By.name("name")).sendKeys("SubProject2");
		driver.findElement(By.name("description")).sendKeys("Description Description Description");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//a[text()='Project001']")).getText(), "Project001");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 26)
	public static void linkSubProjectTest() throws Exception {
		// Delete Project 1
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		Thread.sleep(3000);
		WebElement dropdown2 = driver.findElement(By.name("subproject_id"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("SubProject");
		driver.findElement(By.xpath("//input[@value='Add as Subproject']")).click();
		Thread.sleep(3000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Edit Project");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 27)
	public static void linkMulitpleSubProjectTest() throws Exception {
		// Link Project 1
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		Thread.sleep(3000);
		WebElement dropdown2 = driver.findElement(By.name("subproject_id"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("SubProject1");
		driver.findElement(By.xpath("//input[@value='Add as Subproject']")).click();
		Thread.sleep(3000);
		// Link Project 2
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		Thread.sleep(3000);
		WebElement dropdown = driver.findElement(By.name("subproject_id"));
		Select dropdownEle = new Select(dropdown);
		dropdownEle.selectByVisibleText("SubProject2");
		driver.findElement(By.xpath("//input[@value='Add as Subproject']")).click();
		Thread.sleep(3000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Edit Project");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 28)
	public static void updateProjectTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001 New']")).click();
		Thread.sleep(3000);
		WebElement dropdown1 = driver.findElement(By.name("status"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("release");
		Thread.sleep(1000);
		WebElement dropdown2 = driver.findElement(By.name("view_state"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("public");
		Thread.sleep(1000);
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Project001");
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("Description Description Description");
		driver.findElement(By.xpath("//input[@value='Update Project']")).click();
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 29)
	public static void updateProjectStatusTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		Thread.sleep(3000);
		WebElement dropdown1 = driver.findElement(By.name("status"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("development");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update Project']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 30)
	public static void updateProjectDescriptionTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys("Description Description Description");
		driver.findElement(By.xpath("//input[@value='Update Project']")).click();
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 31)
	public static void unlinkSubProjectTest() throws Exception {
		// Delete Project 1
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		try {
			List<WebElement> unlinkList = driver.findElements(By.xpath("//input[@value='Unlink']"));
			unlinkList.get(0).click();
			Thread.sleep(3000);
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Edit Project");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 32)
	public static void unlinkMultipleSubProjectTest() throws Exception {
		// Delete Project 1
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		try {
			List<WebElement> unlinkList = driver.findElements(By.xpath("//input[@value='Unlink']"));
			for (int i = 0; i < unlinkList.size(); i++) {
				unlinkList.get(i).click();
				Thread.sleep(3000);
			}
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Edit Project");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 33)
	public static void updateProjectViewStatusTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		Thread.sleep(3000);
		WebElement dropdown2 = driver.findElement(By.name("view_state"));
		Select dropdownEle2 = new Select(dropdown2);
		dropdownEle2.selectByVisibleText("private");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update Project']")).click();
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 35)
	public static void addProfileTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='account_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='account_prof_menu_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("platform")).clear();
		driver.findElement(By.name("platform")).sendKeys("Laptop");
		driver.findElement(By.name("os")).clear();
		driver.findElement(By.name("os")).sendKeys("Android");
		driver.findElement(By.name("os_build")).clear();
		driver.findElement(By.name("os_build")).sendKeys("V-10");
		driver.findElement(By.xpath("//input[@value='Add Profile']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Add Profile");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 36)
	public static void addNegativeProfileTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='account_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='account_prof_menu_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("platform")).clear();
		driver.findElement(By.name("os")).clear();
		driver.findElement(By.name("os_build")).clear();
		driver.findElement(By.name("os_build")).sendKeys("V-10");
		driver.findElement(By.xpath("//input[@value='Add Profile']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A necessary field \"Platform\" was empty. Please recheck your inputs.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 37)
	public static void editProfileTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='account_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='account_prof_menu_page.php']")).click();
		Thread.sleep(4000);
		WebElement dropdown1 = driver.findElement(By.name("profile_id"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("Laptop Android V-10");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='edit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("platform")).clear();
		driver.findElement(By.name("platform")).sendKeys("LaptopEdit");
		driver.findElement(By.name("os")).clear();
		driver.findElement(By.name("os")).sendKeys("Android");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update Profile']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Add Profile");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 38)
	public static void editNegativeProfileTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='account_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='account_prof_menu_page.php']")).click();
		Thread.sleep(4000);
		WebElement dropdown1 = driver.findElement(By.name("profile_id"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("Laptop Android V-10");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='edit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("platform")).clear();
		driver.findElement(By.name("os")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Update Profile']")).click();
		Thread.sleep(5000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//p[@style='color:red']")).getText(),
					"A necessary field \"Platform\" was empty. Please recheck your inputs.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 39)
	public static void deleteProfileTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='account_page.php']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='account_prof_menu_page.php']")).click();
		Thread.sleep(5000);
		WebElement dropdown1 = driver.findElement(By.name("profile_id"));
		Select dropdownEle1 = new Select(dropdown1);
		dropdownEle1.selectByVisibleText("LaptopEdit Android V-10");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='delete']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(3000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(),
					"Add Profile");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 40)
	public static void deleteProjectTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		driver.findElement(By.xpath("//a[text()='Project001']")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 41)
	public static void deleteSubProjectTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='SubProject']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 42)
	public static void deleteMultipleSubProjectTest() throws Exception {
		// Delete Project 1
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//a[text()='SubProject1']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//a[text()='» SubProject1']")).click();
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		// Delete Project 2
		driver.findElement(By.xpath("//a[@href='manage_user_page.php']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='manage_proj_page.php']")).click();
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("//a[text()='SubProject2']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//a[text()='» SubProject2']")).click();
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Delete Project']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Projects");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 43)
	public static void ViewIssueTest() throws Exception {
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[3]")).click();
		assertTrue(driver.getPageSource().contains("Viewing Issues"));
	}
	@Test(priority = 44)
	public static void ReportTest() throws Exception {
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[4]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr[4]/td/input")).click();
		try {
			assertTrue(driver.getPageSource().contains( "Enter Report Details"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test(priority = 45)
	public static void ChangeLogTest() throws Exception {
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[5]")).click();
	}
	@Test(priority = 46)
	public static void roadMapTest() throws Exception {
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[6]")).click();
	}
	@Test(priority = 47)
	public static void SummaryTest() throws Exception {
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[7]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/span/a")).click();
		driver.findElement(By.xpath("/html/body/form[1]/table/tbody/tr/td/a[1]/img")).click();
		assertTrue(driver.getPageSource().contains("Mantis - All Projects"));
		if(browserDetail.equalsIgnoreCase("chrome")||browserDetail.equalsIgnoreCase("Edge")) {
			System.out.println("browser is:"+browserDetail);
			driver.navigate().to("http://localhost/mantisbt-1.1.8/summary_page.php");
		}
		else if(browserDetail.equalsIgnoreCase("Firefox")||browserDetail.equalsIgnoreCase("InternetExplorer")) {
			System.out.println("browser is:"+browserDetail);
			driver.navigate().to("http://localhost/mantisbt-1.1.8Incompatible/summary_page.php");
		}
	}
	
	@Test(priority = 48)
	public static void logoutTest() throws Exception {
		driver.findElement(By.xpath("//a[@href='logout_page.php']")).click();
		Thread.sleep(3000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//td[@class='form-title']")).getText(), "Login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 49)
	public static void nagativeLoginTest() throws Exception {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("No User");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Constants.ADMIN_PASSWORD);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(2000);
		try {
			AssertJUnit.assertEquals(driver.findElement(By.xpath("//font[@color='red']")).getText(),
					"Your account may be disabled or blocked or the username/password you entered is incorrect.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}//end suite
