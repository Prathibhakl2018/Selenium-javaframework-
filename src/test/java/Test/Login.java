package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

	public static void main(String[] args) throws InterruptedException{
		
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
     driver.quit();
     
	}

}
