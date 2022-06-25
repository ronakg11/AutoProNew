package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	public WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	private static Logger log = LogManager.getLogger(TestBase.class.getName());

	public WebDriver initializeDriver() throws IOException {
		prop = loadPropertiesFile();

		// With the below command, the browser is selected from the data.properties file
		// String browserName = prop.getProperty("browser");
		
		// With the System.getProperty("browser") command, the browser is retrieved from
		// the maven command "mvn test -Dbrowser=chrome"
		String browserName = System.getProperty("browser");
		String driverPath = System.getProperty("user.dir") + "/drivers/";
		browserName = prop.getProperty("browser");

		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
			driver = new ChromeDriver();
			break;
		case "chrome-background":
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
			ChromeOptions cOpt = new ChromeOptions();
			cOpt.addArguments("headless");
			driver = new ChromeDriver(cOpt);
			break;
		case "firefox":
			System.setProperty("webdriver.firefox.driver", driverPath + "geckodriver");
			driver = new FirefoxDriver();
			break;
		case "safari":
			System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
			driver = new SafariDriver();
			break;
		default:
			System.out.println("The browser you selected is not compatible with this framework");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return driver;
	}

	public String takeScreenshot(WebDriver driver, String testCaseName) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File screenShotFile = screenShot.getScreenshotAs(OutputType.FILE);

		String screenShotpath = System.getProperty("user.dir") + "/reports/screenshots/" + testCaseName
				+ getCurrentTimeStamp() + ".png";
		System.out.println(screenShotpath);
		FileUtils.copyFile(screenShotFile, new File(screenShotpath));
		return screenShotpath;
	}

	public static Properties loadPropertiesFile() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/data.properties");
		prop.load(fis);
		fis.close();
		return prop;
	}

	public static String getCurrentTimeStamp() {
		String pattern = "_yyyyMMdd_hhmmss_SSS";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		log.info(date);
		return date;
	}
}
