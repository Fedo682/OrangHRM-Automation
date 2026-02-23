package AutomationPRJ.OrangHRM;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationModule {

	WebDriver driver;
	WebDriverWait wait;

	public OrganizationModule(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	By admin = By.xpath("//a[contains(@href,'/admin/viewAdminModule')]");
	By organization = By.xpath("//span[normalize-space()='Organization']");
	By GeneralInformation = By.xpath("//a[normalize-space()='General Information']");
	By editToggle = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div/label/span");
	By organizationName = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/input");
	By RegistrationNumber = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
	By TaxID = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
	By phone = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input");
	By email = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/input");
	By address1 = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div[1]/div/div[2]/input");
	By address2 = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div[2]/div/div[2]/input");
	By city = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div[3]/div/div[2]/input");
	By state = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[1]/div/div[2]/input");
	By zipCode = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[2]/div/div[2]/input");
	By countryDropdown = By
			.xpath("//label[normalize-space()='Country']/following::div[contains(@class,'oxd-select-text-input')][1]");
	By notice = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[6]/div/div/div[2]/textarea");
	By saveButton = By.xpath("//button[@type='submit']");
	By getCompanyNameErrorMessage = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/span");

	By locations = By.xpath("//a[contains(normalize-space(.),'Locations')]");
	By addLocationButton = By.xpath("//button[contains(normalize-space(.),'Add')]");
	By addLocationsName = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/input");
	By addLocationsCity = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
	By addLocationsState = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
	By addLocationsZip = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[3]/div/div[2]/input");
	By addLocationCountryDropdown = By
			.xpath("//label[normalize-space()='Country']/following::div[contains(@class,'oxd-select-text')][1]");

	private By addLocationCountryOption(String countryName) {
		return By.xpath("//div[@role='listbox']//*[normalize-space()='" + countryName + "']");
	}

	By addLocationsPhone = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[5]/div/div[2]/input");
	By addLocationsFax = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[6]/div/div[2]/input");

	By addLocationsAddress = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[7]/div/div[2]/textarea");
	By addLocationSaveButton = By.xpath("//button[contains(normalize-space(.),'Save')]");
	By locationName = By.xpath("//div[@role='row']//a[normalize-space()='AXSOS']");
	By editStructureTogle = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div/label/span");
	By addStructureButton = By.xpath("//button[contains(normalize-space(.),'Add')]");
	By addUnitId = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div/div/div/form/div[1]/div/div[2]/input");
	By addUnitName = By
			.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div/div/div/form/div[2]/div/div[2]/input");
	By saveaddStructureButton = By.xpath("//button[contains(normalize-space(.),'Save')]");
	By automationTestingUnit = By
			.xpath("//div[contains(@class,'org-name') and contains(normalize-space(),'Automationtesting')]");

	public void clickGeneralInformation() {
		driver.findElement(admin).click();
		wait.until(ExpectedConditions.elementToBeClickable(organization)).click();
		driver.findElement(GeneralInformation).click();
	}

	public void clickToggle() {
		WebElement toggle = driver.findElement(editToggle);
		if (!toggle.isSelected()) {
			toggle.click();
		}
	}

	public void changeCompanyName(String name) {
		WebElement organizationNames = driver.findElement(organizationName);
		organizationNames.click();
		organizationNames.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		organizationNames.sendKeys(Keys.BACK_SPACE);
		organizationNames.sendKeys(name);
	}

	public void changeRegistrationNumber(String regNum) {
		WebElement regNumber = wait.until(ExpectedConditions.elementToBeClickable(RegistrationNumber));
		regNumber.click();
		regNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		regNumber.sendKeys(Keys.BACK_SPACE);
		regNumber.sendKeys(regNum);
	}

	public void changeTaxID(String taxId) {
		WebElement taxIDField = wait.until(ExpectedConditions.elementToBeClickable(TaxID));
		taxIDField.click();
		taxIDField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		taxIDField.sendKeys(Keys.BACK_SPACE);
		taxIDField.sendKeys(taxId);
	}

	public void changePhone(String phoneNum) {
		WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(phone));
		phoneField.click();
		phoneField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		phoneField.sendKeys(Keys.BACK_SPACE);
		phoneField.sendKeys(phoneNum);
	}

	public void changeEmail(String emailAddress) {
		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(email));
		emailField.click();
		emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		emailField.sendKeys(Keys.BACK_SPACE);
		emailField.sendKeys(emailAddress);
	}

	public void changeAddress1(String address) {
		WebElement address1Field = wait.until(ExpectedConditions.elementToBeClickable(address1));
		address1Field.click();
		address1Field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		address1Field.sendKeys(Keys.BACK_SPACE);
		address1Field.sendKeys(address);
	}

	public void changeAddress2(String address) {
		WebElement address2Field = wait.until(ExpectedConditions.elementToBeClickable(address2));
		address2Field.click();
		address2Field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		address2Field.sendKeys(Keys.BACK_SPACE);
		address2Field.sendKeys(address);
	}

	public void changeCity(String cityName) {
		WebElement cityField = wait.until(ExpectedConditions.elementToBeClickable(city));
		cityField.click();
		cityField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		cityField.sendKeys(Keys.BACK_SPACE);
		cityField.sendKeys(cityName);
	}

	public void changeState(String stateName) {
		WebElement stateField = wait.until(ExpectedConditions.elementToBeClickable(state));
		stateField.click();
		stateField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		stateField.sendKeys(Keys.BACK_SPACE);
		stateField.sendKeys(stateName);
	}

	public void scrollTosaveButton() {
		WebElement saveButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButtonElement);

	}

	public void changeZipCode(String zip) {
		WebElement zipCodeField = wait.until(ExpectedConditions.elementToBeClickable(zipCode));
		zipCodeField.click();
		zipCodeField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		zipCodeField.sendKeys(Keys.BACK_SPACE);
		zipCodeField.sendKeys(zip);
	}

	private By countryOption(String countryName) {
		return By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='" + countryName + "']");
	}

	public void changeCountry(String countryName) {
		wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)).click();

		wait.until(ExpectedConditions.elementToBeClickable(countryOption(countryName))).click();
	}

	public void changeNotes(String note) {
		WebElement notesField = wait.until(ExpectedConditions.elementToBeClickable(notice));
		notesField.click();
		notesField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		notesField.sendKeys(Keys.BACK_SPACE);
		notesField.sendKeys(note);
	}

	public void clickSaveButton() {
		driver.findElement(saveButton).click();
	}

	public String getCompanyNameErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(getCompanyNameErrorMessage)).getText();
	}

	public void clickLocations() {

		driver.findElement(admin).click();
		wait.until(ExpectedConditions.elementToBeClickable(organization)).click();
		wait.until(ExpectedConditions.elementToBeClickable(locations)).click();
	}

	public void clickAddLocation() {
		wait.until(ExpectedConditions.elementToBeClickable(addLocationButton)).click();
	}

	public void addLocation(String name, String city, String state, String zip, String phone, String fax,
			String address) {
		wait.until(ExpectedConditions.elementToBeClickable(addLocationsName)).sendKeys(name);

		driver.findElement(addLocationsCity).sendKeys(city);
		driver.findElement(addLocationsState).sendKeys(state);
		driver.findElement(addLocationsZip).sendKeys(zip);
		driver.findElement(addLocationCountryDropdown).click();
		driver.findElement(addLocationsPhone).sendKeys(phone);
		driver.findElement(addLocationsFax).sendKeys(fax);
		driver.findElement(addLocationsAddress).sendKeys(address);

	}

	public void addLocationSelectCountry(String countryName) {
		driver.findElement(addLocationCountryDropdown).click();
		wait.until(ExpectedConditions.elementToBeClickable(addLocationCountryOption(countryName))).click();
	}

	public void clickAddLocationSaveButton() {
		driver.findElement(addLocationSaveButton).click();
	}

	public String locationName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locationName)).getText();
	}

	public void clickStructre() {
		driver.findElement(admin).click();
		wait.until(ExpectedConditions.elementToBeClickable(organization)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(normalize-space(.),'Structure')]")))
				.click();
	}

	public void clickEditStructureToggle() {
		WebElement toggle = driver.findElement(editStructureTogle);
		if (!toggle.isSelected()) {
			toggle.click();
		}
	}

	public void addStructure(String unitId, String unitName) {
		driver.findElement(addStructureButton).click();
		wait.until(ExpectedConditions.elementToBeClickable(addUnitId)).sendKeys(unitId);
		driver.findElement(addUnitName).sendKeys(unitName);
		driver.findElement(saveaddStructureButton).click();
	}

	public String automationTestingUnit() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(automationTestingUnit)).getText();
	}

}
