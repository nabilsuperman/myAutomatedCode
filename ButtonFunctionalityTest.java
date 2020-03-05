package sanityTests;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class ButtonFunctionalityTest {

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub

		// Button Functionality Test
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\\\OneDrive\\Documents\\chromedriver.exe");

		Xls_Reader reader = new Xls_Reader("C:\\Users\\nabil\\Downloads\\exceldata\\samosas.xlsx");
		WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
		ngWebDriver.waitForAngularRequestsToFinish();
		Robot robot = new Robot();
		Actions ac = new Actions(driver);
		int threeSecs = 3000;
		int sevenSecs = 7000;
		int rowCount = reader.getRowCount("Chat");
		int rowNum = 2;
		String URL = "https://healable-acptfront.herokuapp.com/";
		driver.get(URL);
		driver.manage().window().maximize();
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
		// PASSWORD

		driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
		robot.delay(threeSecs);

		// Sending password via excel
		String Password1 = reader.getCellData("Chat", "patPassword", rowNum);
		System.out.println("password is" + Password1);
		ac.sendKeys(Password1).build().perform();

		// clicking login button
		driver.findElement(ByAngular.buttonText("Log In")).click();
		robot.delay(sevenSecs);
//-----------------------------------------------------------------------------------------------------------------
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
		System.out.println("Test Passed");

	}

}
