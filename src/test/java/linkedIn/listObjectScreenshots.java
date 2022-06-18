package linkedIn;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LandingPage;
import resources.TestBase;

public class listObjectScreenshots extends TestBase {
	public WebDriver driver;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get("https://www.linkedin.com/in/debasish1166/detail/recent-activity/");
	}

	@Test
	public void basePageNavigation() throws IOException, InterruptedException {
		LandingPage lap = new LandingPage(driver);

		Assert.assertEquals(lap.bkgrndText().getText(), "XLearn Earn & Shine");
		Assert.assertTrue(lap.navigationPane().isDisplayed());
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		driver.close();
	}
}
