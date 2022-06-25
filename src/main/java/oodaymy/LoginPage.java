package oodaymy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using="input[name='email']")
	private WebElement usr;

	@FindBy(how = How.CSS, using="input[name='password']")
	private WebElement pwd;
	
	@FindBy(how = How.CSS, using="input[name='submit']")
	private WebElement login;
	
	public WebElement getUserField() {
		return usr;
	}
	
	public WebElement getPwdField() {
		return pwd;
	}
	
	public VishayPage clickLoginBtn() {
		login.click();;
		return new VishayPage(driver);
	}
}
