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

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from
	// testing classes in the POM design
	// as we are here in the pages classes

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// initializes the page element so that you can work directly on the element
		// without getting the NullPointerException
	}
	
	@FindBy (className = "hero-primary")
	WebElement mesg;
	
	By classMesg = By.className("hero-primary");
	
	
	public String getMessage() {
		waitForElementToAppear(classMesg);
		return mesg.getText();
	}
	
	

}
