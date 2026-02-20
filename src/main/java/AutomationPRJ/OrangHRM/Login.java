package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	WebDriver driver;
	WebDriverWait wait ;
	
	public Login(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	By username = By.xpath("//input[@name='username']");
	By password = By.name("password");
	By loginButton = By.xpath("//button[@type='submit']");
	By invalidLoginMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
	By dashboardHeader = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb')]");
	By validationMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");

	
	public void loginMethod(String uName, String pass) {
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(uName);
		wait.until(ExpectedConditions.attributeToBe(username, "value", uName));
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pass);
		wait.until(ExpectedConditions.attributeToBe(password, "value", pass));
		 driver.findElement(loginButton).click();
		
	}
	public String getInvalidLoginMessage() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidLoginMessage)).getText();
	}
	public String getDashboardHeader() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
	}
	public String getValidationMessage() {
		return driver.findElement(validationMessage).getText();
	}
	
}
