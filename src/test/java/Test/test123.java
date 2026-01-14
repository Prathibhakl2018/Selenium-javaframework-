package Test;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class test123 {

	
public static void main(String[] args) throws InterruptedException {
	try {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://b2b.chokhiyatra.com/agent-registration");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='react-select-2-input']")).click();
List<WebElement> options = driver.findElements(By.xpath("//div[@role='option']"));
	options.get(0).click();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("Prathibha");
 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("K L");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='contact_email']")).sendKeys("prathibhakl2018@gmail.com");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("8431769778");driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));	driver.findElement(By.xpath("//input[@id='react-select-3-input']")).click();
	
	List<WebElement> countrydropdown = driver.findElements(By.xpath("//div[contains(@class,'justify-content-between')]"));
	countrydropdown.get(0).click();
	
	
	driver.findElement(By.id("isPhoneSelectedAsUserId")).click();

	if (driver.findElement(By.id("isPhoneSelectedAsUserId")).isSelected())
	{

		String mobileno = driver.findElement(By.id("mobile_number")).getAttribute("value");
System.out.println("mobileno");

String userID = driver.findElement(By.id("userid")).getAttribute("value");
System.out.println(userID);
if(mobileno.equals(userID))
{
	System.out.println("mobile no and UserID matched");
	}

	}
	
	else if (driver.findElement(By.id("isEmailSelectedAsUserId")).isSelected()) {
		String email = driver.findElement(By.id("contact_email")).getAttribute("value");
		System.out.println("email");

		String userID = driver.findElement(By.id("userid")).getAttribute("value");
		System.out.println(userID);
		if(email.equals(userID))
		{
			System.out.println("email  and UserID matched");
			}
	}
		Thread.sleep(1000);

	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='company']")).sendKeys("Trpgain");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='contact_number']")).sendKeys("8431768775");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("davangere");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

	//driver.findElement(By.xpath("//input[@id='react-select-6-input']")).click();
//	
//	List<WebElement> city = driver.findElements(By.xpath("//div[@role='option']"));
//	city.get(5).click();
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	wait.until(ExpectedConditions.presenceOfElementLocated(
//	        By.xpath("//div[@role='option']")
//	));
//
//	driver.findElement(
//	        By.xpath("//div[@role='option' and text()='Bangalore']")
//	).click();
	driver.findElement(By.xpath("//input[@id='react-select-7-input']")).click();
	List<WebElement> state= driver.findElements(By.xpath("//div[@role='option']"));
	state.get(17).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='pincode']")).sendKeys("577002");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	
	driver.findElement(By.xpath("//input[@id='name_pancard']")).sendKeys("Yadav");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
    driver.findElement(By.xpath("//input[@id='pan_number']")).sendKeys("BKIPL2992A");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

	driver.findElement(By.xpath("//input[@id='upload_pan']"))
    .sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");

//wait for upload to complete
Thread.sleep(3000);
//wait after PAN upload
Thread.sleep(3000);

JavascriptExecutor js = (JavascriptExecutor) driver;

//scroll to passport section
js.executeScript("window.scrollBy(0, 600);");
Thread.sleep(1000);

//select passport radio FIRST
WebElement passport1 = driver.findElement(By.id("passport"));
js.executeScript("arguments[0].click();", passport1);
Thread.sleep(1000);

//verify selection
System.out.println("Passport selected: " + passport1.isSelected());

//now enter passport number
WebElement passportNumber = driver.findElement(By.id("passport_number"));
js.executeScript("arguments[0].scrollIntoView(true);", passportNumber);
passportNumber.sendKeys("A1234567");

//upload passport files
driver.findElement(By.name("upload_passport_front"))
   .sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");

driver.findElement(By.name("upload_passport_back"))
   .sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");

	driver.findElement(By.xpath("//input[@id='aadhar']")).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@id='aadhaar_number']")).sendKeys("765478675654");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//input[@name='upload_aadhar']")).sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	driver.findElement(By.xpath("//input[@id='acceptTerms']")).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	driver.findElement(By.xpath("//div[@class='text-center mt-4 mb-4']")).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
	

	}
	catch(Exception e)
	{System.out.println("Exception :"+e.getMessage());}
	
}
}



