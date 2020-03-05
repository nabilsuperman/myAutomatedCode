package sanityTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class testNGtest {
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
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		int rowCount = reader.getRowCount("Chat");
		robot = new Robot();

	}

	@Test 
	public void goog() {
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
	}

	@AfterTest
	public void chat() {
		driver.findElement(ByAngularOptions.xpath("//*[@name='chatbubbles']")).click();
		robot.delay(threeSecs);

		robot.delay(sevenSecs);

		driver.findElement(ByAngularOptions.xpath("//*[@class='midCol col']")).click();
		robot.delay(threeSecs);
		

	}

	@AfterTest
	public void sendMsg() {
		driver.findElement(ByAngularOptions.id("chatTyped")).click();
		robot.delay(threeSecs);
		String PatMsg = reader.getCellData("Chat", "PatMsg", rowNum);
		for (int j = 1; j < 10; j++) {
			ac.sendKeys(PatMsg).build().perform();
		}
		ac.sendKeys(Keys.ENTER).build().perform();
		robot.delay(threeSecs);
	}

}
