package cart;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartTestObjects {
	public WebDriver driver;

	public CartTestObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".entry-content h3")
	private List<WebElement> mainTests;
	
	@FindBy(how = How.CSS, using = ".entry-content p strong")
	private List<WebElement> otherTests;

	public List<WebElement> getMainTests() {
		return mainTests;
	}

	public List<WebElement> getOtherTests() {
		return otherTests;
	}
}
