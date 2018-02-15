import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class switchTab
{
	public static void main(String args[]) throws AWTException, InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "Y:\\Selenium\\chromedriver.exe");
		WebDriver driver;
	
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		String Url = "https://www.google.com/";
		
		driver.get(Url);
		Thread.sleep(2000);
		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		System.out.println(" New Tab(s) opened\n");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		
		/*
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		System.out.println("Alt Tab Works");
		Thread.sleep(2000);
		 */
		driver.findElement(By.id("lst-ib")).sendKeys("Yuga", Keys.ENTER);
		Thread.sleep(8000);
		
		driver.quit();
	}
}
