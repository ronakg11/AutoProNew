package commentsProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import java.time.Duration;

public class Bibtya{
   public static void main(String[] args)
   throws InterruptedException{
	   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
      //object of ChromeOptions class
      ChromeOptions o = new ChromeOptions();
      //setting debuggerAddress value
      o.setExperimentalOption("debuggerAddress", "localhost:53754");
      //add options to browser capabilities
      WebDriver driver = new ChromeDriver(o);
      driver.get("http://facebook.com");
      
//      m.forEach((key, value) −> {
//         System.out.println("Key is: " + key + " Value is: " + value);
//      });
      //set implicit wait
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
      //identify element
      WebElement l = driver.findElement(By.id("gsc−i−id1"));
      //remove existing data in edit box
      l.clear();
      l.sendKeys("Tutorialspoint");
      String s = l.getAttribute("value");
      System.out.println("Attribute value: " + s);
   }
}