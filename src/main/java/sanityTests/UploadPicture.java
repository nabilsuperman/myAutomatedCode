package sanityTests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class UploadPicture {

	public static void main(String[] args) throws AWTException {
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
		// ------------------------------------------------------------------------------------------------------\

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
		// driver.findElement(ByAngularOptions.xpath(
		// "//*[@class='glb-header-button-transparent glb-header-color bar-button
		// bar-button-md bar-button-default bar-button-default-md']"))
		// .click();
		robot.delay(sevenSecs);
		driver.findElement(ByAngular.buttonText("Save")).click();

	}

}
