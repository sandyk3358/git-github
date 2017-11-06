import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetText
{
	WebDriver driver;
	@BeforeTest
	public void open()
	{
		System.setProperty("webdriver.chrome.driver", "Y:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.wikipedia.org/");
	}
	
	@Test (priority = 1)
	public void search() throws InterruptedException
	{
		driver.findElement(By.id("searchInput")).sendKeys("WebDriver");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		Thread.sleep(2000);
	}
	
	@Test (priority = 2)
	public void getTitle() throws InterruptedException
	{
		
		
		JavascriptExecutor jse2= (JavascriptExecutor) driver;
        jse2.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
        
        //((JavascriptExecutor) driver).executeScript("document.getElementById('firstHeading').scrollIntoView(true);");
        
        Thread.sleep(2000);
        String title = driver.findElement(By.cssSelector("#firstHeading")).getText();
        
		if (title.contentEquals("Selenium (software)"))
			System.out.println("Test Passed!");
		else
			System.out.println("Test Failed!");
	}
	
	@Test (priority = 3)
	public void getLink() throws AWTException
	{
		String link = driver.findElement(By.xpath("//*[contains(@href,'github.com')]")).getText();
		System.out.println(link);
	
		Robot url = new Robot();
		url.keyPress(KeyEvent.VK_CONTROL);
		url.keyPress(KeyEvent.VK_T);
		url.keyRelease(KeyEvent.VK_T);
		url.keyRelease(KeyEvent.VK_CONTROL);
		
		//Set<String> tab = (Set<String>)driver.getWindowHandles();
		ArrayList<String> tab = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tab.get(1));
	    driver.get("https://" +link);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    /*
	    for(int i = 0; i < 1000000000; i++)
	    {
	        // wait
	    }
	    */
	    driver.switchTo().window(tab.get(0));
	}
	
	@AfterTest
	public void close()
	{
		driver.close();
		driver.quit();
	}
	
}
