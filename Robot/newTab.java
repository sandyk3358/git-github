import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class newTab {
	public static void main(String args[]) throws AWTException, InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "Y:\\Selenium\\chromedriver.exe");
		WebDriver driver;
	
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		String Url = "https://www.google.com/";
		//driver.navigate().to(Url);
		driver.get(Url);
		Thread.sleep(2000);
		Robot robot = new Robot();
		int i, no = 2;
		//no is the number of new tabs to be opened
		for (i = 1; i <= no; i++)
		{
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			System.out.println(i + " New Tab(s) opened\n");
			
			/* For opening same Url in all the tabs
			Set<String> tabs = (Set<String>)driver.getWindowHandles(); 
			for(String tab : tabs)
			{
				driver.switchTo().window(tab);
				System.out.println(driver.getTitle());
				//if(driver.getTitle().contains("New Tab"))
					driver.get("http://www.facebook.com/");
			}
			 */
			
			/*
			 String Base = driver.getWindowHandle();
                Set<String> tabs = (Set<String>)driver.getWindowHandles();
                
                tabs.remove(Base);
                driver.switchTo().window(tabs.toArray()[0].toString());
                driver.get("http://www.facebook.com/");
			 */
		}
		
		driver.quit();
	}
	
}