package sanityTests;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularOptions;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class VideoSignUp {


	public static void main(String[] args) throws AWTException {
		
		//patientSignUp Video Consult
		
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
	          robot.delay(threeSecs);
			  driver.manage().deleteAllCookies();
			  
			  int rowCount = reader.getRowCount("Healable");
			  int rowNum =2;
			  //-------------------------------------------------------------------------------------------------------
			 
			 // for (int rowNum =2; rowNum<=rowCount; rowNum++) {

			  driver.get(URL);
			  ngWebDriver.waitForAngularRequestsToFinish();

			  robot.delay(threeSecs);
	          driver.findElement(ByAngular.buttonText("Login")).click();
	          robot.delay(threeSecs);

	   
	        	  
		      String Username =reader.getCellData("Healable", "Username", rowNum);
			  System.out.println(Username);
			  //Clicking user name element
	          driver.findElement(ByAngularOptions.className("text-input")).click();
	          robot.delay(sevenSecs);
	          ac.sendKeys(Username).build().perform();
	          
	          
	          //clicking password field
	          
	          driver.findElement(ByAngularOptions.xpath("//*[@type='password']")).click();
	          robot.delay(threeSecs);
	          
	          //Sending password via excel
	          String Password = reader.getCellData("Healable", "Password", rowNum);
	          System.out.println("password is" +Password);
	          ac.sendKeys(Password).build().perform();
	          
	          //clicking login button
	          driver.findElement(ByAngular.buttonText("Log In")).click();
	          
	          robot.delay(sevenSecs);
	       
	           // -------------------------------------------------------------------------------------------------------------------------------
	          //I am not a healthCare provider
	          driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-usertype/ion-content/div[2]/div/ion-row/ion-col[2]/div/ion-list/ion-row[2]/ion-col[1]/ion-item/div[1]/ion-radio/button")).click();robot.delay(threeSecs);
	          //click next
	  
	          WebElement nxt2 =  driver.findElement(ByAngular.buttonText("Next"));
	          nxt2.click();	           
	          //Pop Alert, Cannot change selection in future. Accepting to continue.
	           driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ion-alert/div/div[3]/button[2]/span")).click();
	           robot.delay(threeSecs);
	           //--------------------------------------------------------------------------------------------------------------------------------
	
	          
	          // clicking the video consult
	          robot.delay(sevenSecs);
	          driver.findElement(ByAngularOptions.xpath("//*[@value='videoConsult']")).click();
	          robot.delay(threeSecs);
	          //Clicking Next
	      	  
		      WebElement nextButton = driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-videoconsultcheck > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button"));
	           nextButton.click();
	          robot.delay(sevenSecs);
	  
	          //---------------------------------------------------------------------------------------------------------------------------------------------------------
	          
	          //I AM a Patient, Click Next.
		      WebElement nextButton1 = driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-patientsignup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button"));
	           nextButton1.click();robot.delay(sevenSecs);

	          //----------------------------------------------------------------------------------------------------------------------------------
	      	  //Clicking firstName field.
	      	  driver.findElement(ByAngularOptions.xpath("//*[@type='text']")).click();

	          //Delete any existing text
	          for (int i1=1; i1<20;i1++) {
	        	  ac.sendKeys(Keys.BACK_SPACE).build().perform();
	          }
		      robot.delay(threeSecs);
		      //sending first name from excel
		      
	          String firstname = reader.getCellData("Healable", "firstName", rowNum);
	          System.out.println(firstname);

	          ac.sendKeys(firstname).build().perform();

	          //---------------------------------------------------------------------------------------------------------------------------------------------------------

	          robot.delay(threeSecs);
	          //clicking last name field
		      driver.findElement(ByAngularOptions.xpath("//*[@formcontrolname='lastName']")).click();
	          //Clearing any existing text
	          for (int i1=1; i1<20;i1++) {
	        	  ac.sendKeys(Keys.BACK_SPACE).build().perform();
	          }
	          robot.delay(threeSecs);
		      //Entering last Name from excel
	          String lastName = reader.getCellData("Healable", "lastName", rowNum);
	          System.out.println(lastName);
	          ac.sendKeys(lastName).build().perform();
	          robot.delay(threeSecs);
	          
	          
	          //Clicking next
		      WebElement nextButton3 = driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-patientsignup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button"));
    		  nextButton3.click();
    		 //---------------------------------------------------------------------------------------------------------------------------------------------------------- 
    		  
    		  robot.delay(sevenSecs);
    		  //Patient Details
	          driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-patientsignup/ion-content/div[2]/div/ion-slides/div/div[1]/ion-slide[4]/div/form/ion-card/ion-item[1]/div[1]/div/ion-select/button")).click();
	          //Selecting gender
	          driver.findElement(ByAngular.buttonText("Male")).click();
	          ac.sendKeys(Keys.ENTER).build().perform();
	          robot.delay(threeSecs);
	          //Clicking phoneNum
	          driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-patientsignup/ion-content/div[2]/div/ion-slides/div/div[1]/ion-slide[4]/div/form/ion-card/ion-item[2]/div[1]/div/ion-input/input")).click();
	          //Delete any existing text
	          
	          for (int i1=1; i1<15;i1++) {
	        	  ac.sendKeys(Keys.BACK_SPACE).build().perform();
	          }
	          robot.delay(threeSecs);
	          //-------------------------------------------------------------------------------------------------------------------------------------------------------------
	          // enter phone number from excel
	          
	          String numbrString = reader.getCellData("Healable", "phone", rowNum);
	          System.out.println(numbrString);
	          ac.sendKeys(numbrString).build().perform();
	          //----------------------------------------------------------------------------------------------------------------------------------------------------
	          //Enter date of birth
	          driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	          //Clicking DOB Element
	          WebElement DoB = driver.findElement(ByAngularOptions.xpath("/html/body/ion-app/ng-component/ion-nav/page-patientsignup/ion-content/div[2]/div/ion-slides/div/div[1]/ion-slide[4]/div/form/ion-card/ion-item[3]/div[1]/div/ion-datetime/button"));robot.delay(sevenSecs);
	          DoB.click();robot.delay(threeSecs);
	         //-----------------------------------------------------------------------------------------------------------------------------------------------------
	         
	         //month
	         for(int m=1; m<4; m++) {
	         ac.sendKeys(Keys.TAB).build().perform();
	         }
	         ac.sendKeys(Keys.ENTER).build().perform();

	         robot.delay(threeSecs);
	         
	         //day
	         DoB.click();robot.delay(threeSecs);
	         for (int d=1; d<17; d++) {
	         ac.sendKeys(Keys.TAB).build().perform();
	         }
	         robot.delay(threeSecs);
	         ac.sendKeys(Keys.ENTER).build().perform();

	         //year
	         DoB.click();robot.delay(threeSecs);
	         for(int y=1; y<70; y++) {
		         ac.sendKeys(Keys.TAB).build().perform();

	         }
	         robot.delay(threeSecs);
	         ac.sendKeys(Keys.ENTER).build().perform();
	        

	         //----------------------------------------------------------------------------------------------------------------------------------------------------------------
	         
	         robot.delay(threeSecs);
	         //Entering Address Information
	         WebElement Address = driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-patientsignup > ion-content > div.scroll-content > div > ion-slides > div > div.swiper-wrapper > ion-slide.swiper-slide.swiper-slide-active > div > ion-toolbar > div.toolbar-content.toolbar-content-md > ion-searchbar > div > input"));
	         Address.click();
		     for(int x=1;x<55;x++) {
			        ac.sendKeys(Keys.BACK_SPACE).build().perform();

			       }
		     robot.delay(threeSecs);
		     
		     //Entering Address from excel
		     String address = reader.getCellData("Healable", "Address", rowNum);
		     ac.sendKeys(address).build().perform();
		     robot.delay(threeSecs);
		     //------------------------------------------------------------------------------------------------------------------------------------------------------------------
		     //clicking Next
	         WebElement nextButtonDOB = driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-patientsignup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button"));
	         nextButtonDOB.click();
	          
	         //Clicking Next on About Me (Optional Section)
	         //------------------------------------------------------------------------------------------------------------------------------------------------------------------
	         WebElement nextButtonAboutMe = driver.findElement(ByAngularOptions.cssSelector("body > ion-app > ng-component > ion-nav > page-patientsignup > ion-header > ion-navbar > div.toolbar-content.toolbar-content-md > ion-grid > ion-row > ion-col.glb-display-block--positioning.col > ion-buttons > button"));
	         nextButtonAboutMe.click();
	          robot.delay(threeSecs);
	          
	         // Saving Profile
	         WebElement save = driver.findElement(ByAngular.buttonText("Save"));
	         save.click();

	         //------------------------------------------------------------------------------------------------------------------------------------------------------------------
	         
		    
		    }	    
		}
	//}


