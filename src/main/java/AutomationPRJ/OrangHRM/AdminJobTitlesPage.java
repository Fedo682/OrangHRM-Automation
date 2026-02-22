package AutomationPRJ.OrangHRM;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminJobTitlesPage {
	WebDriver driver;
	WebDriverWait wait;

	public AdminJobTitlesPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	By adminTap = By.xpath("//span[normalize-space()='Admin']");
	By jobTab = By.xpath("//span[contains(text(),'Job')]");
	By jobTitle = By.xpath("//a[@class='oxd-topbar-body-nav-tab-link' and text()='Job Titles']");
	By addButton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	By jobField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	By submitButton = By.xpath("//button[@type='submit']");
	By jobTitleMainPage = By.xpath("//h6[text()='Job Titles']");
	By firstJobTitle = By.xpath("(//div[@role='cell'])[2]");
	By alreadyExistsMessage = By.xpath("//span[text()='Already exists']");
	By requiredMessage = By.xpath("//span[text()='Required']");
	By shouldNotExceed100charactersMessage = By.xpath("//span[text()='Should not exceed 100 characters']");
	By cancelButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/button[1]");
	By editButton = By.xpath("//button[.//i[contains(@class,'bi-pencil-fill')]][1]");
	By editJobTitle = By.xpath("//h6[text()='Edit Job Title']");

	By updateJobTitleFiled = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input");
	By saveEditButton = By.xpath("//button[normalize-space(.)='Save']");
	By cancelEditButton = By.xpath("//button[normalize-space(.)='Cancel']");

	
	public String goToMainJobTitlePage() {
		WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));

		return longWait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleMainPage)).getText();
	}

	public void jobTabThenTitleClick() {

		wait.until(ExpectedConditions.elementToBeClickable(adminTap)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		wait.until(ExpectedConditions.elementToBeClickable(jobTab)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(jobTitle).click();
	}

	public void clickAddButton() {
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void addNewJob(String newJob) {

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(jobField));
		input.click(); // focus
		input.sendKeys(newJob);
	}

	public void clickSubmit() {
		driver.findElement(submitButton).click();

		WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		longWait.until(ExpectedConditions.or(ExpectedConditions.visibilityOfElementLocated(jobTitleMainPage),
				ExpectedConditions.visibilityOfElementLocated(alreadyExistsMessage),ExpectedConditions.visibilityOfElementLocated(shouldNotExceed100charactersMessage),
				ExpectedConditions.visibilityOfElementLocated(requiredMessage)));

	}

	public void clickCancel() {
		wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();

	}

	public String existedJobTitle() {
		WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return longWait.until(ExpectedConditions.visibilityOfElementLocated(firstJobTitle)).getText();
	}

	public String getAlreadyExistsMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(alreadyExistsMessage)).getText();

	}

	public String getRequiredMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessage)).getText();

	}
	public String getshouldNotExceed100charactersMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(shouldNotExceed100charactersMessage)).getText();

	}
	public void clickEditIcon() {
		By firstJobRow = By.xpath("(//div[@role='row'])[2]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstJobRow));

		WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editButton));
		editBtn.click();
	}

	public void editJobTitle(String editJob) {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(updateJobTitleFiled));
		input.click(); // focus
		input.clear();
		input.click();
		input.sendKeys(editJob);
	}

	public String goToEditJobTitlePage() {
		WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));

		return longWait.until(ExpectedConditions.visibilityOfElementLocated(editJobTitle)).getText();
	}

	public void clcikSaveEditButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveEditButton)).click();

	}

	public void clcikCancelEditButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(cancelEditButton)).click();

	}



	


}
