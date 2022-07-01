package Other;

import org.testng.annotations.Test;
import cart.CartTestObjects;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import resources.TestBase;

public class CartTestCases extends TestBase {
	public WebDriver driver;

	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get("https://khyatisehgal.wordpress.com/2017/02/16/functional-test-cases-of-shopping-cart/");
	}

	@Test
	public void basePageNavigation() throws IOException, InterruptedException {
		CartTestObjects cart = new CartTestObjects(driver);
		int tcCount = cart.getMainTests().size();
		
		for(int i = 0; i < tcCount; i++) {
			System.out.println(cart.getMainTests().get(i).getText());
		}
		
		tcCount = cart.getOtherTests().size();
		
		for(int i = 0; i < tcCount; i++) {
			System.out.println(cart.getOtherTests().get(i).getText());
		}
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}
}
