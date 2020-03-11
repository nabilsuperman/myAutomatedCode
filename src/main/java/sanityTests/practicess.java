package sanityTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class practicess {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\OneDrive\\Documents\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions ac = new Actions(driver);
		driver.get("https://www.amazon.com/");
		String search = driver.findElement(By.xpath("(//*[@class='nav-line-1'])[2]")).getText();
		System.out.println(search);
		Assert.assertTrue(search.contains("Hello, Sign in"));
		System.out.println("done");
        
		
	}

		
	}


	

		

		
	



