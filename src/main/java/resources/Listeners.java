package resources;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends TestBase implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getExtentReportsObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> threadTest = new ThreadLocal<>();
	private static Logger log = LogManager.getLogger(Listeners.class.getName());

	@Override
	public void onTestStart(ITestResult result) {
		String testMethodName = result.getMethod().getMethodName();
		test = extent.createTest(testMethodName);
		threadTest.set(test);
		log.info("Test case " + testMethodName + " started execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threadTest.get().log(Status.PASS, "The test has passed");
		String testMethodName = result.getMethod().getMethodName();
		log.info("Test case " + testMethodName + " passed");
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
		log.info("Test case " + testMethodName + " failed");

		try {
			threadTest.get().addScreenCaptureFromPath(takeScreenshot(driver, testMethodName), testMethodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// following is the code for capturing the exact webelement screenshot
		// however, the object remains null everytime and the exact code
		// to capture the failed webelement needs to be written
	    Object object = result.getAttribute("CurrentElement");
	    if (object == null) {
	      return;
	    }

	    WebElement element = (WebElement) object;
	    log.info("The element that caused the failure is " + element.toString());
	    
		try {
			threadTest.get().addScreenCaptureFromPath(webElementScreenShot(element, testMethodName), testMethodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		threadTest.get().log(Status.SKIP, "The test was skipped from execution");
		String testMethodName = result.getMethod().getMethodName();
		log.info("Test case " + testMethodName + " skipped");
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
