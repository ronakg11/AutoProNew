package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='email']")
	private WebElement username;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;

	@FindBy(css = "input[type='submit']")
	private WebElement login;

	@FindBy(css = "a[href*='forgot_password']")
	private WebElement forgotPassword;

	public WebElement emailField() {
		return username;
	}

	public WebElement pwdField() {
		return password;
	}

	public WebElement loginBtn() {
		return login;
	}

	public ForgotPasswordPage forgotPwd() {
		forgotPassword.click();
		return new ForgotPasswordPage(driver);
	}
}
