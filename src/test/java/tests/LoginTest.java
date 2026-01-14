package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.travelsupermall.com/login");
        Thread.sleep(1000);// replace with your URL
    }

    @Test
    public void validLoginTest() {
        driver.findElement(By.id("agent_userid")).sendKeys("rashmitha@tripgain.com");
        driver.findElement(By.id("agent_password")).sendKeys("Rashmi@#$333");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
