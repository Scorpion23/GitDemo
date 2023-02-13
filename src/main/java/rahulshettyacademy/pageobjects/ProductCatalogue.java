package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue  extends AbstractComponents {
	WebDriver driver;
	String productName;
	public ProductCatalogue(WebDriver driver) { // the arguments scope is within this method so in order to 
		
		super(driver);
		this.driver = driver;//retains the scope outside the method we initialize the driver.
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3"); //By is a class/data type for By locators
	By addToCart =By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage = By.xpath("//div[text()=' Product Added To Cart ']");
	
	By spinner =By.cssSelector("ng-animating");
	
	By cartlist =By.cssSelector(".wrap h3");
	
	public List<WebElement> getProductsList() 
	{
		waitForElementToAppear(productsBy);
		return products;

	}
	

	//returns webelement to add product into cart
	public WebElement getProductByName(String productName) {
	    for (int i = 0; i < products.size(); i++) {
	        if (products.get(i).getText().contains(productName)) {
	            return products.get(i);
	        }
	    }
	    return null;
	}
	
	
	//adds selected product to cart need  getProductByName method
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);//scope limited to the webelement   //45th line code
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	//note: in main method addProductToCart is called which again calls getProductByName
	
	// and then click operation is performed
	
	//verify products added to cart o not
	public void getProductByName() {
		
		List<WebElement> cartproducts = driver.findElements(cartlist);

		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct
						.getText().contains(productName));
		

		
	}
	
	
}
