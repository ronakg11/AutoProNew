package cricInfoPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SecondTest {
	public WebDriver driver;

	public SecondTest(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private By scorecard = By.xpath("//table[@class='table batsman']");
	private By scTitle = By.xpath("//h5[@class='header-title label']");
	private By batsman = By.xpath("//td[contains(@class, 'batsman-cell text-truncate')]");
	private By out = By.xpath("./../td[2]");
	private By run = By.xpath("./../td[3]");
	private By ball = By.xpath("./../td[4]");
	private By min = By.xpath("./../td[5]");
	private By four = By.xpath("./../td[6]");
	private By six = By.xpath("./../td[7]");
	private By strikeRate = By.xpath("./../td[8]");

	private int getScorecard(String title) {
		List<WebElement> scTitles = driver.findElements(scTitle);

		for (int i = 0; i < scTitles.size(); i++) {
			if (scTitles.get(i).getText().trim().equalsIgnoreCase(title)) {
				return i + 1;
			}
		}
		return -1;
	}

	public String getBatsmanInningDetails(String title, String playerName) {
		int i = getScorecard(title);
		List<WebElement> batsmen = driver.findElements(By.xpath(getXpath(scorecard, i) + " " + getXpath(batsman, -1)));

		for (i = 0; i < batsmen.size(); i++) {
			if (batsmen.get(i).getText().trim().equalsIgnoreCase(playerName)) {
				String rtn = (playerName + " | "
						+ batsmen.get(i).findElement(out).getText() + " | "
						+ batsmen.get(i).findElement(run).getText() + " | "
						+ batsmen.get(i).findElement(ball).getText() + " | "
						+ batsmen.get(i).findElement(min).getText() + " | "
						+ batsmen.get(i).findElement(four).getText() + " | "
						+ batsmen.get(i).findElement(six).getText() + " | "
						+ batsmen.get(i).findElement(strikeRate).getText()).toUpperCase();
				return rtn;
			}
		}

		return null;
	}
	
	public String getXpath(By ele, int i) {
	    String str = ele.toString();
	    String[] listString = null;
	    if(str.contains("xpath"))
	      listString = str.split("By.xpath: ");
	    String xpath = listString[1].trim();
	    if(i > -1)
	    	xpath = "(" + xpath + ")[" + i + "]";
	    return xpath;
	}
}
