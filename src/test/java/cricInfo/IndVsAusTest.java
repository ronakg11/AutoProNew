package cricInfo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cricInfoPages.SecondTest;
import resources.TestBase;

public class IndVsAusTest extends TestBase {
	public WebDriver driver;
	// private static Logger log = LogManager.getLogger(IndVsAusTest.class.getName());

	@BeforeMethod
	public void initialize() throws IOException {
		String url = "https://www.espncricinfo.com/series/india-tour-of-australia-2003-04-62294/australia-vs-india-2nd-test-64060/full-scorecard";
		driver = initializeDriver();
		driver.get(url);
	}
	
	@Test
	public void assertPlayerScore() {
		SecondTest st = new SecondTest(driver);
		//Assert.assertEquals(st.getOutDetails("AUSTRALIA 2ND INNINGS", "ricky ponting"), "c Chopra b Agarkar");
		System.out.println(st.getBatsmanInningDetails("AUSTRALIA 2nd INNINGS", "ricky ponting"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
