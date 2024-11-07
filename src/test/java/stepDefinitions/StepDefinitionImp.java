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

public class StepDefinitionImp extends BaseTest{
	
	public LandingPage cucumberlandingPageObject;
	public ProductCatalog cucumbercatalogueObject;
	public CartPage cucumbercartPageObject;
	public CheckOutPage cucumbercheckOutObject;
	public ConfirmationPage cucumberconfirmationObject;
/*
	@Given("user is landed on the commerce page")
	public void user_is_landed_on_the_commerce_page() throws IOException {
		cucumberlandingPageObject = lanuchApplication();
	}
	*/
	     
	@Given("^user is logged in with username (.+) and password (.+)$")
	public void user_is_logged_in_with_username_and_password(String username , String password) throws IOException{
		cucumberlandingPageObject = lanuchApplication();
		cucumbercatalogueObject = cucumberlandingPageObject.login(username, password);
	}
	
	@When("^add (.+) to the cart$")
	public void add_productName_to_the_cart(String productName) throws InterruptedException {
		cucumbercatalogueObject.getproductList();
		cucumbercatalogueObject.addToCart(productName);
		cucumbercatalogueObject.toasterHandling();
	}
	
	@When("^checkout (.+) and submit order$")
	public void checkout_productName_and_submit_order(String productName) {
		cucumbercartPageObject = cucumbercatalogueObject.clickOnCart();
		boolean Match = cucumbercartPageObject.verifyProductInCart(productName);
		AssertJUnit.assertTrue(Match);
		
		cucumbercheckOutObject = cucumbercartPageObject.goToCheckout();
		cucumbercheckOutObject.sendCountryName("Egypt");
		cucumbercheckOutObject.selectCountry();
	}
	
	@Then("{string} is displayed on confirmation page")
	public void successMsg_is_displayed_on_confirmation_page(String thanksMesg) {
		cucumberconfirmationObject = cucumbercheckOutObject.placeOrder();
		String message = cucumberconfirmationObject.getMessage();
		
		Assert.assertTrue(message.equalsIgnoreCase(thanksMesg));
		driver.close();
	}
	
}
