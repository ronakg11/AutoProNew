package commentsProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.Capabilities;
import java.util.Map;

public class ConnectExistingSession{
   public static void main(String[] args)
   throws InterruptedException{
	   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
	   WebDriver driver = new ChromeDriver();
      //get browser capabilities in key value pairs
	// getCapabilities will return all browser capabilities
	   Capabilities cap=((ChromiumDriver) driver).getCapabilities();

	   // asMap method will return all capability in MAP
	   Map<String, Object> myCap=cap.asMap();

	   // print the map data-
	   System.out.println(myCap);
   }
}
