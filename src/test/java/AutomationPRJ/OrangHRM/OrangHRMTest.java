package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangHRMTest {
	WebElement dashboard;
	WebDriver driver;
	OrangHRM oranghrm;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		oranghrm = new OrangHRM(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));	
	}
	
	
	
	
	
	
	@Test (priority = 18)
	public void testUpdateContactDetailsValidData() {
		oranghrm.login("Admin", "admin123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.goToMyInfo();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.updateContactDetails("test@example.com", "1234567890", "0987654321");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.saveContactDetails();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		Assert.assertEquals(oranghrm.getWorkEmail(), "test@example.com");
		Assert.assertEquals(oranghrm.getHomeTelephone(), "1234567890");
		Assert.assertEquals(oranghrm.getMobile(), "0987654321");
	}

	@Test (priority = 19)
	public void testUpdateContactDetailsInvalidEmail() {
		oranghrm.login("Admin", "admin123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.goToMyInfo();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.updateContactDetails("invalid-email", "", "");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.saveContactDetails();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement errorMessage = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		Assert.assertTrue(errorMessage.isDisplayed());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
	}

	@Test (priority = 20)
	public void testUpdateContactDetailsPhoneNumberWithLetters() {
		oranghrm.login("Admin", "admin123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.goToMyInfo();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.updateContactDetails("", "abcdefgh", "");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.saveContactDetails();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement errorMessage = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		Assert.assertTrue(errorMessage.isDisplayed());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
	}

	@Test (priority = 21)
	public void testUpdateContactDetailsShortPhoneNumber() {
		oranghrm.login("Admin", "admin123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.goToMyInfo();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.updateContactDetails("", "123", "");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.saveContactDetails();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement errorMessage = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		Assert.assertTrue(errorMessage.isDisplayed());
	}

	@Test (priority = 22)
	public void testUpdateMultipleContactFields() {
		oranghrm.login("Admin", "admin123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.goToMyInfo();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.updateContactDetails("multiple@example.com", "1122334455", "5544332211");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.saveContactDetails();
		driver.navigate().refresh();
		oranghrm.goToMyInfo();
		Assert.assertEquals(oranghrm.getWorkEmail(), "multiple@example.com");
		Assert.assertEquals(oranghrm.getHomeTelephone(), "1122334455");
		Assert.assertEquals(oranghrm.getMobile(), "5544332211");
	}

	@Test (priority = 15)
	public void testCancelContactDetailsUpdate() {
		oranghrm.login("Admin", "admin123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.goToMyInfo();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		String originalEmail = oranghrm.getWorkEmail();
		oranghrm.updateContactDetails("shouldnotbesaved@example.com", "", "");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.cancelContactDetails();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		oranghrm.goToMyInfo();
		Assert.assertEquals(oranghrm.getWorkEmail(), originalEmail);
	}
	
	@AfterMethod
    public void logoutsession() {
    	try {
    		WebElement dashboard = driver.findElement(By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb')]"));
    		if(dashboard.isDisplayed()) {
    			oranghrm.logout();
    		}
    	}
    	catch(Exception e) {
    		System.out.println("Not logged in, no need to logout.");
    	}
    }
    
	@AfterTest
    public void teardown() {
        driver.quit();
    }
}
