package CTSChallenge.Challenge18;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddItemsToCartTest {
	WebDriver driver;
	WebElement userNameEl;
	WebElement passwordEl;
	WebElement submitBtn;
	WebElement registerBtn;
	
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
		driver.findElement(By.xpath("//a[@class='top-nav-tab-name logout']")).click();
		userNameEl = driver.findElement(By.xpath("//input[@id='UserName']"));
		passwordEl = driver.findElement(By.xpath("//input[@id='UserPwd']"));
		submitBtn = driver.findElement(By.xpath("//input[@class='btnSubmit']"));
		
		// login to newegg.com
		userNameEl.sendKeys("ctschallenge17@gmail.com");
		passwordEl.sendKeys("Challenge2018");
		submitBtn.click();
	}


	@DataProvider
	public Object[][] cartData(){
		Object[][] data = {
				{"Micro SD Card", "SanDisk 256GB Ultra", 149.95},
				{"PS4 games","UUNCHARTED: The Lost Legacy", 40.00},
				{"Smart Thermostats","INSTEON Thermostat (2441TH)", 99.99}
		};
		return data;	
	}
	
	@Test(dataProvider="cartData")
	public void addItemToCartTest(String searchName, String itemName, Double price) throws InterruptedException {

		
		// search the item
		driver.findElement(By.xpath("//input[@id='haQuickSearchBox']")).sendKeys(searchName);
		
		// Click Search button
		driver.findElement(By.xpath("//button[@class='btn btn-primary btn-mini search-bar-btn']")).click();
		
		// Find the correct item
		driver.findElement(By.xpath("//img[contains(@title, '"+itemName+"')]")).click();
		
		// Get the actual price
		String itemPrice  = driver.findElement(By.xpath("//*[@id='landingpage-price']/div/div/ul/li[3]/strong")).getText();
		String fraction = driver.findElement(By.xpath("//*[@id='landingpage-price']/div/div/ul/li[3]/sup")).getText();
		Double totalPrice = Double.parseDouble((itemPrice + fraction));
		
		// Click the add to cart button
		driver.findElement(By.xpath("//*[@id='landingpage-cart']/div/div[2]/button")).click();
		// Compare the price now
		assertThat(price, equalTo(totalPrice));
	}

	@Test
	public void cartTest() {
		// click the view cart button
		driver.findElement(By.xpath("//a[@title='Shopping Cart with Items']")).click();
		// remove an item
		driver.findElement(By.xpath("//input[@title='SanDisk SDSQUAR-256G-GN6MA']")).click();
		// remove the second item
		driver.findElement(By.xpath("//input[@title='PlayStation 711719510475']")).click();
		
		// Click the remove selected
		driver.findElement(By.xpath("//a[@id='removeFromCart']")).click();
		// Get the total price
		double grandTotal = Double.parseDouble(driver.findElement(By.xpath("//*[@id='bodyArea']/div[10]/form[1]/table[6]/tbody/tr[3]/td/span[2]")).getText());
		// validate the final price
		assertThat(99.99, equalTo(grandTotal));
		
	}
	
	@Test
	public void checkoutTest() {
		// Click the secure Checkout
		driver.findElement(By.xpath("//a[@title='Secure Checkout']")).click();
		
		// Click on the continue billing
		driver.findElement(By.xpath("//a[@title='Icon button']")).click();
		
		// Grab the error message
		String errorMessage = driver.findElement(By.xpath("//input[@id='SAddress1']")).getText();
		// Check the validation message
		assertThat(errorMessage, equalTo("This field is required."));
		
	}
	
	@AfterMethod
	public void finalize() throws InterruptedException {
		driver.quit();
	}
}
