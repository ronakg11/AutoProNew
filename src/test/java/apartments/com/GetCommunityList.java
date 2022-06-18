package apartments.com;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import google.com.GoogleSearchResultsPage;
import resources.TestBase;

public class GetCommunityList extends TestBase {
	public WebDriver driver;
	HashSet<String> communitiesList = new HashSet<>();
	String[][] finalCommunityData = new String[][] {};

	String url = "https://www.apartments.com/";
	String location = "San Jose, CA";
	int minPrice = 1800;
	int maxPrice = 2600;
	int beds = 1;
	String moveInDate = "2021-08-01"; // yyyy-mm-dd
	double ratings = 3.8;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		url = url + location.toLowerCase().replace(" ", "-").replaceAll("[^a-z-]", "");
		if (beds > 0)
			url = url + "/min-" + beds + "-bedrooms";
		driver.get(url);
		driver.navigate().refresh();
	}

	@Test
	public void applyFiltersAndGetCommunities() throws ParseException, InterruptedException {
		ResultsPage results = new ResultsPage(driver);
		results.enterPriceRange(minPrice, maxPrice);
		// results.enterBeds(beds);
		results.selectHousingType();
		// results.selectMoveInDate(moveInDate);
		communitiesList = results.getResultsList(results.areThereMultipleResultsPages());
		System.out.println(communitiesList + "\n");
	}

	@Test(dependsOnMethods = { "applyFiltersAndGetCommunities" })
	public void filterOutCommunitiesBasedonGoogleReviews() {
		url = "https://www.google.com/search?q=a";
		driver.get(url);
		String[] communityArr = new String[] {};
		Iterator<String> it = communitiesList.iterator();
		int j = 0;

		GoogleSearchResultsPage results = new GoogleSearchResultsPage(driver);
		while (it.hasNext()) {
			communityArr = it.next().split("~~~");
			results.googleIt(communityArr[0]);
			Object[] actualRating = results.getCommunityGoogleRating(location);

			it.remove();
			if ((double) actualRating[0] >= ratings && (int) actualRating[1] > 0) {
				finalCommunityData = Arrays.copyOf(finalCommunityData, finalCommunityData.length + 1);
				communityArr = Arrays.copyOf(communityArr, communityArr.length + 3);
				communityArr[4] = communityArr[1];
				communityArr[1] = String.valueOf(actualRating[0]);
				communityArr[2] = String.valueOf(actualRating[1]);
				communityArr[3] = (String) actualRating[2];
				finalCommunityData[j] = communityArr.clone();
				j++;
			}
		}
	}

	@Test(dependsOnMethods = { "filterOutCommunitiesBasedonGoogleReviews" })
	public void processFinalResults() {
		for (int i = 0; i < finalCommunityData.length; i++) {
			System.out.println(finalCommunityData[i][0] + " - " + finalCommunityData[i][1] + " ("
					+ finalCommunityData[i][2] + ")");
			System.out.println(finalCommunityData[i][3]);
			System.out.println(finalCommunityData[i][4] + "\n");
		}
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
