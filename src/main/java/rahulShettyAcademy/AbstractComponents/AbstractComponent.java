package rahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulShettyAcademy.pageObjects.CartPage;
import rahulShettyAcademy.pageObjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	@FindBy(css = "[routerlink*='/dashboard/cart']")
	WebElement cartlink;

	@FindBy(css = "[routerlink*='/dashboard/myorders']")
	WebElement myOrders;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public OrdersPage gotoOrdersPage() {
		waitForElementToAppear(myOrders);
		myOrders.click();
		OrdersPage orders = new OrdersPage(driver);
		return orders;
	}
	
	public CartPage clickOnCart() {
		cartlink.click();

		CartPage cartPageObject = new CartPage(driver);
		return cartPageObject;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementToDisAppear(WebElement element) throws InterruptedException {

		// maybe you can discard the two lines below and use Thread.sleep as the two
		// lines are executed on spinner that appeared then disappeared and new one
		// appeared
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.invisibilityOf(element));

		Thread.sleep(6000);
	}

}
