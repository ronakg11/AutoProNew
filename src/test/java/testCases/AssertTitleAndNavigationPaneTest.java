package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LandingPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

import resources.TestBase;

public class AssertTitleAndNavigationPaneTest extends TestBase {
	private static Logger log = LogManager.getLogger(AssertTitleAndNavigationPaneTest.class.getName());
	public WebDriver driver;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.get(prop.getProperty("qaUrl"));
		log.info("URL opened");
	}

	@Test
	public void basePageNavigation() throws IOException, InterruptedException {
		LandingPage lap = new LandingPage(driver);
		log.info("LandingPage - Object created");

		Assert.assertEquals(lap.bkgrndText().getText(), "XLearn Earn & Shine");
		log.info("assertEquals validated");
		Assert.assertTrue(lap.navigationPane().isDisplayed());
		log.info("assertTrue validated");
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
		log.info("Driver closed");
	}
}
