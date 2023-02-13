package testing;

import java.sql.DriverManager;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class myclas {

	public static void main(String[] args) {
		WebDriver driver;
		String  productName = "adidas";
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("pass23@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sachin@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='flyInOut']")));
		//WebElement element = driver.findElement(By.cssSelector("[class*='flyInOut']"));
		//driver.findElement(By.cssSelector("[class*='flyInOut']")).getText();
		driver.findElement(By.id("toast-container")).getText();
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		driver.findElement(By.cssSelector("[routerlink*='myorders']")).click();
		


        List<WebElement> elements = driver.findElements(By.xpath("//tr/td[2]"));

            
            for(int i=0;i<elements.size();i++) {
            	elements.get(i).getText();
            	System.out.println(elements.get(i).getText());
            }
}
}


