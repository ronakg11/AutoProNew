package google.com;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage {
	public WebDriver driver;
	
	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".gLFyf.gsfi")
	private WebElement searchField;
	
	public GoogleSearchResultsPage googleSearch(String community) {
		community = Keys.chord(community, Keys.ENTER);
		searchField.sendKeys(community);
		return new GoogleSearchResultsPage(driver);
	}
}
