package commentsProject;

import org.testng.annotations.Test;

import oodaymy.VishayPahila;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
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
	public void basePageNavigation(String usrNm, String pwd, String usrType) throws IOException, InterruptedException {
		driver.get(prop.getProperty("link1"));
		log.info("URL opened");

		VishayPahila v1 = new VishayPahila(driver);
		log.info("Courses - Object created");
		
		WriteExcel.createExcelWkbk();
		
		for(int i = 0; i < v1.getCommentsCount(); i++) {
			
		}
		
		
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
		log.info("Driver closed");
	}
}
