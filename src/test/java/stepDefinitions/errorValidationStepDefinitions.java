package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulShettyAcademy.pageObjects.CartPage;
import rahulShettyAcademy.pageObjects.CheckOutPage;
import rahulShettyAcademy.pageObjects.ConfirmationPage;
import rahulShettyAcademy.pageObjects.LandingPage;
import rahulShettyAcademy.pageObjects.ProductCatalog;
import testingComponents.BaseTest;

public class errorValidationStepDefinitions extends BaseTest{
	
	public LandingPage cucumberlandingPageObject;
	public ProductCatalog cucumbercatalogueObject;
	
//    Given user is on landing page
//    When I logged in with username <username> and password <password>
//    Then "Incorrect email or password." message is displayed
    
    @Given("user is on landing page")
    public void user_is_on_landing_page() throws IOException {
    	cucumberlandingPageObject = lanuchApplication();
    }
    
    @When("^I logged in with username (.+) and password (.+)$")
    public void I_logged_in_with_username_and_password(String username , String password) {
    	cucumberlandingPageObject.login(username, password);
    }

    @Then("{string} message is displayed")
    public void validation_message_is_displayed(String message) {
    
  		Assert.assertEquals(message,cucumberlandingPageObject.getErrorMessage());
		driver.close();
    }
    
}
