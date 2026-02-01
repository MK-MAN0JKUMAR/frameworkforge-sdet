package framework.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.driver.DriverManager;

public abstract class BasePageObjects {

	protected WebDriver driver;
	private final WebDriverWait wait;

	protected BasePageObjects() {
		this.driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	protected void waitForVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void click(WebElement element) {
		waitForVisible(element);
		element.click();
	}

	protected void type(WebElement element, String value) {
		waitForVisible(element);
		element.clear();
		element.sendKeys(value);
	}
}
