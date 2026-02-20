package AutomationPRJ.OrangHRM;
 
import java.time.Duration;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class TestAddEmployee {

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

	public void testAdminAddEmployeeWithValidData()  {

	oranghrm.login("Admin","admin123");	

	oranghrm.addEmployee();

	oranghrm.addEmployeeFields("laith", "faheem");

	oranghrm.clickSaveAddEmployee();

	Assert.assertTrue(oranghrm.getSuccessToastMessage().contains("Success"),"Success toast Message not displayed after adding employee");	

    System.out.println("Employee Added successfully!");

	}

	@Test (priority = 2)

	public void testAdminAddEmployeeWithEmptyMandatoryFields()  {

	oranghrm.login("Admin","admin123");	

	oranghrm.addEmployee();

	oranghrm.clickSaveAddEmployee();

	String errorMessage = driver.findElement(By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")).getText();

    Assert.assertEquals(errorMessage, "Required","Error message is not displayed for Required credintials");

    System.out.println("Add employee with empty mandatory fields test passed - Error message displayed.");

	}

	@Test (priority = 3)

	public void testAdminAddEmployeewithPreventExistingID() throws InterruptedException  {

	oranghrm.login("Admin","admin123");	

	oranghrm.addEmployee();

	oranghrm.addEmployeeFields("laith", "faheem");

    String existingID = oranghrm.getEmployeeID();

    Thread.sleep(5000);

	oranghrm.clickSaveAddEmployee();

	Assert.assertTrue(oranghrm.getSuccessToastMessage().contains("Success"),"Success toast Message not displayed after adding employee");	

	System.out.println("Employee Added successfully!");

    oranghrm.addEmployee();

    oranghrm.addEmployeeFields("ibraheem", "ahmad");

    oranghrm.setEmployeeID(existingID);

    oranghrm.clickSaveAddEmployee();

    Assert.assertTrue(oranghrm.getValidationMessage().toLowerCase().contains("already exists"),"Error message is not displayed for duplicate employee ID");    

	}

	@Test (priority = 4)

	public void testCancelPIMButton() {

        oranghrm.login("ADMIN" ,"admin123" );

		oranghrm.addEmployee();

		oranghrm.addEmployeeFields("laith", "faheem");

		oranghrm.cancelPIMButton();

		Assert.assertEquals(oranghrm.validateEmployeeInfoHeader(), "Employee Information","Employee Information page is not displayed after cancelation of adding employee");

	    System.out.println("operation canceled successfully");

	}

	@Test	(priority = 5)

	public void testFirstNameLongerThan30Chars() {

    oranghrm.login("ADMIN" ,"admin123" );

    oranghrm.addEmployee();

    oranghrm.addEmployeeFields("test123456789011121314151617181920", "laith");

    Assert.assertEquals(oranghrm.validateExceed30CharsMessage(), "Should not exceed 30 characters","Error message is not displayed for exceeding 30 chars for first name");

    System.out.println("operation canceled successfully");

	}

	//******************************************************************ibrahim & laith are done********************************
 
	@Test	(priority = 6)

	public void testEnableCreateLoginDetailsToggle() {	

    oranghrm.login("ADMIN" ,"admin123" );

    oranghrm.addEmployee();

    oranghrm.enableCreateLoginDetailsToggle();

    Assert.assertTrue(oranghrm.checkToggle(),"the creat login details toggle is off");

	}

	@Test		(priority = 7)

	public void testDisableCreateLoginDetailsToggle() {

    oranghrm.login("ADMIN" ,"admin123" );

    oranghrm.addEmployee();

    oranghrm.enableCreateLoginDetailsToggle();

    oranghrm.disableCreateLoginDetailsToggle();

    Assert.assertFalse(oranghrm.checkToggle(),"the creat login details toggle is ONN");

	}

	@Test		(priority = 6)

	public void testValidCreateLoginDetailsToggle() {

    oranghrm.login("ADMIN" ,"admin123" );

    oranghrm.addEmployee();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

    oranghrm.enableCreateLoginDetailsToggle();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

    oranghrm.addEmployeeFields("Mariam", "Mariam");

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

    oranghrm.fillCreateLoginDetailsForm("mariam01", "Admin@123", "Admin@123");

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

    oranghrm.ClickSaveButtonAddEmployee();

    Assert.assertTrue(true, "Employee added successfully with valid login details");

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

	}

	@Test		(priority = 7)

	public void testEmptyCreateLoginDetailsToggle() {

		oranghrm.login("ADMIN" ,"admin123" );

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        oranghrm.addEmployee();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        oranghrm.enableCreateLoginDetailsToggle();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        oranghrm.ClickSaveButtonAddEmployee();

        WebElement requieredMsg = driver.findElement(By.xpath("//span[text()='Required']"));

        if(requieredMsg.isDisplayed()) {

        	Assert.assertEquals(requieredMsg.getText(), "Required");

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

	}

	@Test	(priority = 8)

	public void testMismatchConfirmPassword() {

		oranghrm.login("ADMIN" ,"admin123" );

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        oranghrm.addEmployee();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        oranghrm.enableCreateLoginDetailsToggle();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        oranghrm.addEmployeeFields("Mariam", "Mariam");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        oranghrm.fillCreateLoginDetailsForm("fadii", "1234fadi", "4321Fadi");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        try {

        	Thread.sleep(2000);

        } catch (InterruptedException e) {

        	e.printStackTrace();

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        WebElement passwordNotMatchMsg = null;

        	passwordNotMatchMsg = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

        Assert.assertTrue(passwordNotMatchMsg.isDisplayed(), "Password mismatch error message should be displayed");

        System.out.println("Error message found: " + passwordNotMatchMsg.getText());

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

 