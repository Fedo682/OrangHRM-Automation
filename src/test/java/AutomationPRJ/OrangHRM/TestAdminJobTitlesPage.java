package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAdminJobTitlesPage {
    WebDriver driver;
    Login loginPage;
    AdminJobTitlesPage adminJobTitlesPage;
    OrangHRM oranghrm;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new Login(driver);
        adminJobTitlesPage = new AdminJobTitlesPage(driver);
        oranghrm = new OrangHRM(driver);
    }

    @BeforeMethod
    public void loginAndOpenJobTitles() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.loginMethod("Admin", "admin123");
        adminJobTitlesPage.jobTabThenTitleClick();
    }

    @AfterMethod
    public void logoutAfterTest() {
        try {
            oranghrm.logout();
        } catch (Exception e) {
            System.out.println("Not logged in or already logged out.");
        }
    }

    @Test(priority = 1)
    public void TC26_LoginAndOpenJobTitles() {
        System.out.println("Login and open job title page");
    }

    @Test(priority = 2)
    public void TC27_addNewJob() {
        String newJob = "CSE_" + System.currentTimeMillis();
        adminJobTitlesPage.clickAddButton();
        adminJobTitlesPage.addNewJob(newJob);
        adminJobTitlesPage.clickSubmit();
        String expectedResult = "Job Titles";
        Assert.assertEquals(adminJobTitlesPage.goToMainJobTitlePage(), expectedResult);
        System.out.println("Job added successfully");
    }

    @Test(priority = 3)
    public void TC28_addExsistJob() {
        String existedJobTitle = adminJobTitlesPage.existedJobTitle();
        adminJobTitlesPage.clickAddButton();
        adminJobTitlesPage.addNewJob(existedJobTitle);
        adminJobTitlesPage.clickSubmit();
        String expectedResult = "Already exists";
        Assert.assertEquals(adminJobTitlesPage.getAlreadyExistsMessage(), expectedResult);
        System.out.println("Duplicate jobs");
    }

    @Test(priority = 4)
    public void TC29_testAddJobWithEmptyJobTitle() {
        adminJobTitlesPage.clickAddButton();
        adminJobTitlesPage.addNewJob("");
        adminJobTitlesPage.clickSubmit();
        String expectedResult = "Required";
        Assert.assertEquals(adminJobTitlesPage.getRequiredMessage(), expectedResult);
        System.out.println("Jobs Title Required");
    }

    @Test(priority = 5)
    public void TC30_verifyAdminCanCancelAddingAJobTitle() {
        adminJobTitlesPage.clickAddButton();
        adminJobTitlesPage.addNewJob("QA");
        adminJobTitlesPage.clickCancel();
        String expectedResult = "Job Titles";
        Assert.assertEquals(adminJobTitlesPage.goToMainJobTitlePage(), expectedResult);
        System.out.println("Job canceled successfully");
    }

    @Test(priority = 6)
    public void TC31_verifyTheEditIconNavigateToEditJobTitle() {
    	
        adminJobTitlesPage.clickEditIcon();
        String expectedResult = "Edit Job Title";
        Assert.assertEquals(adminJobTitlesPage.goToEditJobTitlePage(), expectedResult);
        System.out.println("Navigate to Edit Job Title successfully");
    }
 
    @Test(priority = 7)
    public void TC32_verifyToEditJobTitle() {
        adminJobTitlesPage.clickEditIcon();
        adminJobTitlesPage.editJobTitle("new");
        adminJobTitlesPage.clcikSaveEditButton();
        String expectedResult = "Edit Job Title";
        Assert.assertEquals(adminJobTitlesPage.goToEditJobTitlePage(), expectedResult);
        System.out.println("Edit Job Title successfully");
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}