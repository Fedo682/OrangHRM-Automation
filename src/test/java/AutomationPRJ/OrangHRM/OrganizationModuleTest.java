package AutomationPRJ.OrangHRM;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import java.net.URL;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrganizationModuleTest {

	WebDriver driver;
	OrganizationModule organizationModule;
	Login loginpage;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs",
				Map.of("credentials_enable_service", false, "profile.password_manager_enabled", false,
						"autofill.profile_enabled", false, "profile.autofill_address_enabled", false));
		driver = new ChromeDriver(options);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		organizationModule = new OrganizationModule(driver);
		loginpage = new Login(driver);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void implicitWait() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@DataProvider(name = "locationsData")
	public Object[][] locationsData() throws Exception {
		URL url = getClass().getClassLoader().getResource("MOCK_DATA_Locations.xlsx");
		if (url == null) {
			throw new RuntimeException("Excel file not found. Put it in src/test/resources.");
		}

		String filePath = Paths.get(url.toURI()).toString();
		return ExcelUtils.readSheet(filePath, "data");
	}

	@Test(priority = 1) // TC035-TC038
	public void testChangeGeneralInformation() {
		organizationModule.clickGeneralInformation();
		organizationModule.clickToggle();
		organizationModule.changeCompanyName("AXSOS");
		organizationModule.changeRegistrationNumber("123456789");
		organizationModule.changeTaxID("123456789");
		organizationModule.changePhone("123456789");
		organizationModule.changeEmail("ibrahim@axsos.com");
		organizationModule.changeAddress1("Street1");
		organizationModule.changeAddress2("Street2");
		organizationModule.changeCity("Berlin");
		organizationModule.changeState("Rhienland-Pfalz");
		organizationModule.scrollTosaveButton();
		organizationModule.changeZipCode("12345");
		organizationModule.changeCountry("Germany");
		organizationModule.changeNotes("Ibrahim");
		organizationModule.clickSaveButton();

	}

	@Test(priority = 2) // TC039
	public void testChangeGeneralInformationWithoutCompanyName() {
		loginpage.loginMethod("Admin", "admin123");
		organizationModule.clickGeneralInformation();
		organizationModule.clickToggle();
		organizationModule.changeCompanyName("");
		organizationModule.changeRegistrationNumber("123456789");
		organizationModule.changeTaxID("123456789");
		organizationModule.changePhone("123456789");
		organizationModule.changeEmail("ibrahim@axsos.com");
		organizationModule.changeAddress1("Street1");
		organizationModule.changeAddress2("Street2");
		organizationModule.changeCity("Berlin");
		organizationModule.changeState("Rhienland-Pfalz");
		organizationModule.scrollTosaveButton();
		organizationModule.changeZipCode("12345");
		organizationModule.changeCountry("Germany");
		organizationModule.changeNotes("Ibrahim");
		organizationModule.clickSaveButton();
		Assert.assertEquals(organizationModule.getCompanyNameErrorMessage(), "Required");

	}

	@Test(priority = 3) // TC040
	public void addLocation() {
		organizationModule.clickLocations();
		organizationModule.clickAddLocation();
		organizationModule.addLocation("AXSOS", "Berlin", "RLP", "123456789", "123456789", "123456789", "steet1");
		organizationModule.addLocationSelectCountry("Germany");
		organizationModule.clickAddLocationSaveButton();
		Assert.assertEquals(organizationModule.locationName(), "AXSOS");

	}

	@Test(dataProvider = "locationsData", priority = 4) // TC041
	public void addLocationFromExcel(String name, String city, String state, String zip, String phone, String fax,
			String address) {

		System.out.println("Adding location: " + name + ", " + city + ", " + state + ", " + zip + ", " + phone + ", "
				+ fax + ", " + address);

		organizationModule.clickLocations();
		organizationModule.clickAddLocation();
		organizationModule.addLocation(name, city, state, zip, phone, fax, address);
		organizationModule.addLocationSelectCountry("Germany");
		organizationModule.clickAddLocationSaveButton();

	}

	@Test(priority = 5) // TC042-TC044
	public void structureAdd() {
		organizationModule.clickStructre();
		organizationModule.clickEditStructureToggle();
		organizationModule.addStructure("1", "Automationtesting");
		Assert.assertEquals(organizationModule.automationTestingUnit(), "Automationtesting");

	}
}