package Test;


	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class  Register {

	    public static void main(String[] args) {

	        WebDriver driver = null;

	        try {
	            driver = new ChromeDriver();
	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	            driver.get("https://b2b.chokhiyatra.com/agent-registration");

	            /* =======================
	               BASIC DETAILS
	               ======================= */

	            driver.findElement(By.id("react-select-2-input"))
	                  .sendKeys("India", Keys.ENTER);

	            driver.findElement(By.id("first_name"))
	                  .sendKeys("Prathibha");

	            driver.findElement(By.id("last_name"))
	                  .sendKeys("K L");

	            driver.findElement(By.id("contact_email"))
	                  .sendKeys("prathibhakl2018@gmail.com");

	            driver.findElement(By.id("mobile_number"))
	                  .sendKeys("8431760778");

	            driver.findElement(By.id("react-select-3-input"))
	                  .sendKeys("India", Keys.ENTER);

	            /* =======================
	               USER ID RADIO BUTTON
	               ======================= */

	            WebElement phoneRadio = driver.findElement(By.id("isPhoneSelectedAsUserId"));
	            phoneRadio.click();

	            Thread.sleep(1000); // allow userid field to auto-update

	            if (phoneRadio.isSelected()) {

	                String mobileNo = driver.findElement(By.id("mobile_number"))
	                                         .getAttribute("value");

	                String userID = driver.findElement(By.id("userid"))
	                                       .getAttribute("value");

	                System.out.println("Mobile Number : " + mobileNo);
	                System.out.println("User ID       : " + userID);

	                if (mobileNo.equals(userID)) {
	                    System.out.println("✅ Mobile number and UserID matched");
	                }

	            } else if (driver.findElement(By.id("isEmailSelectedAsUserId")).isSelected()) {

	                String email = driver.findElement(By.id("contact_email"))
	                                      .getAttribute("value");

	                String userID = driver.findElement(By.id("userid"))
	                                      .getAttribute("value");

	                System.out.println("Email  : " + email);
	                System.out.println("UserID : " + userID);

	                if (email.equals(userID)) {
	                    System.out.println(" Email and UserID matched");
	                }
	            }

	            /* =======================
	               COMPANY DETAILS
	               ======================= */

	            driver.findElement(By.id("company"))
	                  .sendKeys("Trpgain");

	            driver.findElement(By.id("contact_number"))
	                  .sendKeys("8431768775");

	            driver.findElement(By.id("address1"))
	                  .sendKeys("Davangere");

	            driver.findElement(By.id("react-select-6-input"))
	                  .sendKeys("Davangere", Keys.ENTER);

	            driver.findElement(By.id("react-select-7-input"))
	                  .sendKeys("Karnataka", Keys.ENTER);

	            driver.findElement(By.id("pincode"))
	                  .sendKeys("577002");

	            /* =======================
	               PAN DETAILS
	               ======================= */

	            driver.findElement(By.id("name_pancard"))
	                  .sendKeys("Yadav");

	            driver.findElement(By.id("pan_number"))
	                  .sendKeys("BKIPL2992A");

	            driver.findElement(By.id("upload_pan"))
	                  .sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");

	            Thread.sleep(2000);

	            /* =======================
	               PASSPORT DETAILS
	               ======================= */

	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("window.scrollBy(0,600)");

	            WebElement passportRadio = driver.findElement(By.id("passport"));
	            js.executeScript("arguments[0].click();", passportRadio);

	            System.out.println("Passport selected : " + passportRadio.isSelected());

	            driver.findElement(By.id("passport_number"))
	                  .sendKeys("A1234567");

	            driver.findElement(By.name("upload_passport_front"))
	                  .sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");

	            driver.findElement(By.name("upload_passport_back"))
	                  .sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");

	            /* =======================
	               AADHAAR DETAILS
	               ======================= */

	            driver.findElement(By.id("aadhar")).click();

	            driver.findElement(By.id("aadhaar_number"))
	                  .sendKeys("765478675654");

	            driver.findElement(By.name("upload_aadhar"))
	                  .sendKeys("C:\\Users\\NEWUSER\\Downloads\\ai-generated-8865944_1280.jpg");

	            /* =======================
	               SUBMIT
	               ======================= */

	            driver.findElement(By.id("acceptTerms")).click();

	            driver.findElement(By.xpath("//div[@class='text-center mt-4 mb-4']"))
	                  .click();

	            System.out.println(" Registration form submitted successfully");

	        } catch (Exception e) {
	            System.out.println("Exception occurred: " + e.getMessage());
	        }

	        // Uncomment if you want to close browser
	        // finally {
	        //     if (driver != null) {
	        //         driver.quit();
	        //     }
	        // }
	    }
	}




