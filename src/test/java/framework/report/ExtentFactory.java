package framework.report;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentFactory {

    private static ExtentReports extent;

    private ExtentFactory() {}

    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportPath = Paths.get(
                    "test-results", "reports", "ExtentReport_" + timestamp + ".html"
            ).toString();

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("FrameworkForge Automation");
            spark.config().setDocumentTitle("Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // system info
            extent.setSystemInfo("Application", "Rahul Shetty Academy");
            extent.setSystemInfo("Module", "Admin");
            extent.setSystemInfo("Sub Module", "Customer");
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }

        return extent;
    }
}
