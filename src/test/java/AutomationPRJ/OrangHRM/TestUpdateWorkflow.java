package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUpdateWorkflow {
	WebElement dashboard;
	WebDriver driver;
	AddEmployee addEmployee;
	OrangHRM oranghrm;
	Login loginpage ;
	
	
	@BeforeTest

	public void setup() {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		addEmployee = new AddEmployee(driver);
		oranghrm = new OrangHRM(driver);
		loginpage = new Login(driver);
		driver.manage().window().maximize(); 

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 
 
	}
	@BeforeMethod

	public void implicitWait() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
 
	}
	@Test (priority = 1)
	public void testUpdateContactDetailsValidData() {
		loginpage.loginMethod("ADMIN" ,"admin123" );		
		oranghrm.goToMyInfo();
		implicitWait();
		oranghrm.updateContactDetails("test@example.com", "1234567890", "0987654321");
		implicitWait();
		oranghrm.saveContactDetails();
		Assert.assertEquals(oranghrm.getWorkEmail(), "test@example.com");
		Assert.assertEquals(oranghrm.getHomeTelephone(), "1234567890");
		Assert.assertEquals(oranghrm.getMobile(), "0987654321");
	}

	@Test (priority = 2)
	public void testUpdateContactDetailsInvalidEmail() {
		loginpage.loginMethod("ADMIN" ,"admin123" );
		oranghrm.goToMyInfo();
		implicitWait();
		oranghrm.updateContactDetails("invalid-email", "", "");
		implicitWait();
		oranghrm.saveContactDetails();
		WebElement errorMessage = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		Assert.assertTrue(errorMessage.isDisplayed());
	}

	@Test (priority = 3)
	public void testUpdateContactDetailsPhoneNumberWithLetters() {
		loginpage.loginMethod("ADMIN" ,"admin123" );
		oranghrm.goToMyInfo();
		oranghrm.updateContactDetails("", "abcdefgh", "");
		implicitWait();
		oranghrm.saveContactDetails();
		WebElement errorMessage = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		Assert.assertTrue(errorMessage.isDisplayed());
	}

	@Test (priority = 4)
	public void testUpdateContactDetailsShortPhoneNumber() {
		loginpage.loginMethod("ADMIN" ,"admin123" );
		oranghrm.goToMyInfo();
		oranghrm.updateContactDetails("", "123", "");
		implicitWait();
		oranghrm.saveContactDetails();
		WebElement errorMessage = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		Assert.assertTrue(errorMessage.isDisplayed());
	}

	@Test (priority = 5)
	public void testUpdateMultipleContactFields() {
		loginpage.loginMethod("ADMIN" ,"admin123" );
		oranghrm.goToMyInfo();
		oranghrm.updateContactDetails("multiple@example.com", "1122334455", "5544332211");
		implicitWait();
		oranghrm.saveContactDetails();
		driver.navigate().refresh();
		oranghrm.goToMyInfo();
		Assert.assertEquals(oranghrm.getWorkEmail(), "multiple@example.com");
		Assert.assertEquals(oranghrm.getHomeTelephone(), "1122334455");
		Assert.assertEquals(oranghrm.getMobile(), "5544332211");
	}

	@Test (priority = 6)
	public void testCancelContactDetailsUpdate() {
		loginpage.loginMethod("ADMIN" ,"admin123" );
		oranghrm.goToMyInfo();
		implicitWait();
		String originalEmail = oranghrm.getWorkEmail();
		implicitWait();
		oranghrm.updateContactDetails("shouldnotbesaved@example.com", "", "");
		implicitWait();
		oranghrm.cancelContactDetails();
		driver.navigate().refresh();
		oranghrm.goToMyInfo();
	}
}
