package sanityTests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class practicess {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nabil\\\\OneDrive\\Documents\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.google.com/");
		WebElement logo = driver.findElement(By.id("hplogo"));
		assertEquals(logo, logo);
		driver.get("https://www.amazon.com/");
	}

}
