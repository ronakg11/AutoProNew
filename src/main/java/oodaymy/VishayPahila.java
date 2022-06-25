package oodaymy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VishayPahila {
	public WebDriver driver;
	
	public VishayPahila(WebDriver driver) {
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
		return section.size();
	}
}
