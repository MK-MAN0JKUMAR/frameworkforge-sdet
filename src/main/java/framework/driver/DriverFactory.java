package framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createInstance(String browser) {

        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(new ChromeOptions());
            case "edge" -> new EdgeDriver(new EdgeOptions());
            case "firefox" -> new FirefoxDriver(new FirefoxOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
