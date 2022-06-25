package oodaymy;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VishayPage {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(VishayPage.class.getName());
	
	public VishayPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using=".lecture-bookmark-v2--section--383LP")
	private List<WebElement> section;
	
	@FindBy(how = How.CSS, using=".lecture-bookmark-v2--bookmark-header--2JV49 .udlite-text-sm")
	private List<WebElement> lectureNum;
	
	@FindBy(how = How.CSS, using=".lecture-bookmark-v2--duration--VkIdY span")
	private List<WebElement> time;
	
	@FindBy(how = How.CSS, using="div[data-purpose='safely-set-inner-html:rich-text-viewer:html']")
	private List<WebElement> comments;
	
	private By paragraph = By.cssSelector("p");
	
	public List<WebElement> getSection() {
		return section;
	}
	
	public List<WebElement> getLectureNum() {
		return lectureNum;
	}
	
	public List<WebElement> getTime() {
		return time;
	}
	
	public List<WebElement> getcomments() {
		return comments;
	}
	
	public int getCommentsCount() {
		log.info("Comments count found: " + section.size());
		return section.size();
	}
	
	public String getcommentText(int i) {
		int size = comments.get(i).findElements(paragraph).size();
		String comment = null;
		
		for(int j = 0; j < size; j++) {
			if(comment.isEmpty())
				comment = comments.get(i).findElements(paragraph).get(j).getText();
			else
				comment = comment + "\n" + comments.get(i).findElements(paragraph).get(j).getText();
		}
		return comment;
	}
}
