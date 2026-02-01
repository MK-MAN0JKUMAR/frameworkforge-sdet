package framework.pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.driver.DriverManager;

public abstract class BasePageObjects {

    protected WebDriver driver;
    private final WebDriverWait wait;
    protected Logger log = LogManager.getLogger(this.getClass());


    protected BasePageObjects() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void waitForVisible(WebElement element) {
        log.info("Wait for visible: {}", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element) {
        waitForVisible(element);
        log.info("Clicking element: {}", element);
        element.click();
    }

    protected void type(WebElement element, String value) {
        waitForVisible(element);
        element.clear();
        log.info("Enter value element: {} {}", element, value);
        element.sendKeys(value);
    }
}
