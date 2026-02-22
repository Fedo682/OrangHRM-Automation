package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAdminJobTitlesPage {
	WebDriver driver;
	Login loginPage;
	AdminJobTitlesPage  adminJobTitlesPage;

    @BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		loginPage = new Login(driver);
		adminJobTitlesPage = new AdminJobTitlesPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));	
	}
	
  @Test 
  public void TC26_LoginAndOpenJobTitles() {
	  loginPage.loginMethod("Admin","admin123");
	  adminJobTitlesPage.jobTabThenTitleClick();
	  System.out.println("Login and open job title page");
  }
  

  @AfterTest
  public void teardown() {
      if (driver != null) {
          driver.quit();
      }
  }

  
}
