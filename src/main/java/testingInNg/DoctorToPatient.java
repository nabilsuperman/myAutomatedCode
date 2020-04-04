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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

import sanityTests.Xls_Reader;

public class DoctorToPatient {
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
	// DoctorChatMsg
	String msg;
	String msg2;

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
		ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);

	}

	@Test(priority = 1, description = "This test will log in as the physician account, and send a msg to patient")
	public void doctorLogin() {
		driver.get(URL);
		ngWebDriver.waitForAngularRequestsToFinish();

		robot.delay(threeSecs);
		driver.findElement(ByAngular.buttonText("Login")).click();
		robot.delay(threeSecs);
		String Username = reader.getCellData("Chat", "userNameDoc", rowNum);
		// System.out.println(Username);
		// Clicking user name element
		driver.findElement(ByAngularOptions.className("text-input")).click();
		robot.delay(sevenSecs);
		ac.sendKeys(Username).build().perform();

		driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
		robot.delay(threeSecs);

		// Sending password via excel
		String Password = reader.getCellData("Chat", "passWordDoc", rowNum);
		// System.out.println("password is" + Password);
		ac.sendKeys(Password).build().perform();

		// clicking login button
		driver.findElement(ByAngular.buttonText("Log In")).click();
		robot.delay(sevenSecs);

		// clickChat
		driver.findElement(ByAngularOptions.xpath("//*[@name='chatbubbles']")).click();

		robot.delay(threeSecs);
		// ---------------------------------------------------------------------------------------------------------------------
		driver.findElement(ByAngularOptions.xpath("//*[@class='midCol col']")).click();
		robot.delay(threeSecs);
		// click Msg Box
		driver.findElement(ByAngularOptions.id("chatTyped")).click();
		robot.delay(threeSecs);
		String DocMsg = reader.getCellData("Chat", "DocMsg", rowNum);
		ac.sendKeys(DocMsg).build().perform();
		ac.sendKeys(Keys.ENTER).build().perform();
		robot.delay(sevenSecs);
		msg = driver.findElement(ByAngularOptions.xpath("(//*[@class='msg-content' and @id='msgBody'])[15]")).getText();
		System.out.println(msg);

	}

	@Test(priority = 2, description = "Log in as the patient account and verify the previous msg")
	public void PatientLoginMsg() {
		driver.get(URL);

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
		// clickChat
		driver.findElement(ByAngularOptions.xpath("//*[@name='chatbubbles']")).click();
		robot.delay(threeSecs);

		robot.delay(sevenSecs);

		driver.findElement(ByAngularOptions.xpath("//*[@class='midCol col']")).click();
		robot.delay(threeSecs);

		// click Msg Box
		driver.findElement(ByAngularOptions.id("chatTyped")).click();
		robot.delay(sevenSecs);
		// Need to get the right Node of this
		msg2 = driver.findElement(ByAngularOptions.xpath("(//div[@class='msg-detail'])[last()]")).getText();
		robot.delay(sevenSecs);

		System.out.println(msg2);
		Assert.assertEquals(msg2, msg);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
