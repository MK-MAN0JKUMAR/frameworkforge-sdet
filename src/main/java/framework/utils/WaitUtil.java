package framework.utils;

import framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtil {

    private static final Logger log = LogManager.getLogger(WaitUtil.class);
    private static final int DEFAULT_TIMEOUT = 5;

    private WaitUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /* =========================
       CORE WAIT (FindBy/Lazy initialize)
       ========================= */

    public static void waitForVisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(seconds)
        );
        log.info("Waiting for visibility: {}", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisible(WebElement element) {
        waitForVisible(element, DEFAULT_TIMEOUT);
    }


    /* =========================
       wait for clickable and return safe element
       ========================= */

    public static WebElement waitForClickable(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(seconds)
        );
        log.info("Waiting for element to be clickable: {}", element);

        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /* =========================
       CLICK METHOD (CI safe)
       ========================= */

    public static void click(WebElement element, int seconds) {

        WebElement clickableElement = waitForClickable(element, seconds);

        try {

            log.info("Clicking element: {} in {} seconds", element, seconds);
            clickableElement.click();

        } catch (ElementClickInterceptedException e) {

            log.warn("Click intercepted. Retrying with JavaScript click: {}", element);

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].click();", clickableElement);
        }
    }

    public static void click(WebElement element) {
        click(element, DEFAULT_TIMEOUT);
    }


    /* =========================
       TYPE METHODS
       ========================= */

    public static void type(WebElement element, String value, int seconds) {
        waitForVisible(element, seconds);

        log.info("Typing value '{}' into element: {} in {} seconds", value, element, seconds);

        element.clear();
        element.sendKeys(value);
    }

    public static void type(WebElement element, String value) {
        type(element, value, DEFAULT_TIMEOUT);
    }


    /* =========================
       CORE WAIT (BY-BASED)
       ========================= */

    public static WebElement waitForVisible(By locator, int seconds) {

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(seconds)
        );

        log.info("Waiting for visibility of locator: {}", locator);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static WebElement waitForClickable(By locator, int seconds) {

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(seconds)
        );

        log.info("Waiting for locator to be clickable: {}", locator);

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForVisible(By locator) {
        return waitForVisible(locator, DEFAULT_TIMEOUT);
    }


    /* =========================
       CLICK METHOD
       ========================= */

    public static void click(By locator, int seconds) {

        WebElement element = waitForClickable(locator, seconds);

        try {

            log.info("Clicking locator: {} in {} seconds", locator, seconds);
            element.click();

        } catch (ElementClickInterceptedException e) {

            log.warn("Locator click intercepted. Retrying with JavaScript click: {}", locator);

            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static void click(By locator) {
        click(locator, DEFAULT_TIMEOUT);
    }


    /* =========================
       TYPE BY LOCATOR
       ========================= */

    public static void type(By locator, String value, int seconds) {

        WebElement element = waitForVisible(locator, seconds);

        log.info("Typing value '{}' into locator: {} in {} seconds", value, locator, seconds);

        element.clear();
        element.sendKeys(value);
    }

    public static void type(By locator, String value) {
        type(locator, value, DEFAULT_TIMEOUT);
    }


    /* =========================
       HARD SLEEP (LAST RESORT)
       ========================= */

    public static void hardSleep(int seconds) {

        try {

            Thread.sleep(seconds * 1000L);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            log.warn("Thread interrupted during hard sleep");
        }
    }
}