package rahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// initializes the page element so that you can work directly on the element
		// without getting the NullPointerException
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsBy = By.cssSelector(".mb-3");

	@FindBy(id = "toast-container")
	WebElement spinner;

	By toaster = By.id("toast-container");

	By addToCart = By.cssSelector(".mb-3 button:last-of-type");


	public List<WebElement> getproductList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	// @FindBy(css = ".mb-3 button:last-of-type")
	// WebElement addToCart;

	public List<WebElement> getProductList() {

		waitForElementToAppear(productsBy);
		return products;
	}

	// this is will return an element by it`s text
	public WebElement getproductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(name -> name.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addToCart(String productName) {
		WebElement item = getproductByName(productName);
		item.findElement(addToCart).click();
	}

	public void toasterHandling() throws InterruptedException {
		waitForElementToAppear(toaster);
		waitForElementToDisAppear(spinner);
	}

	

}
