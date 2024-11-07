package rahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// initializes the page element so that you can work directly on the element
		// without getting the NullPointerException
	}
	
	@FindBy (className = "action__submit")
	WebElement submit;
	
	By submitBy = By.className("action__submit");
	
	@FindBy (css = "[placeholder='Select Country']")
	WebElement countryBox;
	
	@FindBy (css = ".ta-item")
	WebElement countries;
	
	By countryList = By.cssSelector(".ta-item");
	
	
	@FindBy (xpath = "(//button[@class='ta-item list-group-item ng-star-inserted'])[1]")
	WebElement selectedCountry;
	
	public void sendCountryName(String countryName) {
		waitForElementToAppear(submitBy);
		Actions a = new Actions(driver);
		a.sendKeys(countryBox, countryName).build().perform();
	}
	
	public void selectCountry() {
		waitForElementToAppear(countryList);
		selectedCountry.click();
	}
	
	public ConfirmationPage placeOrder() {
		submit.click();
		ConfirmationPage confirmObject = new ConfirmationPage(driver);
		return confirmObject;
	}
	
	

}
