package com.marketplace.www.Selenium_Automation;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	@Test
	public void LoginErrorValidation() throws IOException {

		landingpage.loginApplication("pass23@yopmail.com", "hjhgjhjhj");
		landingpage.getErrorMessage();

		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException {
		String productName = "ADIDAS";

		landingpage.loginApplication("pass23@yopmail.com", "Sachin@123");

		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();

		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
