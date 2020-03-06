package testingInNg;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

import sanityTests.Xls_Reader;

public class DoctorTests {
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
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\\\OneDrive\\Documents\\chromedriver.exe");
		driver = new ChromeDriver(); // Launching Chrome
		ac = new Actions(driver);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		robot = new Robot();
		ngWebDriver= new NgWebDriver((JavascriptExecutor) driver);
		
	}

	@Test(priority=1,description = "This test will log in as the physician account")
	public void doctorLogin() {
		driver.get(URL);
		ngWebDriver.waitForAngularRequestsToFinish();

		robot.delay(threeSecs);
		driver.findElement(ByAngular.buttonText("Login")).click();
		robot.delay(threeSecs);
		String Username = reader.getCellData("Chat", "userNameDoc", rowNum);
		System.out.println(Username);
		// Clicking user name element
		driver.findElement(ByAngularOptions.className("text-input")).click();
		robot.delay(sevenSecs);
		ac.sendKeys(Username).build().perform();

		driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
		robot.delay(threeSecs);

		// Sending password via excel
		String Password = reader.getCellData("Chat", "passWordDoc", rowNum);
		System.out.println("password is" + Password);
		ac.sendKeys(Password).build().perform();

		// clicking login button
		driver.findElement(ByAngular.buttonText("Log In")).click();
		robot.delay(sevenSecs);

	}

	@Test(priority=2,dependsOnMethods="doctorLogin", description = "This test will click on chat feature and send a message to the patient.")
	public void sendMsgToPatient() {
		doctorLogin();
		// clickChat
		driver.findElement(ByAngularOptions.xpath("//*[@name='chatbubbles']")).click();

		robot.delay(threeSecs);
		driver.findElement(ByAngularOptions.xpath("//*[@class='midCol col']")).click();
		robot.delay(threeSecs);
		// click Msg Box
		driver.findElement(ByAngularOptions.id("chatTyped")).click();
		robot.delay(threeSecs);
		String DocMsg = reader.getCellData("Chat", "DocMsg", rowNum);
		ac.sendKeys(DocMsg).build().perform();
		ac.sendKeys(Keys.ENTER).build().perform();
		robot.delay(sevenSecs);
	}

	@Test(priority=3,dependsOnMethods="doctorLogin", description = "This will test the functionality of the create case feature available to the physicians")
	public void createCase() {
		doctorLogin();

		WebElement createCase = driver.findElement(ByAngularOptions.xpath(
				"//*[@id='newCase'] |(//*[@class='buttonClass button-case glb-font-16--typography button button-md button-clear button-clear-md'])[1]"));
		createCase.click();
		robot.delay(sevenSecs);
		// Case Title click
		driver.findElement(ByAngularOptions.xpath("(//*[@formcontrolname='caseTitle'])[2]")).click();
		String casetitl = reader.getCellData("Chat", "caseTitle", rowNum);
		ac.sendKeys(casetitl).build().perform();
		robot.delay(sevenSecs);
		System.out.println("Case Extract");
		driver.findElement(ByAngularOptions.xpath("(//*[@formcontrolname='extract'])[2]")).click();
		String xtract = reader.getCellData("Chat", "caseExtract", rowNum);
		ac.sendKeys(xtract).build().perform();
		robot.delay(threeSecs);
		ac.sendKeys(Keys.TAB).build().perform();
		System.out.println("Ask questions");
		String questions = reader.getCellData("Chat", "AskQuestion", rowNum);
		ac.sendKeys(questions).build().perform();
		robot.delay(sevenSecs);
		// clicking the 'i confirm there is no PII info'
		ac.sendKeys(Keys.TAB).build().perform();
		ac.sendKeys(Keys.ENTER).build().perform();

		driver.findElement(ByAngularOptions.xpath(
				"//*[@class='glb-header-button-transparent glb-header-color bar-button bar-button-md bar-button-default bar-button-default-md']"))
				.click();
		robot.delay(sevenSecs);
		driver.findElement(ByAngularOptions.xpath("//*[@id='discoverGroup']")).click();
		System.out.println("the test has executed successfully");
	}
		@AfterMethod
		public void tearDown() {
			driver.quit();
		}

	}

