package sanityTests;
import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class DoctorSignup {

	public static void main(String[] args) throws AWTException {
		//Generating doctor SignUp TestScript
		
		//Step 1: Log in for the First time (New user )
	      System.setProperty("webdriver.chrome.driver","C:\\Users\\nabil\\\\OneDrive\\Documents\\chromedriver.exe");

		  Xls_Reader reader = new Xls_Reader("C:\\Users\\nabil\\Downloads\\exceldata\\samosas.xlsx");
		  WebDriver driver = new ChromeDriver();

		  NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
	      ngWebDriver.waitForAngularRequestsToFinish();
	      Robot robot = new Robot();
	      Actions ac = new Actions(driver);
	      int threeSecs = 3000;
	      int sevenSecs = 7000;
	  	  String URL = "https://healable-acptfront.herokuapp.com/";
		  ngWebDriver.waitForAngularRequestsToFinish();
		  driver.manage().window().maximize();
		  driver.manage().deleteAllCookies();
		  int rowCount = reader.getRowCount("Doctor");
		  int rowNum =2;
		  //----------------------------------------------------------------------------------------------------------------------------------------------------
		 
		  //for (int rowNum =2; rowNum<=rowCount; rowNum++) {

			  
		  driver.get(URL);
		  robot.delay(threeSecs);
          driver.findElement(ByAngular.buttonText("Login")).click();
          robot.delay(threeSecs);

 
      	  
	      String Username =reader.getCellData("Doctor", "Username", rowNum);
		  System.out.println(Username);
		  //Clicking user name element
          driver.findElement(ByAngularOptions.className("text-input")).click();
          robot.delay(sevenSecs);
           ac.sendKeys(Username).build().perform();
           
        
        //clicking password field
        
        driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
        robot.delay(threeSecs);
        
        //Sending password via excel
        String Password = reader.getCellData("Doctor", "Password", rowNum);
        System.out.println("password is" +Password);
        ac.sendKeys(Password).build().perform();
        
        //clicking login button
        driver.findElement(ByAngular.buttonText("Log In")).click();
        
        robot.delay(sevenSecs);
        
        //I Am Healthcare provider.
        driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-usertype/ion-content/div[2]/div/ion-row/ion-col[2]/div/ion-list/ion-row[1]/ion-col[1]/ion-item/div[1]/ion-radio/button")).click();
        robot.delay(1400);
        
        //Clicking next
        
        WebElement nxt2 =  driver.findElement(ByAngular.buttonText("Next"));
        nxt2.click();	 
        
        //Pop Alert, Cannot change selection in future. Accepting to continue.
         driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ion-alert/div/div[3]/button[2]/span")).click();
         robot.delay(threeSecs);
         
         //FirstName
     	  driver.findElement(ByAngularOptions.xpath("//*[@type='text']")).click();

          //Delete any existing text
          for (int i1=1; i1<20;i1++) {
        	  ac.sendKeys(Keys.BACK_SPACE).build().perform();
          }
	      robot.delay(threeSecs);
	      //sending first name from excel
	      
          String firstname = reader.getCellData("Doctor", "firstName", rowNum);
          System.out.println(firstname);

          ac.sendKeys(firstname).build().perform();
          
          //LastName
          //clicking last name field
	      driver.findElement(ByAngularOptions.xpath("//*[@formcontrolname='lastName']")).click();
          //Clearing any existing text
          for (int i1=1; i1<20;i1++) {
        	  ac.sendKeys(Keys.BACK_SPACE).build().perform();
          }
          robot.delay(threeSecs);
	      //Entering last Name from excel
          String lastName = reader.getCellData("Doctor", "lastName", rowNum);
          System.out.println(lastName);
          ac.sendKeys(lastName).build().perform();
          robot.delay(threeSecs);
          
          /*
          Address
         WebElement Address1= driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-continue-signup > ion-content > div.scroll-content > ion-scroll > div > div > div > ion-row > ion-col:nth-child(2) > div > ion-slides > div > div.swiper-wrapper > ion-slide.signupSlide.swiper-slide.swiper-slide-active > div > ion-card > ion-card-content > ion-toolbar > div.toolbar-content.toolbar-content-md > ion-searchbar > div > input"));
         Address1.click();
         //Entering data from excel
         String Address2enter= reader.getCellData("Doctor", "Address", rowNum);
         System.out.println(Address2enter);
         ac.sendKeys(Address2enter);
         robot.delay(threeSecs);
     */
          
          
          //Clicking next
          driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-continue-signup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button")).click();
		  robot.delay(sevenSecs);
		  //--------------------------------------------------------------------------------------------------------------------------------------------------------
		  //specialization
		  driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-continue-signup/ion-content/div[2]/ion-scroll/div/div/div/ion-row/ion-col[2]/div/ion-slides/div/div[1]/ion-slide[2]/div/ion-card/ion-card-content/form/ion-item/div[1]/div/ion-select/button"));
		  robot.delay(threeSecs);
		  
		  //specilty
		  driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-continue-signup/ion-content/div[2]/ion-scroll/div/div/div/ion-row/ion-col[2]/div/ion-slides/div/div[1]/ion-slide[2]/div/ion-card/ion-card-content/form/ion-item/div[1]/div/ion-select/button")).click();
		  robot.delay(threeSecs);
		  ac.sendKeys(Keys.TAB).build().perform();
		  ac.sendKeys(Keys.TAB).build().perform();
		  ac.sendKeys(Keys.ENTER).build().perform();

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
		  //next
		  driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-continue-signup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button")).click();

          //Accomplishments (Optional) pressing next...
		  driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-continue-signup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button")).click();
		  robot.delay(threeSecs);
		  //Education, optional, pressing next
		  driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-continue-signup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button")).click();

		  //Work Experience, selecting next will save
		  driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-continue-signup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button")).click();

		  
		  
		  

        

	}
	}
//}
