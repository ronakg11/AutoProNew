package commentsProject;

import org.testng.annotations.Test;
import oodaymy.VishayPage;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import resources.WriteExcel;

public class OodaymyNew {
	private static Logger log = LogManager.getLogger(OodaymyNew.class.getName());
	public WebDriver driver;

	@BeforeClass
	public void initialize() throws IOException {
		// Run the following command in terminal first
		// /Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome --remote-debugging-port=9222 --user-data-dir="/Users/ronakgavandi/Downloads/Nimma
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
		ChromeOptions o = new ChromeOptions();
		int port = 53754;
		o.setExperimentalOption("debuggerAddress", "localhost:" + port);
		driver = new ChromeDriver(o);
		log.info("Driver initialized with the existing browser on port: " + port);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		WriteExcel.createExcelWkbk();
		log.info("Test case requesting the workbook be created");
	}

	@Test
	public void extractCommentsC1() throws IOException, InterruptedException {
		driver.get("https://www.udemy.com/course/selenium-real-time-examplesinterview-questions/learn/lecture/2340124?start=0#notes");
		log.info("URL opened");
		
		VishayPage v1 = new VishayPage(driver);
		log.info("Course 1 - Object created");
		
		List<String> ls = new ArrayList<>();
		
		for(int i = 0; i < v1.getCommentsCount(); i++) {
			ls.add(String.valueOf(i + 1));
			ls.add(v1.getSection().get(i).getText());
			ls.add(v1.getLectureNum().get(i).getText());
			ls.add(v1.getTime().get(i).getText());
			ls.add(v1.getcomments().get(i).getText());
//			ls.add(v1.getcommentText(i));
			WriteExcel.writeInColumns(ls, 0);
			ls.removeAll(ls);
		}
	}
	
	@Test
	public void extractCommentsC2() throws IOException, InterruptedException {
		driver.get("https://www.udemy.com/course/rest-api-automation-testing-rest-assured/learn/lecture/23037894?start=0#notes");
		log.info("URL opened");
		
		VishayPage v1 = new VishayPage(driver);
		log.info("Course 2 - Object created");
		
		List<String> ls = new ArrayList<>();
		
		for(int i = 0; i < v1.getCommentsCount(); i++) {
			ls.add(String.valueOf(i + 1));
			ls.add(v1.getSection().get(i).getText());
			ls.add(v1.getLectureNum().get(i).getText());
			ls.add(v1.getTime().get(i).getText());
			ls.add(v1.getcomments().get(i).getText());
//			ls.add(v1.getcommentText(i));
			WriteExcel.writeInColumns(ls, 1);
			ls.removeAll(ls);
		}
	}

	@AfterClass
	public void tearDown() throws InterruptedException, IOException {
		Thread.sleep(2000);
//		driver.close();
		log.info("Driver closed");
		WriteExcel.closeWorkbook();
		log.info("Workbook closed");
	}
}
