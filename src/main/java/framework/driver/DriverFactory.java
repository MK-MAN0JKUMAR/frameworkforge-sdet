package framework.driver;

import framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.Properties;

public class DriverFactory {

    public static WebDriver createInstance(String browser) {
        try {
            if (ConfigReader.get("run.mode").equalsIgnoreCase("remote")) {
                return createRemoteDriver(browser);
            } else {
                return createLocalDriver(browser);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create driver", e);
        }
    }

    private static WebDriver createRemoteDriver(String browser) throws Exception {
        return switch (browser.toLowerCase()) {
            case "chrome" ->
                    new RemoteWebDriver(
                            new URL(ConfigReader.get("grid.url")),
                            new ChromeOptions()
                    );
            case "firefox" ->
                    new RemoteWebDriver(
                            new URL(ConfigReader.get("grid.url")),
                            new FirefoxOptions()
                    );
            case "edge" ->
                    new RemoteWebDriver(
                            new URL(ConfigReader.get("grid.url")),
                            new EdgeOptions()
                    );
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static WebDriver createLocalDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(new ChromeOptions());
            case "firefox" -> new FirefoxDriver(new FirefoxOptions());
            case "edge" -> new EdgeDriver(new EdgeOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
