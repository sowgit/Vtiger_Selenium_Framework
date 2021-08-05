package com.crm.vtiger.GenericUtils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.crm.vtiger.HomepagePomclass.HomePage;
import com.crm.vtiger.LoginpagePomclass.LoginPage;

/**
 * Baseclass configuration
 * @author Adarsh
 *
 */

public class BaseClass {
	public WebDriver driver;
	public static WebDriver staticDriver;
	public ExcelUtility eUtil=new ExcelUtility();
	public FileUtility fUtil=new FileUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public DataBaseUtilities dblib = new DataBaseUtilities();
	public HomePage homePAge;

	@BeforeSuite()
	public void configBS() throws Throwable {
		//connect to DB
		dblib.connectToDB();
	}
	@BeforeTest()
	public void configBT() {
		//launch browser in parallel mode
	}
	@BeforeClass()
	public void configBC() throws Throwable {

		String browserName=fUtil.getPropertyKeyValue("browser");

		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")) {
			driver=new InternetExplorerDriver();
		}
		staticDriver=driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeMethod()
	public void configBM() throws Throwable {
		String url=fUtil.getPropertyKeyValue("url");
		String username=fUtil.getPropertyKeyValue("username");
		String password=fUtil.getPropertyKeyValue("password");
		driver.get(url);
		//login to the application
		LoginPage loginPage=new LoginPage(driver);
		homePAge = loginPage.login(username, password);
	}
	@AfterMethod()
	public void configAM() throws Throwable {
		HomePage  homePage = new HomePage(driver);
		homePage.signOut();
	}
	@AfterClass()
	public void configAC() {
		driver.quit();
	}
	@AfterTest()
	public void configAT() {
		//close driver ref in parallel mode
	}
	@AfterSuite()
	public void configAS() throws Throwable {
		// close DB connection
		dblib.closeDb();
	}

}
