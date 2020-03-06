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

public class ForgotPassword {

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
	      System.setProperty("webdriver.chrome.driver","C:\\Users\\nabil\\\\OneDrive\\Documents\\chromedriver.exe");

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
		  int rowNum =2;
	  	  String URL = "https://healable-acptfront.herokuapp.com/";
	       driver.get(URL);
		   ngWebDriver.waitForAngularRequestsToFinish();

	       robot.delay(threeSecs);
	       driver.findElement(ByAngular.buttonText("Login")).click();
	       robot.delay(sevenSecs);
	       driver.findElement(ByAngularOptions.cssSelector(".glb-margin-right-6px--positioning")).click();
	       robot.delay(threeSecs);
	       driver.findElement(ByAngularOptions.xpath(("//*[@formcontrolname='email']"))).click();
	       
	      //Enter a desired email for which the password should be resend. (from excel)
	       String passReset=reader.getCellData("Chat", "PatUser", rowNum);
	       ac.sendKeys(passReset).build().perform();
	       for(int i=1; i<5; i++) {
	       driver.findElement(ByAngularOptions.xpath("(//*[@class='button-inner'])[9]")).click();
	       }
	}

}
