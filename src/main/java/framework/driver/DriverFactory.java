package framework.driver;

import framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

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

    private static boolean isCI() {
        return System.getenv("CI") != null;
    }

    private static ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        if (isCI()) {

            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

        }

        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {

        FirefoxOptions options = new FirefoxOptions();

        if (isCI()) {
            options.addArguments("-headless");
        }

        return options;
    }

    private static EdgeOptions getEdgeOptions() {

        EdgeOptions options = new EdgeOptions();

        if (isCI()) {
            options.addArguments("--headless=new");
        }

        return options;
    }


    private static WebDriver createLocalDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(getChromeOptions());
            case "firefox" -> new FirefoxDriver(getFirefoxOptions());
            case "edge" -> new EdgeDriver(getEdgeOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }


    private static WebDriver createRemoteDriver(String browser) throws Exception {
        String gridUrl = ConfigReader.get("grid.url");
        return switch (browser.toLowerCase()) {
            case "chrome" -> new RemoteWebDriver(
                    new URL(gridUrl),
                    getChromeOptions()
            );
            case "firefox" -> new RemoteWebDriver(
                    new URL(gridUrl),
                    getFirefoxOptions()
            );
            case "edge" -> new RemoteWebDriver(
                    new URL(gridUrl),
                    getEdgeOptions()
            );
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}
