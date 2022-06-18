package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
	public WebDriver driver;

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".form-control.string.email.optional")
	private WebElement email;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement send;

	public WebElement emailField() {
		return email;
	}
	
	public WebElement sendMeInstructions() {
		return send;
	}
}
