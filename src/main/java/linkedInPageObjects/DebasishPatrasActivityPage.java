package linkedInPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DebasishPatrasActivityPage {
	public WebDriver driver;

	public DebasishPatrasActivityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".feed-shared-document__container.feed-shared-document__container--top-bottom-border")
	private WebElement restAssuredDoc;
	
	@FindBy(xpath = "//iframe[contains(@class, 'document-s-container')]")
	private WebElement docFrame;

	@FindBy(xpath = "//div[@class='ssplayer-carousel-panel left']/button/li-icon/span")
	private WebElement leftBtn;
	
	@FindBy(xpath = "//div[@class='ssplayer-carousel-panel right']/button/li-icon/span")
	private WebElement rightBtn;

	public WebElement getAttachment() {
		return restAssuredDoc;
	}
	
	public WebElement getFrame() {
		return docFrame;
	}
}
