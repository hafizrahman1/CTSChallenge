package CTSChallenge.Challenge18;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailLoginTest {
	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mdrah\\Desktop\\Selenium Executables\\Drivers\\chromedriver_win32\\chromedriver.exe");
	}
	
	@BeforeMethod
	public void initialize() {
		// Create a new instance of the Google Chrome driver
		driver = new ChromeDriver();
		// Navigate to the gmail login page
		driver.navigate().to("https://accounts.google.com/signin");
		// Implicitly Wait 10 seconds to load the page 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Set the window width to maximum 
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void loginTest() throws InterruptedException {
		// Enter the user name as email address
		driver.findElement(By.id("identifierId")).sendKeys("ctschallenge17@gmail.com"); 
		// Click the next button
        driver.findElement(By.xpath("//*[@id='identifierNext']/content/span")).click();
        Thread.sleep(1000);
        // Clear the password field
        driver.findElement(By.xpath("//input[@name='password']")).clear();
        // Enter the password
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Challenge2018");
        
        // Click the next button again
        driver.findElement(By.xpath("//*[@id='passwordNext']/content/span")).click();
        Thread.sleep(1000);
        
        // Verify the successfully logged in page
        assertThat("My Account", equalTo(driver.getTitle()));
        
	}
	
	@AfterMethod
	public void finalize() throws InterruptedException {
		driver.quit();
	}
}
