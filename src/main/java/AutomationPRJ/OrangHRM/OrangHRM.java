package AutomationPRJ.OrangHRM;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangHRM {
	
	WebDriver driver;
	WebDriverWait wait ;

	public OrangHRM(WebDriver driver) {
		
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	By username = By.xpath("//input[@name='username']");
	By password = By.name("password");
	By loginButton = By.xpath("//button[@type='submit']");
	By dashboardHeader = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb')]");
	By invalidLoginMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

	By pimHeader = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]");
	By successToastMessage = By.xpath("//p[contains(@class,'oxd-toast-content-text')]");
	By saveButtonAddEmployee = By.xpath("//button[@type='submit']");
	By EmployeeID = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
	By validationMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
	By empInfo = By.xpath("//h5[text()='Employee Information']");
	By exceedMessage = By.xpath("//span[text()='Should not exceed 30 characters']");
	By createLoginToggleOff =  By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span");
	By createLoginToggleOn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span");
	
	By arrow = By.xpath("//span[@class='oxd-userdropdown-tab']");
	By logoutButton = By.xpath("//a[text()='Logout']");
	By pim = By.xpath("//span[text()='PIM']");
	By addEmploy = By.xpath("//a[text()='Add Employee']");
	By firstName = By.name("firstName");
	By lastName = By.name("lastName");
	By cancelButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[1]");
	
	By userNameAddEmployee = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
	By statusEnableRadio = By.xpath("//label[text()='Enabled']");
	By passwordAddEmployee = By.xpath("(//input[@type='password'])[1]");
	By confirmPassworAddEmployee = By.xpath("(//input[@type='password'])[2]");
	By myInfo = By.xpath("//span[text()='My Info']");
	By contactDetailsLink = By.xpath("//a[text()='Contact Details']");
	By workEmail = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div/div[1]/div/div[2]/input");
	By homeTelephone = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div/div[1]/div/div[2]/input");
	By mobile = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/input");
	By contactDetailsSaveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button");
	By contactDetailsCancelButton = By.xpath("//button[contains(text(),'Cancel')]");
	
	public void login(String uName, String pass) {
		driver.findElement(username).clear();
		driver.findElement(username).sendKeys(uName);
		wait.until(ExpectedConditions.attributeToBe(username, "value", uName));
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pass);
		wait.until(ExpectedConditions.attributeToBe(password, "value", pass));
		 driver.findElement(loginButton).click();
		
	}
	public String getDashboardHeader() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).getText();
	}
	public String getInvalidLoginMessage() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidLoginMessage)).getText();
	}
	public void addEmployee() {
		driver.findElement(pim).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(pimHeader)).getText();
		driver.findElement(addEmploy).click();
	}
	public void addEmployeeFields(String fName,String lName) {
		
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(fName);
		wait.until(ExpectedConditions.attributeToBe(firstName, "value", fName));
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(lName);
		wait.until(ExpectedConditions.attributeToBe(lastName, "value", lName));
	}
	public void clickSaveAddEmployee() {
		
		driver.findElement(saveButtonAddEmployee).click();

	}
	public String getSuccessToastMessage() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(successToastMessage)).getText();
	}
	public String getEmployeeID() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(EmployeeID));
		return driver.findElement(EmployeeID).getAttribute("value");
		
	}
	public void setEmployeeID(String employeeIDExist) {
		WebElement idField = wait.until(ExpectedConditions.elementToBeClickable(EmployeeID));
	    idField.click();
	    idField.sendKeys(Keys.CONTROL + "a");
	    idField.sendKeys(Keys.DELETE);
	    wait.until(ExpectedConditions.attributeToBe(EmployeeID, "value", ""));
	    idField.sendKeys(employeeIDExist);	
	}
	public String getValidationMessage() {
		return driver.findElement(validationMessage).getText();
	}
	public void cancelPIMButton() {
		driver.findElement(cancelButton).click();
	}
	public String validateEmployeeInfoHeader() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(empInfo)).getText();

	}
	public String validateExceed30CharsMessage() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(exceedMessage)).getText();

	}
	public void enableCreateLoginDetailsToggle() {
		 WebElement toggleLabel = wait.until(ExpectedConditions.elementToBeClickable(createLoginToggleOff));

		    WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));

		    if (!checkbox.isSelected()) {
		        toggleLabel.click();
		    }
	}
	public void disableCreateLoginDetailsToggle() {
		 WebElement toggleLabel = wait.until(ExpectedConditions.elementToBeClickable(createLoginToggleOff));

		    WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));

		    if (checkbox.isSelected()) {
		        toggleLabel.click();
		    }
	}
	public boolean checkToggle() {
		 //WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='checkbox']")));

		    return driver.findElement(createLoginToggleOff).isSelected();
	}


	public void fillCreateLoginDetailsForm(String uName, String pass,String confPass) {
		// Fill username
		wait.until(ExpectedConditions.elementToBeClickable(userNameAddEmployee)).clear();
		driver.findElement(userNameAddEmployee).sendKeys(uName);
		
		// Click Enabled status
		wait.until(ExpectedConditions.elementToBeClickable(statusEnableRadio)).click();
		
		// Wait for password fields to be visible and interactable
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordAddEmployee));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		// Fill password
		wait.until(ExpectedConditions.elementToBeClickable(passwordAddEmployee)).clear();
		driver.findElement(passwordAddEmployee).sendKeys(pass);
		
		// Fill confirm password
		wait.until(ExpectedConditions.elementToBeClickable(confirmPassworAddEmployee)).clear();
		driver.findElement(confirmPassworAddEmployee).sendKeys(confPass);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	public void ClickSaveButtonAddEmployee() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(saveButtonAddEmployee).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
	}
	





	public void logout() {
		// TODO Auto-generated method stub
		driver.findElement(arrow).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));	
		driver.findElement(logoutButton).click();

	}
	public void goToMyInfo() {
		wait.until(ExpectedConditions.elementToBeClickable(myInfo)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(contactDetailsLink)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Contact Details']")));
		// Wait for the form to be fully loaded - wait for all input fields
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@class='oxd-input oxd-input--active']")));
	}

	public void updateContactDetails(String email, String telephone, String mobileNumber) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		
		if (!email.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(workEmail));
			org.openqa.selenium.WebElement emailField = driver.findElement(workEmail);
			// Force clear and fill using JavaScript
			js.executeScript("arguments[0].value='';", emailField);
			try { Thread.sleep(300); } catch (InterruptedException e) {}
			js.executeScript("arguments[0].value=arguments[1];", emailField, email);
			try { Thread.sleep(300); } catch (InterruptedException e) {}
		}
		
		if (!telephone.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(homeTelephone));
			org.openqa.selenium.WebElement telField = driver.findElement(homeTelephone);
			// Force clear and fill using JavaScript
			js.executeScript("arguments[0].value='';", telField);
			try { Thread.sleep(300); } catch (InterruptedException e) {}
			js.executeScript("arguments[0].value=arguments[1];", telField, telephone);
			try { Thread.sleep(300); } catch (InterruptedException e) {}
		}

		if (!mobileNumber.isEmpty()) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(mobile));
			org.openqa.selenium.WebElement mobField = driver.findElement(mobile);
			// Force clear and fill using JavaScript
			js.executeScript("arguments[0].value='';", mobField);
			try { Thread.sleep(300); } catch (InterruptedException e) {}
			js.executeScript("arguments[0].value=arguments[1];", mobField, mobileNumber);
			try { Thread.sleep(300); } catch (InterruptedException e) {}
		}
	}
	
	public void saveContactDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(contactDetailsSaveButton)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Wait for save to complete
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cancelContactDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(contactDetailsCancelButton)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	public String getWorkEmail() {
		return driver.findElement(workEmail).getAttribute("value");
	}

	public String getHomeTelephone() {
		return driver.findElement(homeTelephone).getAttribute("value");
	}

	public String getMobile() {
		return driver.findElement(mobile).getAttribute("value");
	}




}

