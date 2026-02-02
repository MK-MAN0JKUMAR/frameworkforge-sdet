package framework.base;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import framework.config.ConfigReader;
import framework.utils.TestDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import framework.driver.DriverFactory;
import framework.driver.DriverManager;

public class BaseTestClass {

    protected Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser) {
        logger = LogManager.getLogger(this.getClass());

        WebDriver driver = DriverFactory.createInstance(browser);
        DriverManager.setDriver(driver);

        logger.info("Browser launched: {}", browser);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        String baseUrl = ConfigReader.get("base.url");
        driver.get(baseUrl);
        logger.info("Navigated to: {}", baseUrl);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
            DriverManager.unload();
            logger.info("Browser closed");
        }
    }

    private Properties loadConfig() {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            Properties p = new Properties();
            p.load(is);
            return p;

        } catch (Exception e) {
            throw new RuntimeException("Unable to load config", e);
        }
    }
}
