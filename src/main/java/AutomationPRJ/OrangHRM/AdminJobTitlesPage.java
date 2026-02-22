package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	By addButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	By jobField=By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	By submitButton=By.xpath("//button[@type='submit']");
	By jobTitleMainPage=By.xpath("//h6[text()='Job Titles']");

	public String goToMainJobTitlePage() {
	    WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));

		return 	longWait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleMainPage)).getText();
	}
	
	public void jobTabThenTitleClick() {
		
		wait.until(ExpectedConditions.elementToBeClickable(adminTap)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(jobTab)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(jobTitle).click();
	}
	public void addNewJob(String newJob) {
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(jobField));
		input.click();  // focus
		input.sendKeys(newJob);		
	}
	
	
	public void clickSubmit() {
		wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleMainPage));

	}

}
