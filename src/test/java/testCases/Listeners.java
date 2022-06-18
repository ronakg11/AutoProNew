package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.TestBase;

public class Listeners extends TestBase implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getExtentReportsObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> threadTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		threadTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadTest.get().log(Status.PASS, "The test has passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadTest.get().fail(result.getThrowable());

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String testMethodName = result.getMethod().getMethodName();

		try {
			threadTest.get().addScreenCaptureFromPath(takeScreenshot(driver, testMethodName), testMethodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
