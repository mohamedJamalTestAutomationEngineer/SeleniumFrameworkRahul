package rahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// initializes the page element so that you can work directly on the element
		// without getting the NullPointerException
	}


	@FindBy(css = ".cartSection h3")
	List<WebElement> productsNameInCart;

	By ProductsNameBy = By.cssSelector(".cartSection h3");

	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	public boolean verifyProductInCart(String productName) {
		waitForElementToAppear(ProductsNameBy);
		boolean match = productsNameInCart.stream().anyMatch(name -> name.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckout() {
		checkOut.click();
		CheckOutPage checkOutObject = new CheckOutPage(driver);
		return checkOutObject;
	}


}
