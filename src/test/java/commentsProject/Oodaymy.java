package commentsProject;

import org.testng.annotations.Test;
import oodaymy.LoginPage;
import oodaymy.VishayPage;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import resources.TestBase;
import resources.WriteExcel;

public class Oodaymy extends TestBase {
	private static Logger log = LogManager.getLogger(Oodaymy.class.getName());
	public WebDriver driver;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
	}

	@Test
	public void extractComments() throws IOException, InterruptedException {
		driver.get(prop.getProperty("link1"));
		log.info("URL opened");
		
		LoginPage lop = new LoginPage(driver);
		lop.getUserField().sendKeys("ronakg11@gmail.com");
		lop.getPwdField().sendKeys("Swamiom_11@c");
		VishayPage v1 = lop.clickLoginBtn();
		wait.until(ExpectedConditions.urlToBe(prop.getProperty("link1")));
		log.info("Courses - Object created");
		
		WriteExcel.createExcelWkbk();
		
		for(int i = 0; i < v1.getCommentsCount(); i++) {
//			WriteExcel.writeInColumns(pathToFile, v1.getTime().get(i).getText(), 0);
		}
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
		log.info("Driver closed");
	}
}
