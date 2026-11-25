
package listeners;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import reports.ExtentManager;
import reports.ExtentTestManager;

public class ExtentTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        Object[] parameters = result.getParameters();

        if (parameters.length > 0) {
            testName += " - " + parameters[0];
        }

        ExtentTest test = ExtentManager.getInstance()
                .createTest(testName);

        ExtentTestManager.setTest(test);

        test.info("Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {

            test.pass("Test passed successfully");

            long duration = result.getEndMillis()
                    - result.getStartMillis();

            test.info("Execution duration: " + duration + " ms");
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("===== onTestFailure EXECUTED =====");

        ExtentTest test = ExtentTestManager.getTest();

        try {

            BaseTest baseTest = (BaseTest) result.getInstance();

            WebDriver driver = baseTest.getDriver();

            System.out.println("Driver: " + driver);

            if (driver != null) {

                // Create screenshots folder
                Path folder = Paths.get(
                        System.getProperty("user.dir"),
                        "screenshots"
                );

                Files.createDirectories(folder);

                // Generate unique screenshot filename
                String fileName = result.getMethod()
                        .getMethodName()
                        + "_" + System.currentTimeMillis()
                        + ".png";

                Path screenshotPath = folder.resolve(fileName);

                // Capture screenshot as bytes
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);

                // Save screenshot
                Files.write(screenshotPath, screenshot);

                System.out.println("SCREENSHOT CREATED: "
                        + screenshotPath.toAbsolutePath());

                System.out.println("File size: "
                        + screenshot.length + " bytes");

                // Attach screenshot to Extent Report
                if (test != null) {

                    test.fail(result.getThrowable());

                    test.addScreenCaptureFromPath(
                            screenshotPath.toAbsolutePath().toString()
                    );
                }

            } else {

                System.out.println("ERROR: WebDriver is NULL");

                if (test != null) {
                    test.fail(result.getThrowable());
                }
            }

        } catch (Exception e) {

            System.out.println("SCREENSHOT ERROR:");

            e.printStackTrace();

            if (test != null) {
                test.fail(result.getThrowable());
                test.warning("Screenshot capture failed: "
                        + e.getMessage());
            }
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {

            if (result.getThrowable() != null) {
                test.skip(result.getThrowable());
            } else {
                test.skip("Test skipped");
            }
        }

        ExtentTestManager.removeTest();
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentManager.getInstance().flush();

    }
}
