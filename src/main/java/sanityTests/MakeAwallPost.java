package sanityTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class MakeAwallPost {

	public static void main(String[] args) throws AWTException {
		// Make a wall Post on Healable.

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

		// ------------------------------------------------------------------------------

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
		System.out.println("------------------------------------------------------------------------------------");

		System.out.println("done");


		//System.out.println("Test Successfull");
		
			
		
}

}
