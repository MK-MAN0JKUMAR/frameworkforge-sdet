package framework.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import framework.driver.DriverManager;

public final class ScreenshotUtil {

    private static final Logger log = LogManager.getLogger(ScreenshotUtil.class);

    private ScreenshotUtil() {}

    public static String capture(String testName) {

        String mode = System.getProperty("screenshot.mode", "file");
        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            log.warn("Driver is null, screenshot skipped");
            return null;
        }

        try {
            if ("base64".equalsIgnoreCase(mode)) {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            }

            // file mode (local)
            String safeName = testName.replaceAll("[^a-zA-Z0-9-_]", "_");
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

            Path dest = Paths.get(
                    "test-results", "screenshots", safeName + "_" + timestamp + ".png"
            );

            Files.createDirectories(dest.getParent());

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), dest);

            log.info("Screenshot captured: {}", dest);

            // IMPORTANT: relative path for Extent
            return "../screenshots/" + dest.getFileName().toString();

        } catch (Exception e) {
            log.error("Screenshot capture failed", e);
            return null;
        }
    }
}
