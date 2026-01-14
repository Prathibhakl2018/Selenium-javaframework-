package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class forgotpassword {

	public static void main(String[] args) throws InterruptedException{
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		// TODO Auto-generated method stub
     driver.get("https://b2b.chokhiyatra.com/login");
     Thread.sleep(1000);
     driver.findElement(By.xpath("//a[@class='fw-500 fs-14 d-inline app-link-primary']")).click();
     Thread.sleep(1000);
     driver.findElement(By.xpath("//input[@id='userId']")).sendKeys("Krishn@iweensoft.com");
     Thread.sleep(1000);
     driver.findElement(By.xpath("//button[@type='submit']")).click();
     
     
	}
}