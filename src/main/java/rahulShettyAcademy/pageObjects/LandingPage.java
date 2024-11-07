package rahulShettyAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver; // before creating constructor , the below driver has no life , just null value
	// so we must create constructor and this constructor will take the life from testing classes in the POM design
	// as we are here in the pages classes
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
		// initializes the page element so that you can work directly on the element without getting the NullPointerException
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(id = "toast-container")
	WebElement spinner;
	
	public ProductCatalog login(String userEmail , String userPassword) {
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		login.click();
		ProductCatalog catalogue = new ProductCatalog(driver);
		return catalogue;
	}

	public void GoToMainPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(spinner);
		return spinner.getText();
	}
	
	

}
