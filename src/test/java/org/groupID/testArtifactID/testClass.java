package org.groupID.testArtifactID;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import rahulShettyAcademy.pageObjects.OrdersPage;
import rahulShettyAcademy.pageObjects.ProductCatalog;
import testingComponents.BaseTest;

public class testClass extends BaseTest {

	@Test(dataProvider = "getData" , groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		String countryNameSearch = "egy";
		String thanksMesg = "Thankyou for the order.";

		ProductCatalog catalogue = landingObject.login(input.get("email"), input.get("password"));
		catalogue.getproductList();
		catalogue.addToCart(input.get("productName"));
		catalogue.toasterHandling();
		
		CartPage cartPageObject = catalogue.clickOnCart();
		boolean Match = cartPageObject.verifyProductInCart(input.get("productName"));
		AssertJUnit.assertTrue(Match);
		
		CheckOutPage checkOutObject = cartPageObject.goToCheckout();
		checkOutObject.sendCountryName(countryNameSearch);
		checkOutObject.selectCountry();
		
		ConfirmationPage confirmationObject = checkOutObject.placeOrder();
		String message = confirmationObject.getMessage();
		
		Assert.assertTrue(message.equalsIgnoreCase(thanksMesg));

	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void getOrderHistory() throws InterruptedException {
		ProductCatalog catalog = landingObject.login("fakeemail@tmpmail.org" , "Password1234");
		OrdersPage orders = catalog.gotoOrdersPage();
		Assert.assertTrue(orders.getProductName("ZARA COAT 3"));
		
	}
	
	/*
	@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"fakeemail@tmpmail.org","Password1234","ZARA COAT 3"},{"oppps@teml.net","Password1234","ADIDAS ORIGINAL"}};
	}
	*/
	
	/*
	@DataProvider
	public Object[][] getData(){
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "fakeemail@tmpmail.org");
		map1.put("password", "Password1234");
		map1.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "oppps@teml.net");
		map2.put("password", "Password1234");
		map2.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] { {map1},{map2} };
	}
	*/
	
	
	@DataProvider
	public Object[][] getData() throws IOException{

		List<HashMap<String, String>> map =
				getDataFromJSON(System.getProperty("user.dir") + "\\src\\test\\java\\dataPreparation\\purchaseOrder.json");
		return new Object[][] {{map.get(0)} , {map.get(1)}};		
	}


}
