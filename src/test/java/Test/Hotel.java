package Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hotel {

public static void main(String[] args) throws InterruptedException{
	try {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		// TODO Auto-generated method stub
     driver.get("https://b2b.chokhiyatra.com/login");
     Thread.sleep(1000);
     driver.findElement(By.xpath("//input[@id='agent_userid']")).sendKeys("Krishn@iweensoft.com");
     Thread.sleep(1000);
     driver.findElement(By.xpath("//input[@id='agent_password']")).sendKeys("Thanks@123");
     Thread.sleep(1000);
     driver.findElement(By.xpath("//button[@type='submit']")).click();
     Thread.sleep(1000);
     driver.findElement(By.xpath("//div[@class='service-tab ']")).click();
     //     driver.findElement(By.xpath("(//div[(@class='service-tab ')]")).click();

     Thread.sleep(1000);
     driver.findElement(By.xpath("//input[@id='react-select-5-input']")).sendKeys("DEL");
     Thread.sleep(2000);
    // List<WebElement> listbox = driver.findElements(By.xpath("//div[@role='listbox']"));
 	//listbox.get(0).click();
 	List<WebElement> listbox = driver.findElements(By.xpath("//div[@class='d-flex']//span"));
 	System.out.println(listbox.size());
 	
 	listbox.get(0).click();

	/*
	 * driver.findElement(By.
	 * xpath("//div[@class='location-focused location-option']")).click();
	 * Thread.sleep(1000); driver.findElement(By.
	 * xpath("//div[@class='room-select d-flex justify-content-between align-items-center flex-1']"
	 * )).click(); Thread.sleep(1000);
	 */
 	driver.findElement(By.xpath("//button[@class='app-btn app-btn-primary app-btn-medium ']")).click();
    Thread.sleep(1000);
    driver.quit();
    
     

}
	catch(Exception e)
	{System.out.println("Exception :"+e.getMessage());}
}
}
