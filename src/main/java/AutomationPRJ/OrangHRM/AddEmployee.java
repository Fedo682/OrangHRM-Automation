package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEmployee {
	WebDriver driver;
	WebDriverWait wait ;

	public AddEmployee(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		

	}
	By pimHeader = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]");
	By successToastMessage = By.xpath("//p[contains(@class,'oxd-toast-content-text')]");
	By saveButtonAddEmployee = By.xpath("//button[@type='submit']");
	By EmployeeID = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
	By validationMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
	By empInfo = By.xpath("//h5[text()='Employee Information']");
	By exceedMessage = By.xpath("//span[text()='Should not exceed 30 characters']");
	By createLoginToggleOff =  By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span");
	By createLoginToggleOn = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[2]/div/label/span");
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
	
	public void addEmployeePage() {
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


}
