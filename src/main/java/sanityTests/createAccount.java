package sanityTests;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class createAccount {
//Generate many accounts using this program
	public static void main(String[] args) throws AWTException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\OneDrive\\Documents\\chromedriver.exe");

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
		driver.manage().window().maximize();
		ngWebDriver.waitForAngularRequestsToFinish();
		robot.delay(threeSecs);
		for (int a = 71; a < 90; a++) {
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

			// continue;
		}
	}
}
