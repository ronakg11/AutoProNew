package apartments.com;

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
	
	@FindBy(css = "#quickSearchLookup")
	private WebElement searchField;
	
	@FindBy(css = ".go")
	private WebElement searchBtn;
	
	public ResultsPage searchLocation(String location) {
		searchField.sendKeys(location);
		searchBtn.click();
		return new ResultsPage(driver);
	}
}
