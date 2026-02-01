package framework.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framework.report.ExtentFactory;
import framework.utils.ScreenshotUtil;

public class ExtentReportListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentFactory.getInstance();


    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        String screenshot = ScreenshotUtil.capture(result.getMethod().getMethodName());
        if (screenshot != null) {
            if (screenshot.startsWith("data:image") || screenshot.length() > 1000) {
                test.get().addScreenCaptureFromBase64String(screenshot);
            } else {
                test.get().addScreenCaptureFromPath(screenshot);
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public boolean isEnabled() {
        return Boolean.parseBoolean(
                System.getProperty("enable.report", "true")
        );
    }
}
