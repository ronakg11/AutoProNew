package apartments.com;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {
	public WebDriver driver;
	public WebDriverWait wait;

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#searchBarLookup")
	private WebElement locationBar;

	@FindBy(css = ".btn.btn-default.rentselector")
	private WebElement priceBtn;

	@FindBy(css = "#min-input")
	private WebElement minPriceField;

	@FindBy(css = "#max-input")
	private WebElement maxPriceField;

	@FindBy(css = ".btn.btn-default.bedselector")
	private WebElement bedBtn;

	@FindAll(@FindBy(xpath = "//li[contains(@data-bind,'isMinBedsOptionActive')]"))
	private List<WebElement> bedSelectors;

	@FindBy(css = "#typeSelect")
	private WebElement typeBtn;

	@FindBy(xpath = "(//div[@id='type-selection-wrapper'] //fieldset[@class='checkbox'])[1]")
	private WebElement typeSelectorValue;

	@FindBy(css = ".btn.btn-default.move-in-selector")
	private WebElement dateBtn;

	@FindBy(css = ".switch")
	private WebElement monthYear;

	@FindBy(css = ".next")
	private WebElement nextMonth;

	@FindBy(xpath = "//div[@id='datepickerSearch']")
	private WebElement datePicker;
	private String daysXpath = "//td[@class='day ']";

	@FindBy(css = ".searchResults")
	private WebElement multipleResultsPages;

	@FindAll(@FindBy(xpath = "//nav[@id='paging']/ol/li/a"))
	private List<WebElement> resultPages;

	private List<WebElement> communityList;
	private String communityListCss = ".property-title";

	public void enterPriceRange(int minPrice, int maxPrice) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(priceBtn));
		priceBtn.click();
		wait.until(ExpectedConditions.visibilityOf(minPriceField));
		minPriceField.sendKeys(String.valueOf(minPrice));
		wait.until(ExpectedConditions.visibilityOf(maxPriceField));
		maxPriceField.sendKeys(String.valueOf(maxPrice));
		priceBtn.click();
	}

	public void enterBeds(int beds) throws InterruptedException {
		wait.until(ExpectedConditions.invisibilityOf(maxPriceField));
		bedBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(bedSelectors.get(beds)));
		bedSelectors.get(beds).click();
		wait.until(ExpectedConditions.elementToBeClickable(bedBtn));
		bedBtn.click();
	}

	public void selectHousingType() {
		wait.until(ExpectedConditions.elementToBeClickable(typeBtn));
		typeBtn.click();
		wait.until(ExpectedConditions.visibilityOf(typeSelectorValue));
		typeSelectorValue.click();
		typeBtn.click();
	}

	public void selectMoveInDate(String date) throws ParseException {
		wait.until(ExpectedConditions.elementToBeClickable(dateBtn));
		dateBtn.click();

		String[] dateParams = getDateParams(date);
		while (!monthYear.getText().equalsIgnoreCase(dateParams[1])) {
			nextMonth.click();
		}

		List<WebElement> daysList = datePicker.findElements(By.xpath(daysXpath));

		for (WebElement day : daysList) {
			if (day.getText().equalsIgnoreCase(dateParams[0])) {
				day.click();
				break;
			}
		}

		dateBtn.click();
	}

	public boolean areThereMultipleResultsPages() {
		try {
			return multipleResultsPages.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public HashSet<String> getResultsList(boolean multiplePages) throws InterruptedException {
		// Thread.sleep(3000);
		HashSet<String> communities = new HashSet<String>();
		String data = "";
		WebElement parent;

		if (multiplePages) {
			int size = resultPages.size();
			System.out.println("No. of pages: " + size);

			for (int j = 0; j < size; j++) {
				if (j > 0)
					resultPages.get(j).click();

				Thread.sleep(2000);
				communityList = driver.findElements(By.cssSelector(communityListCss));
				for (int i = 0; i < communityList.size(); i++) {
					parent = communityList.get(i).findElement(By.xpath("./.."));
					data = communityList.get(i).getAttribute("title").toUpperCase();
					if ((!data.contains("CONDO FOR RENT")) && (!data.contains("HOUSE FOR RENT"))
							&& (!data.contains("SENIOR")) && (!data.contains("62+"))) {
						data = data + "~~~" + parent.getAttribute("href");
						communities.add(data);
					}
				}
			}
		} else {
			System.out.println("No. of pages: 1");
			Thread.sleep(2000);
			communityList = driver.findElements(By.cssSelector(communityListCss));
			for (int i = 0; i < communityList.size(); i++) {
				parent = communityList.get(i).findElement(By.xpath("./.."));
				data = communityList.get(i).getAttribute("title").toUpperCase();
				if ((!data.contains("CONDO FOR RENT")) && (!data.contains("HOUSE FOR RENT"))
						&& (!data.contains("SENIOR")) && (!data.contains("62+"))) {
					data = data + "~~~" + parent.getAttribute("href");
					communities.add(data);
				}
			}
		}

		return communities;
	}

	private String[] getDateParams(String date) throws ParseException {
		String[] params = new String[2];
		LocalDate date1 = LocalDate.parse(date);
		params[0] = String.valueOf(date1.getDayOfMonth());
		params[1] = date1.getMonth() + " " + date1.getYear();
		return params;
	}
}
