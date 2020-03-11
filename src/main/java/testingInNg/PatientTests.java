package testingInNg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

public class PatientTests {

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
		int rowCount = reader.getRowCount("Chat");
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

	@Test(priority = 2, dependsOnMethods = "PatientLogin", description = "Login, and send a message to the doctor using the chat feature")
	public void sendChatToDoctor() {

		PatientLogin();
		// clickChat
		driver.findElement(ByAngularOptions.xpath("//*[@name='chatbubbles']")).click();
		robot.delay(threeSecs);

		robot.delay(sevenSecs);

		driver.findElement(ByAngularOptions.xpath("//*[@class='midCol col']")).click();
		robot.delay(threeSecs);

		// --------------------------------------------------------------------------------------------------------------------------
		// click Msg Box
		driver.findElement(ByAngularOptions.id("chatTyped")).click();
		robot.delay(threeSecs);
		String PatMsg = reader.getCellData("Chat", "PatMsg", rowNum);

		ac.sendKeys(PatMsg).build().perform();

		ac.sendKeys(Keys.ENTER).build().perform();
		robot.delay(sevenSecs);
		String msg = driver.findElement(ByAngularOptions.xpath("(//*[@class='msg-content' and @id='msgBody'])[15]"))
				.getText();
		System.out.println(msg);

		System.out.println("Chat successfull sent to the doctor");

	}

	@Test(priority = 3, dependsOnMethods = "PatientLogin", description = "Login to the patient account and Upload profile picutre")
	public void upLoadPicture() {
		PatientLogin();

		robot.delay(sevenSecs);
		driver.findElement(ByAngularOptions.xpath("//*[@id='imgPosition']")).click();
		robot.delay(threeSecs);

		WebElement uploadElement = driver.findElement(ByAngularOptions.xpath(
				"//*[@class='logo glb-border-radius-50-percent--styling glb-align-content--center glb-group-icon-sizing glb-margin-0-auto--positioning']"));
		uploadElement.click();
		robot.delay(sevenSecs);

		// SelectButton
		WebElement upload = driver.findElement(ByAngularOptions.xpath(
				"//*[@class='disable-hover action-sheet-button action-sheet-button-md action-sheet-button-default action-sheet-button-default-md']"));
		upload.click();
		// Uploading a picture using Robot Class!
		StringSelection ss = new StringSelection("C:\\Users\\nabil\\Downloads\\tyrannmatt.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		robot.delay(threeSecs);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.setAutoDelay(threeSecs);

		robot.delay(sevenSecs);
		driver.findElement(ByAngular.buttonText("Save")).click();
	}

	@Test(priority = 4, dependsOnMethods = "PatientLogin", description = "Login to the patient account and make a post on your wall")
	public void wallPost() {
		PatientLogin();
		// Click on Create Post

		driver.findElement(ByAngularOptions.cssSelector("#share")).click();
		robot.delay(threeSecs);
		// -----------------------------------------------------------------------
		// Write your thoughts (needs to be clicked).
		driver.findElement(ByAngularOptions.xpath("//textarea[@formcontrolname='thoughts']")).click();

		String ChatMsg = reader.getCellData("Chat", "WallMsg", rowNum);
		ac.sendKeys(ChatMsg).build().perform();
		robot.delay(threeSecs);
		
		// ------------------------------------------------------------------------------------------------

		// Click Save

		driver.findElement(ByAngularOptions.xpath(
				"//*[@class='glb-header-button-transparent glb-header-color bar-button bar-button-md bar-button-default bar-button-default-md']"))
				.click();

		robot.delay(sevenSecs);
		
		// Clicking no i am done


		driver.findElement(ByAngularOptions.xpath(
				"//*[@id='discoverGroup' and @class='glb-label--typography button button-md button-clear button-clear-md']"))
				.click();
		robot.delay(sevenSecs);
		//String hi =driver.findElement(ByAngularOptions.xpath("(//*[@class='layout col'])[2]")).getText();
		String hi =driver.findElement(ByAngularOptions.xpath("(//*[@class='card-content card-content-md'])[1]")).getText();

		System.out.println(hi);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}