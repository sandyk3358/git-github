import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchATrip {
	public static void main(String args[]) {
		
		//IE Driver
		System.setProperty("webdriver.ie.driver", "Y:\\Selenium\\IEDriverServer.exe");
		
		//Creating driver object
		WebDriver driver = new InternetExplorerDriver();
		
		//To maximize browser window
		driver.manage().window().maximize();
		
		//Open webpage
		driver.get("https://www.greyhound.com/");
		
		//Scroll
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)", "");
		
		//Select an origin and destination
		driver.findElement(By.id("fromLocation")).sendKeys("New York, NY");
		driver.findElement(By.id("toLocation")).sendKeys("Boston, MA");
		
		// To input a date using 'sendKeys'
		//Choose a Start Date
		driver.findElement(By.id("datepicker-from")).submit();
		driver.findElement(By.id("datepicker-from")).sendKeys("10/22/2017");
				
		//Choose an End Date
		driver.findElement(By.id("datepicker-to")).submit();
		driver.findElement(By.id("datepicker-to")).sendKeys("10/27/2017");
		//driver.findElement(By.id("datepicker-to")).sendKeys(Keys.ENTER);
		
		//Explicit Wait
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("table.ui-datepicker-calendar")));
		
		//Click Search button
		driver.findElement(By.id("fare-search-btn")).submit();
		
	}

}
