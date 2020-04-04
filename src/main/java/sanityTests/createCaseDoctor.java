package sanityTests;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class createCaseDoctor {

	public static void main(String[] args) throws AWTException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\\\OneDrive\\Documents\\chromedriver.exe");

		Xls_Reader reader = new Xls_Reader("C:\\Users\\nabil\\Downloads\\exceldata\\samosas.xlsx");
		WebDriver driver = new ChromeDriver();
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
		ngWebDriver.waitForAngularRequestsToFinish();
		robot.delay(threeSecs);

		driver.findElement(ByAngular.buttonText("Login")).click();
		robot.delay(threeSecs);

		// Doctor Sign
		// In-------------------------------------------------------------------------------------------------
		String Username = reader.getCellData("Chat", "userNameDoc", rowNum);
		System.out.println(Username);
		// Clicking user name element
		driver.findElement(ByAngularOptions.className("text-input")).click();
		robot.delay(sevenSecs);
		ac.sendKeys(Username).build().perform();
		robot.delay(threeSecs);
		// clicking password field

		driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
		robot.delay(threeSecs);

		// Sending password via excel
		String Password = reader.getCellData("Chat", "passWordDoc", rowNum);
		System.out.println("password is" + Password);
		ac.sendKeys(Password).build().perform();
		// clicking login button
		driver.findElement(ByAngular.buttonText("Log In")).click();
		robot.delay(sevenSecs);

		System.out.println("you are now logged in");
		// -----------------------------------------------------------------------------------------------------------------
		WebElement createCase = driver.findElement(ByAngularOptions.xpath(
				"//*[@id='newCase'] |(//*[@class='buttonClass button-case glb-font-16--typography button button-md button-clear button-clear-md'])[1]"));
		createCase.click();
		robot.delay(threeSecs);
		// Case Title click
		driver.findElement(ByAngularOptions.xpath("(//*[@formcontrolname='caseTitle'])[2]")).click();
		String casetitl = reader.getCellData("Chat", "caseTitle", rowNum);
		ac.sendKeys(casetitl).build().perform();
		robot.delay(threeSecs);
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

		// driver.findElement(ByAngularOptions.xpath("//*[@class='checkbox-icon
		// checkbox-checked']")).click();
		// Saving
		driver.findElement(ByAngularOptions.xpath(
				"//*[@class='glb-header-button-transparent glb-header-color bar-button bar-button-md bar-button-default bar-button-default-md']"))
				.click();
		robot.delay(sevenSecs);
		driver.findElement(ByAngularOptions.xpath("//*[@id='discoverGroup']")).click();
		robot.delay(sevenSecs);
		//String hi =driver.findElement(ByAngularOptions.xpath("(//*[@class='HomeWallLayout'])[1]")).getText();

		//System.out.println(hi);

		System.out.println("the test has executed successfully");
	}

}
