import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Search
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
	public void search()
	{
		driver.findElement(By.id("searchInput")).sendKeys("Selenium (software)");
		driver.findElement(By.xpath("//*[@id=\"search-form\"]/fieldset/button/i")).click();
	}
	
	@Test (priority = 2)
	public void screenshot() throws IOException
	{
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		
		File destination = new File("Y:\\Selenium\\Screenshots\\Screenshot.png");
		FileUtils.copyFile(file, destination);
	}
	
	@AfterTest
	public void close()
	{
		driver.close();
		driver.quit();
	}
	
}
