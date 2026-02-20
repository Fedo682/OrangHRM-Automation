package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLoginWorkflow {
	WebElement dashboard;
	WebDriver driver;
	OrangHRM oranghrm;
	Login loginPage;
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		oranghrm = new OrangHRM(driver);
		loginPage = new Login(driver);
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


	}
	@BeforeMethod
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	

	}
	
	@Test (priority = 1)
	public void testLoginValidCredintials() {
		loginPage.loginMethod("Admin","admin123");	
		System.out.println(" time to wait for dashboard to load");
		Assert.assertEquals(loginPage.getDashboardHeader(), "Dashboard","Login failed - Dashboard not displayed");
		System.out.println("Login successful - Dashboard is displayed.");
		}
	

	@Test (priority = 2)
	public void testLoginInValidCredintials() {
	loginPage.loginMethod("addminn","not exist");
	    Assert.assertEquals(loginPage.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
	    System.out.println("Invalid login test passed - Error message displayed.");
	}
	
	@Test (priority = 3)
    public void testLoginEmpty ()  {
    loginPage.loginMethod("" ,"");
    Assert.assertEquals(loginPage.getValidationMessage(), "Required","Error message is not displayed for Required credintials");
    System.out.println("Invalid login test passed - Error message displayed.");
    }
    
	@Test (priority = 4)
    public void testLoginCapitalUserName ()  {
    loginPage.loginMethod("ADMIN" ,"admin123" );
	Assert.assertEquals(loginPage.getDashboardHeader(), "Dashboard","Login failed - Dashboard not displayed");
	System.out.println("Login successful - Dashboard is displayed.");
	}

	@Test (priority = 5)
	public void testLoginCapitalPass ()   {	
	loginPage.loginMethod("Admin" ,"ADMIN123" );
    Assert.assertEquals(loginPage.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login test passed - Error message displayed.");
	}
	
	@Test (priority = 6)
	public void testLoginUserNameWithExtraSpace ()    {	
	loginPage.loginMethod(" Admin" ,"admin123" );
    Assert.assertEquals(loginPage.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login test passed - Error message displayed.");
	}
	
	@Test (priority = 7)
	public void testLoginPassWithExtraSpace(){	
	loginPage.loginMethod("Admin" ,"admin123 " );
    Assert.assertEquals(loginPage.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login test passed - Error message displayed.");
    }
	@Test ( priority = 8)
	public void testLogInMultipleTimes()   {	
    int counter = 5;
    while (counter!=0) {
    implicitWait();
    loginPage.loginMethod("Admin" ," admin123 " );
    Assert.assertEquals(loginPage.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login multiple times test passed - Error message displayed.");
    counter--;
    }
    loginPage.loginMethod("Admin" ,"admin123" );
	Assert.assertEquals(loginPage.getDashboardHeader(), "Dashboard","Login failed - Dashboard not displayed");
	System.out.println("Login successful & accout didn't locked - Dashboard is displayed.");
	}
	
	@AfterMethod
    public void logoutsession() {
    	try {
    		if(loginPage.getDashboardHeader().equals("Dashboard")) {
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
