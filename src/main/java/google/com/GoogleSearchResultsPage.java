package google.com;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchResultsPage {
	public WebDriver driver;

	public GoogleSearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@class='gLFyf gsfi']")
	private WebElement searchField;

	@FindBy(css = ".Fam1ne.EBe2gf")
	private WebElement googleReviews;

	@FindBy(css = ".hqzQac span a span")
	private WebElement googleReviewsCount;

	@FindBy(xpath = "(//div[@class='QqG1Sd']/a)[1]")
	private WebElement website;

	public void googleIt(String community) {
		community = Keys.chord(community, Keys.ENTER);
		searchField.clear();
		searchField.sendKeys(community);
	}

	public Object[] getCommunityGoogleRating(String location) {
		String ratings = "-1";
		String count = "0";
		String communityWebsite = "Website not available";

		try {
			googleReviews.isDisplayed();
		} catch (Exception e) {
			String stringAndEnter = searchField.getAttribute("value").toUpperCase()
					.replace(", " + location.toUpperCase(), "") + " APARTMENTS, " + location.toUpperCase();
			stringAndEnter = Keys.chord(stringAndEnter, Keys.ENTER);
			searchField.clear();
			searchField.sendKeys(stringAndEnter);
		}

		try {
			ratings = googleReviews.getAttribute("aria-label");
			ratings = ratings.replace("Rated ", "").replace(" out of 5,", "");
		} catch (Exception e) {
			ratings = "-1";
		}

		try {
			if (ratings.equalsIgnoreCase("-1"))
				count = "0";
			else {
				count = googleReviewsCount.getText().trim().replace(" Google reviews", "").replace(" Google review",
						"");
				try {
					communityWebsite = website.getAttribute("href");
				} catch (Exception e) {
					communityWebsite = "Website not available";
				}
			}
		} catch (Exception e) {
			count = "0";
		}

		Object[] returnObject = new Object[3];
		returnObject[0] = Double.parseDouble(ratings);
		returnObject[1] = Integer.parseInt(count.replaceAll("[^0-9]", ""));
		returnObject[2] = communityWebsite;
		return returnObject;
	}
}
