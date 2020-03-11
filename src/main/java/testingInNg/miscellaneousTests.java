package testingInNg;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

import sanityTests.Xls_Reader;

public class miscellaneousTests {

	WebDriver driver;
	Robot robot;
	int threeSecs = 3000;
	int sevenSecs = 7000;
	int rowNum = 2;
	String URL = "https://healable-acptfront.herokuapp.com/";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
	Xls_Reader reader;
	Actions ac;

	@BeforeMethod
	public void setUp() throws AWTException {

		reader = new Xls_Reader("C:\\Users\\nabil\\Downloads\\exceldata\\samosas.xlsx");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\OneDrive\\Documents\\chromedriver.exe");
		driver = new ChromeDriver(); // Launching Chrome
		ac = new Actions(driver);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		robot = new Robot();
		ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);

	}

	@Test(priority = 1, description = "This test will log in as the Patient account")
	public void PatientLogin() {
		driver.get(URL);
		ngWebDriver.waitForAngularRequestsToFinish();

		robot.delay(threeSecs);
		driver.findElement(ByAngular.buttonText("Login")).click();
		robot.delay(threeSecs);
		String Username1 = reader.getCellData("Chat", "PatUser", rowNum);
		System.out.println(Username1);
		// Clicking user name element
		driver.findElement(ByAngularOptions.className("text-input")).click();
		robot.delay(sevenSecs);
		ac.sendKeys(Username1).build().perform();

		driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
		robot.delay(threeSecs);

		// Sending password via excel
		String Password1 = reader.getCellData("Chat", "patPassword", rowNum);
		System.out.println("password is" + Password1);
		ac.sendKeys(Password1).build().perform();

		// clicking login button
		driver.findElement(ByAngular.buttonText("Log In")).click();
		robot.delay(sevenSecs);
		System.out.println("You have successfully logged in as the patient");
	}

	@Test(priority = 2, dependsOnMethods = "PatientLogin", description = "This Test will click on the following buttons on the main Page: Home, Videocall, chat, groups, notifications, settings, account icon")
	public void buttonFunctionality() {
		PatientLogin();
		// clicking home
		driver.findElement(ByAngularOptions.xpath("//*[@name='home']")).click();
		robot.delay(threeSecs);

		// clicking VideoCall
		driver.findElement(ByAngularOptions.xpath("//*[@name='videocam']")).click();
		robot.delay(threeSecs);

		// Clicking Chat
		// *[@name='chatbubbles']
		driver.findElement(ByAngularOptions.xpath("//*[@name='chatbubbles']")).click();
		robot.delay(threeSecs);

		// Groups
		// *[@name='people']

		driver.findElement(ByAngularOptions.xpath("//*[@name='people']")).click();
		robot.delay(threeSecs);

		// Clicking notifications
		// *[@name='notifications']

		driver.findElement(ByAngularOptions.xpath("//*[@name='notifications']")).click();
		robot.delay(threeSecs);

		// clicking Settings
		// *[@name='more']
		driver.findElement(ByAngularOptions.xpath("//*[@name='more']")).click();
		robot.delay(threeSecs);

		// clicking Account Icon
		// *[@id='imgPosition']
		driver.findElement(ByAngularOptions.xpath("//*[@id='imgPosition']")).click();
		robot.delay(threeSecs);

		// Going back to home page.

		// clicking the back arrow

		driver.findElement(ByAngularOptions.xpath(
				"(//*[@class='glb-header-button-transparent glb-padding-inherit--positioning bar-button bar-button-md bar-button-default bar-button-default-md'])[1]"))
				.click();
		robot.delay(threeSecs);

		driver.findElement(ByAngularOptions.xpath("//*[@name='home']")).click();
		robot.delay(threeSecs);
	}

	@Test(priority = 3, description = "Create a new userAccount")
	public void wallPost() {

		String URL = "https://healable-acptfront.herokuapp.com/";
		driver.manage().window().maximize();
		ngWebDriver.waitForAngularRequestsToFinish();
		robot.delay(threeSecs);
		for (int a = 100; a < 102; a++) {
			driver.get(URL);
			robot.delay(sevenSecs);
			driver.findElement(ByAngularOptions.xpath(
					"//*[@id='signup' and @class='button button-md button-default button-default-md button-full button-full-md']"))
					.click();
			robot.delay(threeSecs);

			// Enter an EMAIL ADDRESS
			driver.findElement(ByAngularOptions.xpath("(//*[@formcontrolname='email'])[2]")).click();
			ac.sendKeys("pat" + a + "@pat.com").build().perform();
			// break;

			ac.sendKeys(Keys.TAB).build().perform();
			ac.sendKeys("Healing123!").build().perform();
			ac.sendKeys(Keys.TAB).build().perform();
			ac.sendKeys(Keys.TAB).build().perform();

			ac.sendKeys(Keys.ENTER).build().perform();
			ac.sendKeys(Keys.TAB).build().perform();

			ac.sendKeys(Keys.ENTER).build().perform();
			robot.delay(sevenSecs);
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
