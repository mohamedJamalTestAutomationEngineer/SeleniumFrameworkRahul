package rahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// initializes the page element so that you can work directly on the element
		// without getting the NullPointerException
	}

	@FindBy (css = "tr td:nth-child(3)")
	List<WebElement> productsname;
	
	By ProducsNameBy = By.cssSelector("tr td:nth-child(3)");

	public Boolean getProductName(String productName) {
	
		waitForElementToAppear(ProducsNameBy);
		boolean match = productsname.stream().anyMatch(name->name.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
