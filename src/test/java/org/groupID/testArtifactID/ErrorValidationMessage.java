package org.groupID.testArtifactID;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.pageObjects.CartPage;
import rahulShettyAcademy.pageObjects.CheckOutPage;
import rahulShettyAcademy.pageObjects.ConfirmationPage;
import rahulShettyAcademy.pageObjects.LandingPage;
import rahulShettyAcademy.pageObjects.ProductCatalog;
import testingComponents.BaseTest;
import testingComponents.Retry;

public class ErrorValidationMessage extends BaseTest {

	@Test(groups = "ErrorValidation" , retryAnalyzer = Retry.class)
	public void checkNegativeLogin() throws InterruptedException, IOException {

		landingObject.login("fakeemail123@tmpmail.org", "password");
		Assert.assertEquals("Incorrect email or password.", landingObject.getErrorMessage());
	}
	
	@Test
	public void verifyProductName() throws InterruptedException {
		ProductCatalog catalogue = landingObject.login("fakeemail@tmpmail.org", "Password1234");
		catalogue.getproductList();
		catalogue.addToCart("ZARA COAT 3");
		catalogue.toasterHandling();
		
		CartPage cartPageObject = catalogue.clickOnCart();
		boolean Match = cartPageObject.verifyProductInCart("ZARA COAT 3");
		Assert.assertTrue(Match);
	}

}
