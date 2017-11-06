import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Bharadwaj
{
	
	WebDriver driver;
	@BeforeTest
	public void open()
	{
		System.setProperty("webdriver.chrome.driver", "Y:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://bharadwajpisupati-dev-ed.my.salesforce.com/");
	}
	
	@DataProvider(name = "Create-Consultant")
	public static String[][] createConsultant()
	{
		return new String[][] { {"Yuga", 			"Challagolla", 	"7074 Majors Rd", 	"GA", 	"30040",	"Computer Science", "Binghamton University"},
								{"Yuga Mounika", 	"", 			"", 				"VI", 	"3004", 	"Electrical", 		"BUniversity"},
								{"$Yuga", 			"C", 			"7074 MR", 			"AB", 	"01854", 	"Mechanical", 		"BU"},
								{"1234", 			"Ch", 			"Majors Rd", 		"ABC", 	"123", 		"CIvil", 			"123"},
								{"1234 Yuga", 		"Challagolla", 	"$ Majors Rd", 		"GA", 	"ABC", 		"", 		"$BU"} };
	}
	
	@Test (priority = 1)
	public void login() throws InterruptedException
	{
		// Login Credentials
		driver.findElement(By.id("username")).sendKeys("yugamounika.ch@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Prolific@123");
		
		// Remember Me
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		Thread.sleep(1000);
		
		// Login
		driver.findElement(By.id("Login")).click();
		Thread.sleep(1000);
		
		// Opt 'Remind Me Later' for Mobile registration
		driver.findElement(By.xpath("//a[contains(text(),'Remind Me Later')]")).click();
	}
	
	@Test (priority = 2)
	public void new_consultant()
	{
		// Click on 'Consultancy' tab
		driver.findElement(By.xpath("//a[contains(text(),'Consultancy')]")).click();
		
	}
	
	@Test (priority = 3, dataProvider = "Create-Consultant")
	public void consultant_form(String fn, String ln, String A1, String state, String zip, String major, String university) throws InterruptedException
	{
		// Create New Consultant
		driver.findElement(By.name("new")).click();
				
		driver.findElement(By.id("j_id0:j_id1:j_id2:j_id5:First_Name__c")).sendKeys(fn);
		driver.findElement(By.id("j_id0:j_id1:j_id2:j_id5:Last_Name__c")).sendKeys(ln);
		driver.findElement(By.id("j_id0:j_id1:j_id2:j_id5:Street_1__c")).sendKeys(A1);
		driver.findElement(By.id("j_id0:j_id1:j_id2:j_id5:State__c")).sendKeys(state);
		driver.findElement(By.id("j_id0:j_id1:j_id2:j_id5:Zip_Code__c")).sendKeys(zip);
		
		Select dropdown = new Select(driver.findElement(By.id("j_id0:j_id1:j_id2:j_id6:Master_s_Specialization__c")));
		dropdown.selectByValue(major);
		//driver.findElement(By.xpath("")).sendKeys(major);
		driver.findElement(By.id("j_id0:j_id1:j_id2:j_id6:University__c")).sendKeys(university);
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(3000);
		driver.navigate().to("https://bharadwajpisupati-dev-ed.my.salesforce.com/a01/o");
	}
	
	@Test (priority = 4)
	public void edit_consultant() throws InterruptedException
	{
		driver.navigate().to("https://bharadwajpisupati-dev-ed.my.salesforce.com/a01/o");
		driver.findElement(By.xpath("//span[contains(text(),'A-0033')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("edit")).click();
		driver.findElement(By.name("save")).click();
	}
	
	@Test (priority = 5)
	public void delete_consultant() throws InterruptedException
	{
		driver.navigate().to("https://bharadwajpisupati-dev-ed.my.salesforce.com/a01/o");
		driver.findElement(By.xpath("//span[contains(text(),'A-0033')]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("del")).click();
		Thread.sleep(2000);
		// Confirm delete with Alert
		driver.switchTo().alert().accept();
	}
	
	@AfterTest
	public void close()
	{
		driver.close();
		driver.quit();
	}
	
}
