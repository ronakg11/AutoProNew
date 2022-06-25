package testCases;

import org.testng.annotations.Test;

import pages.ForgotPasswordPage;
import pages.LandingPage;
import pages.LoginPage;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import resources.TestBase;

public class HomePageAndLoginTest extends TestBase {
	private static Logger log = LogManager.getLogger(HomePageAndLoginTest.class.getName());
	public WebDriver driver;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
	}

	@Test(dataProvider = "sendCredentials")
<<<<<<< HEAD
	public void Login(String usrNm, String pwd, String usrType) throws IOException, InterruptedException {
=======
	public void LoginTest(String usrNm, String pwd, String usrType) throws IOException, InterruptedException {
>>>>>>> develop
		driver.get(prop.getProperty("qaUrl"));
		log.info("URL opened");

		LandingPage lap = new LandingPage(driver);
		log.info("LandingPage - Object created");
		
		LoginPage lop = lap.getLogin();
		log.info("LandingPage - Clicked login & LoginPage - object created");
		lop.emailField().sendKeys(usrNm);
		log.info("LoginPage - email id entered");
		lop.pwdField().sendKeys(pwd);
		log.info("LoginPage - password entered");
		lop.loginBtn().click();
		log.info("LoginPage - tried logging in for user type - " + usrType);
		
		driver.navigate().back();
		log.info("Navigated back to the LoginPage");
		
		ForgotPasswordPage fgtPwd = lop.forgotPwd();
		log.info("LoginPage - clicked 'forgot password'");
		fgtPwd.emailField().sendKeys("trent.boult@blackcaps.com");
		log.info("ForgotPasswordPage - entered email");
		fgtPwd.sendMeInstructions().click();
		log.info("ForgotPasswordPage - clicked 'send me instructions'");
	}

	@DataProvider
	public Object[][] sendCredentials() {
		Object[][] credentials = new Object[2][3];

		credentials[0][0] = "ronakg11@lapandaav.com";
		credentials[0][1] = "JaiGanesh123";
		credentials[0][2] = "Unrestricted User";

		credentials[1][0] = "pangolin11@chaayna.com";
		credentials[1][1] = "JaiVeerHanumaan123";
		credentials[1][2] = "Restricted User";

		return credentials;
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
		log.info("Driver closed");
	}
}
