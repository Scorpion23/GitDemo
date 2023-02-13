package rahulshettyacademy.TestComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver  driver;
	public LandingPage landingpage;
	
	//to get new chromeDriver() or new FirefoxDriver() 
	
	public WebDriver  initializeDriver() throws IOException {  //select the WebDriverbrowser using .properties file
		
		//properties class object to read text from the .properties file

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			
			  } else if (browserName.equalsIgnoreCase("firefox")) {
			  WebDriverManager.firefoxdriver().setup(); 
			  driver = new FirefoxDriver();
			 
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.manage().window().maximize();
		return driver;
}
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver(); /// ? driver = new ChromeDriver();
		 landingpage = new LandingPage(driver); // instance variable of the 
		//Landing Page Object created at class level so it is accessible by other classes also
		landingpage.goTo("https://rahulshettyacademy.com/client");
		return landingpage;// returning the instance i.e driver info
	}
	//@AfterMethod
	
	public void tearDown() {
		driver.close();
	}
}
