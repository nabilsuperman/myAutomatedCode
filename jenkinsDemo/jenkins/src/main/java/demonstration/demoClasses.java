package demonstration;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.NgWebDriver;


public class demoClasses {

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\\\OneDrive\\Documents\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
		ngWebDriver.waitForAngularRequestsToFinish();
		Robot robot = new Robot();
		Actions ac = new Actions(driver);
		int threeSecs = 3000;
		int sevenSecs = 7000;
		int rowNum = 2;
		String URL = "https://healable-acptfront.herokuapp.com/";
		driver.get(URL);

	}

}
