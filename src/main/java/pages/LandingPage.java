package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[href*='sign_in']")
	private WebElement signin;

	@FindBy(xpath = "//strong[contains(text(),'Shine')]")
	private WebElement redBkgrndText;

	@FindBy(css = ".navigation.clearfix")
	private WebElement navPane;
	
	By example = By.cssSelector(".class.name");

	public LoginPage getLogin() {
		signin.click();
		return new LoginPage(driver);
	}

	public WebElement bkgrndText() {
		return redBkgrndText;
	}

	public WebElement navigationPane() {
		return navPane;
	}
}
