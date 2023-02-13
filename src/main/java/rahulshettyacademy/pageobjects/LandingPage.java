package rahulshettyacademy.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents  {
	WebDriver driver;
	public LandingPage(WebDriver driver) { // the arguments scope is within this method so in order to 
		
		super(driver);
		this.driver = driver;//retaint the scope outside the method we initialize the driver.
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));// here we get error for driver
	//write this code in shorter syntax
	
	@FindBy(id="userEmail")
	WebElement userEmail;//new variable
	
	@FindBy(id="userPassword")//WEbElement
	WebElement passworde;//initializing the webelement with a variable name
	
	@FindBy(id="login")
	WebElement submit;
	

	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	@FindBy(id="toast-container")
	WebElement errorMessage2;
	   
	//Actions
	public void goTo(String url) {
		driver.get(url);
	}
	
	public ProductCatalogue loginApplication(String email,String password){
		userEmail.sendKeys(email);
		passworde.sendKeys(password);//argument and webelement name should be different
		submit.click();
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public String getErrorMessage() {
		waitForElementToAppearw(errorMessage);
		System.out.println(errorMessage.getText());
		return errorMessage.getText();
		
	}
	
}
