package com.marketplace.www.Selenium_Automation;


import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "Zara coat 33";
@Test
public void submitOrder() throws IOException {
		
		String countryName = "Aus"; // this line of code is not dynamic
		String existingmessage = "Thankyou for the order.";

		
		
		//LandingPage landingpage =launchApplication();// ?????
		//LandingPage landingpage =new landingpage(driver);
		
		//landingpage.goTo("https://rahulshettyacademy.com/client");
		landingpage.loginApplication("pass23@yopmail.com", "Sachin@123");
		//landingpage class level variable
		

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();

		
		
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.goToCheckOut();

		
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		checkoutpage.selectCountry(countryName);
		checkoutpage.submitOrder();

		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String confirmedmessage = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmedmessage.toLowerCase(), existingmessage.toLowerCase());
		//driver.close();

	}

@Test(dependsOnMethods = {"submitOrder"})
public void orderHistoryTest(){
	
	ProductCatalogue productCatalogue =landingpage.loginApplication("pass23@yopmail.com", "Sachin@123");
	
	OrderPage orderspage =productCatalogue.goToOrdersPage();
	orderspage.VerifyOrderDisplay(productName);
	
	Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
	
}


}
