package CTSChallenge.Challenge18;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NeweggAutomationTest {
	WebDriver driver;
	WebElement userNameEl;
	WebElement passwordEl;
	WebElement loginBtn;
	WebElement registerBtn;
	
	boolean loggedIn;
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mdrah\\Desktop\\Selenium Executables\\Drivers\\chromedriver_win32\\chromedriver.exe");
	}
	
	@BeforeMethod
	public void initialize() {
		// Create a new instance of the Google Chrome driver
		driver = new ChromeDriver();
		// Navigate to the newegg page
		driver.navigate().to("https://www.newegg.com/");
		// Implicitly Wait 10 seconds to load the page 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Set the window width to maximum 
		driver.manage().window().maximize();
	}

	@Test
	public void simpleTest() {
		driver.findElement(By.xpath("//a[@class='top-nav-tab-name logout']")).click();
		driver.findElement(By.xpath("//a[@title='Forgot your password?']")).click();
		driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("ctschallenge17@gmail.com");

		
		// grab the image source
		String img = driver.findElement(By.xpath("//img[@id='imgValidCodeverification1']")).getAttribute("src");
		// image parsing
		Scanner input = new Scanner(System.in);
		String captcha = input.next();
		input.close();

		driver.findElement(By.xpath("//input[@id='txtValidateCode']")).sendKeys(captcha);
		// CLICK THE SUBMIT BUTTON
		driver.findElement(By.xpath("//input[@class='btnSubmit']")).click();
	}
	
}
