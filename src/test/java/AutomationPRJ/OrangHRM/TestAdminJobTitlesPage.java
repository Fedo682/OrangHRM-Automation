package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
	
  @Test (priority =1 )
  public void TC26_LoginAndOpenJobTitles() {
	  loginPage.loginMethod("Admin","admin123");
	  adminJobTitlesPage.jobTabThenTitleClick();
	  System.out.println("Login and open job title page");
  }
  @Test (priority =2 )
  public void TC27_addNewJob() {
	  String newJob = "CSE_" + System.currentTimeMillis();
	  adminJobTitlesPage.clickAddButton();
	  adminJobTitlesPage.addNewJob(newJob);
	  adminJobTitlesPage.clickSubmit();
	  String expectedResult="Job Titles";
	  Assert.assertEquals(adminJobTitlesPage.goToMainJobTitlePage(), expectedResult);
	  System.out.println("Job added successfully");
  }
  @Test (priority =3 )
  public void TC28_addExsistJob() {
	  String existedJobTitle =adminJobTitlesPage.existedJobTitle(); 
	  adminJobTitlesPage.clickAddButton();
	  adminJobTitlesPage.addNewJob(existedJobTitle);
	  adminJobTitlesPage.clickSubmit();
	  String expectedResult="Already exists";
	  Assert.assertEquals(adminJobTitlesPage.getAlreadyExistsMessage(), expectedResult);
	  System.out.println("Duplicate jobs");
  }
  @Test (priority =4 )
  public void TC29_testAddJobWithEmptyJobTitle() {
	  adminJobTitlesPage.clickCancel();
	  adminJobTitlesPage.clickAddButton();
	  adminJobTitlesPage.addNewJob("");
	  adminJobTitlesPage.clickSubmit();
	  String expectedResult="Required";
	  Assert.assertEquals(adminJobTitlesPage.getRequiredMessage(), expectedResult);
	  System.out.println("Jobs Title Required");
	  adminJobTitlesPage.clickCancel();

  }
  
  @Test (priority =5 )
  public void TC30_verifyAdminCanCancelAddingAJobTitle() {
	  
	  adminJobTitlesPage.clickAddButton();
	  adminJobTitlesPage.addNewJob("QA");
	  adminJobTitlesPage.clickCancel();
	  String expectedResult="Job Titles";
	  Assert.assertEquals(adminJobTitlesPage.goToMainJobTitlePage(), expectedResult);
	  System.out.println("Job canceled successfully");
  }
  
  @AfterTest
  public void teardown() {
      if (driver != null) {
          driver.quit();
      }
  }

  
}
