package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
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
	
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		oranghrm = new OrangHRM(driver);
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


	}
	@BeforeMethod
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	

	}
	
	@Test (priority = 1)
	public void testLoginValidCredintials() {
		oranghrm.login("Admin","admin123");	
		System.out.println("");
		Assert.assertEquals(oranghrm.getDashboardHeader(), "Dashboard","Login failed - Dashboard not displayed");
		System.out.println("Login successful - Dashboard is displayed.");
		}
	

	@Test (priority = 2)
	public void testLoginInValidCredintials() {
	oranghrm.login("addminn","not exist");
	    Assert.assertEquals(oranghrm.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
	    System.out.println("Invalid login test passed - Error message displayed.");
	}
	
	@Test (priority = 3)
    public void testLoginEmpty ()  {
    oranghrm.login("" ,"");
    String errorMessage = driver.findElement(By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")).getText();
    Assert.assertEquals(errorMessage, "Required","Error message is not displayed for Required credintials");
    System.out.println("Invalid login test passed - Error message displayed.");
    }
    
	@Test (priority = 4)
    public void testLoginCapitalUserName ()  {
    oranghrm.login("ADMIN" ,"admin123" );
	Assert.assertEquals(oranghrm.getDashboardHeader(), "Dashboard","Login failed - Dashboard not displayed");
	System.out.println("Login successful - Dashboard is displayed.");
	}

	@Test (priority = 5)
	public void testLoginCapitalPass ()   {	
    oranghrm.login("Admin" ,"ADMIN123" );
    Assert.assertEquals(oranghrm.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login test passed - Error message displayed.");
	}
	
	@Test (priority = 6)
	public void testLoginUserNameWithExtraSpace ()    {	
    oranghrm.login(" Admin" ,"admin123" );
    Assert.assertEquals(oranghrm.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login test passed - Error message displayed.");
	}
	
	@Test (priority = 7)
	public void testLoginPassWithExtraSpace(){	
    oranghrm.login("Admin" ,"admin123 " );
    Assert.assertEquals(oranghrm.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login test passed - Error message displayed.");
    }
	@Test ( priority = 8)
	public void testLogInMultipleTimes()   {	
    int counter = 5;
    while (counter!=0) {
    oranghrm.login("Admin" ," admin123 " );
    Assert.assertEquals(oranghrm.getInvalidLoginMessage(), "Invalid credentials","Error message is not displayed for invalid login");
    System.out.println("Invalid login multiple times test passed - Error message displayed.");
    counter--;
    }
    oranghrm.login("Admin" ,"admin123" );
	Assert.assertEquals(oranghrm.getDashboardHeader(), "Dashboard","Login failed - Dashboard not displayed");
	System.out.println("Login successful & accout didn't locked - Dashboard is displayed.");
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
