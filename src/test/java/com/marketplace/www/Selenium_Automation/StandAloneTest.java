package com.marketplace.www.Selenium_Automation;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest  {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		driver.get("https://rahulshettyacademy.com/client");            
		

		driver.findElement(By.id("userEmail")).sendKeys("pass23@yopmail.com");// useremail
		driver.findElement(By.id("userPassword")).sendKeys("Sachin@123"); // password
		driver.findElement(By.id("login")).click();// Loginbutton
		
		  Timestamp time1 = new Timestamp(System.currentTimeMillis());

		System.out.println(time1);

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		String productname ="ZARA COAT 3";

		for (int i = 0; i < products.size(); i++) {

			if (products.get(i).getText().contains(productname)) { // products.get(i).getText() will get entire text
				// not only our text thats why we use contains.check output
				WebElement button = products.get(i).findElement(By.cssSelector(".card-body button:last-of-type"));
				button.click();
				System.out.println("Product " + products.get(i).getText() + " added sucessfully");
				break;
			}

		}

		// toast message

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()=' Product Added To Cart ']")));
		WebElement toast = driver.findElement(By.xpath("//div[text()=' Product Added To Cart ']"));
		  Timestamp time2 = new Timestamp(System.currentTimeMillis());
		System.out.println(time2);
		/*
		 * WebElement wait1 = new WebDriverWait(driver, Duration.ofSeconds(10))
		 * .until(ExpectedConditions.elementToBeClickable(By.
		 * xpath("//div[text()=' Product Added To Cart ']")));
		 */

		if (toast.getText().contains("Product Added To Cart")) {
			System.out.println("Toast Message success");
		} else {
			System.out.println("Toast Message failed");
		}

		// loader icon confirmation
		// WebElement loadingicon =
		// driver.findElement(By.xpath("//div[@class*='ng-tns-c31-2']"));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));

		// () expected is locator not webelement
		// WebElement loadingicon = driver.findElement(By.cssSelector("ng-animating"));
		/*
		 * if(wait.until(ExpectedConditions.invisibilityOf(loadingicon))) {
		 * System.out.println("Loading icon disappeared"); } else {
		 * System.out.println("Icon is still loading"); }
		 */

		if (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")))) {
			System.out.println("Loading icon disappeared");
		}

		WebElement cart = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
		cart.click();
		System.out.println("clicked on cart");
		
		  Timestamp time3 = new Timestamp(System.currentTimeMillis());
		System.out.println(time3);
		
		//Verify products added to cart
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".wrap h3"));
		
		/*
		 * cartproducts.stream() .filter(cartproduct->cartproduct.getText()//
		 * cartproducts=WebElements
		 * .equalsIgnoreCase(productname));//cartproduct=WebElement productname=String
		 */		
		System.out.println(cartproducts.get(0).getText());
		
		Boolean match = cartproducts.stream()
		.anyMatch(cartproduct->cartproduct.getText().contains(productname));// cartproducts=WebElements      
		//.equalsIgnoreCase(productname));//cartproduct=WebElement   productname=String
		Assert.assertTrue( match);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		
		driver.findElement(By.xpath("//section/button[@type='button'][2] ")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmedmessage = driver.findElement(By.cssSelector(".hero-primary")).getText().toLowerCase();
		String existingmessage = "Thankyou for the order.";
		System.out.println(confirmedmessage +","+ existingmessage);
		Assert.assertEquals(confirmedmessage.toLowerCase(),existingmessage.toLowerCase());
		System.out.println(confirmedmessage.toLowerCase() +","+ confirmedmessage);
	}

}
