package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminJobTitlesPage {
	WebDriver driver;
	WebDriverWait wait;
	public AdminJobTitlesPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
	}
		
	By adminTap=By.xpath("//span[text()='Admin']");
	By jobTab = By.xpath("//span[contains(text(),'Job')]");
	By jobTitle = By.xpath("//a[@class='oxd-topbar-body-nav-tab-link' and text()='Job Titles']");
	
	public void jobTabThenTitleClick() {
		wait.until(ExpectedConditions.elementToBeClickable(adminTap)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(jobTab)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(jobTitle).click();

	}
	
	

}
