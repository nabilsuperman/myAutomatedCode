package sanityTests;
import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class PatChat {

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		//Patient will msg the doctor
		
		
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

        //Patient Sign In
        
        //Open A new Tab Window
       // js.executeScript("window.open('https://healable-acptfront.herokuapp.com/','_blank');");

   //------------------------------------------------------------------------------------------------------------------------ 
       driver.get(URL);
	      ngWebDriver.waitForAngularRequestsToFinish();

       robot.delay(threeSecs);
       driver.findElement(ByAngular.buttonText("Login")).click();
       robot.delay(threeSecs);

	      String Username1 =reader.getCellData("Chat", "PatUser", rowNum);
		  System.out.println(Username1);
		  //Clicking user name element
       driver.findElement(ByAngularOptions.className("text-input")).click();
       robot.delay(sevenSecs);
        ac.sendKeys(Username1).build().perform();
        
    //----------------------------------------------------------------------------------------------------------------------
        //PASSWORD
        
        driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
        robot.delay(threeSecs);
     
        //Sending password via excel
        String Password1 = reader.getCellData("Chat", "patPassword", rowNum);
        System.out.println("password is" +Password1);
        ac.sendKeys(Password1).build().perform();
     
        //clicking login button
        driver.findElement(ByAngular.buttonText("Log In")).click();
        robot.delay(sevenSecs);
  //-------------------------------------------------------------------------------------------------------------------------      
        //clickChat
        //driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-wall/ion-tabs/ion-tab[1]/page-home/ion-header/span/ion-grid/ion-row/ion-col[3]/div/div[3]/ion-icon")).click();
        driver.findElement(ByAngularOptions.xpath("//*[@name='chatbubbles']")).click();robot.delay(threeSecs);

        robot.delay(sevenSecs);
        
        driver.findElement(ByAngularOptions.xpath("//*[@class='midCol col']")).click();
        robot.delay(threeSecs);
        
    //--------------------------------------------------------------------------------------------------------------------------
        //click Msg Box
        driver.findElement(ByAngularOptions.id("chatTyped")).click();
        robot.delay(threeSecs);
        String PatMsg = reader.getCellData("Chat", "PatMsg", rowNum);
        for(int j=1; j<10; j++) {
        ac.sendKeys(PatMsg).build().perform();
        }
        ac.sendKeys(Keys.ENTER).build().perform();
        robot.delay(threeSecs);
   
        System.out.println("Test has passed");

	}

}
