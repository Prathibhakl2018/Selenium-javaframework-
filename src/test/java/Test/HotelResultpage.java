package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class HotelResultpage {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {
        try {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            driver.manage().window().maximize();
            driver.get("https://www.travelsupermall.com/login");

            // ================= LOGIN =================
            wait.until(ExpectedConditions.elementToBeClickable(By.id("agent_userid")))
                    .sendKeys("rashmitha@tripgain.com");
            Thread.sleep(1000);

            driver.findElement(By.id("agent_password"))
                    .sendKeys("Rashmi@#$333");

            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(3000);

            // Close popup if present
            driver.findElement(By.xpath("//button[normalize-space()='Close']")).click();
            Thread.sleep(3000);

            // ================= HOTEL TAB =================
            driver.findElement(By.xpath("//div[contains(@class,'service-tab')][2]")).click();
            Thread.sleep(3000);

            // ================= CITY SELECTION =================
            wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-5-input")))
                    .sendKeys("DELHI");

//            List<WebElement> cityList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                    By.xpath("//div[@class='d-flex']//span")));
//            cityList.get(0).click();
            List<WebElement> cityList = wait.until(
                    ExpectedConditions.numberOfElementsToBeMoreThan(
                            By.xpath("//div[@class='d-flex']//span"), 0));

            cityList.get(0).click();

            // ================= DATE SELECTION =================
            selectDate(1, "30", "January 2026"); // Check-in
            selectDate(2, "31", "January 2026"); // Check-out

            // ================= SEARCH =================
            Thread.sleep(2000);
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Search Hotels')]")));
            searchBtn.click();

            Thread.sleep(5000);
            try {
                WebElement hotelResult =
                        wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[contains(@class,'hotels-found-text')]")
                        ));

                String text = hotelResult.getText().trim();

                if (!text.isEmpty()) {
                    System.out.println(text);   // e.g. "2958 Hotels Found"
                } else {
                    System.out.println("No Hotels Found");
                }

            } catch (Exception e) {
                System.out.println("No Hotels Found");
            }
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@class='css-sta4mc-control'])[3]")).click();

            WebElement hotelOption = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='option']")));
            hotelOption.click();

            driver.findElement(By.xpath("(//div[@class='css-19bb58m'])[5]")).click();

            WebElement addressOption = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[@role='option']")));
            addressOption.click();
         	
        	
        	
            
        	// driver.findElement(By.xpath("//button[text( )='Select Room']")).click();
         // Open Map View
            Thread.sleep(2000);
            
         WebElement Map=  driver.findElement(By.xpath("//span[contains(text(),'MAP VIEW')]"));
           Map.click();
//            WebElement mapView = driver.findElement(By.xpath("//span[contains(text(),'MAP VIEW')]"));
//
//            
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].click();", mapView);
            Thread.sleep(3000);
            // Verify map visible
            boolean mapVisible;
            try {
             mapVisible = driver.findElement(
                By.xpath("//div[contains(@class,'map')]")
            ).isDisplayed();
            }
            catch(Exception e)
            {
            	  mapVisible = driver.findElement(
                         By.xpath("//div[contains(@class,'map')]")
                     ).isDisplayed();
            }

            System.out.println("Map visible: " + mapVisible);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[contains(text(),'GRID VIEW')]")).click();
            List<WebElement> netFareAmount = driver.findElements(
            	    By.xpath("//*[name()='svg'][.//*[name()='title' and text()='Net Fare']]")
            	);

            	if (!netFareAmount.isEmpty() && netFareAmount.get(0).isDisplayed()) {
            	    System.out.println("Net Fare element is visible");
            	    // Click if needed
            	    netFareAmount.get(0).click();
            	} else {
            	    System.out.println("Net Fare element is hidden or not found");
            	}
            	driver.findElement(By.xpath("//button[text()='Shortlist'][1]")).click();
            	Thread.sleep(2000);
            	// Hotel Name
            	String hotelName = driver.findElement(By.xpath("//span[@class='hotel-card__body__header_hotel-name'][1]")).getText();
            	System.out.println("Hotel Name: " + hotelName);
            	Thread.sleep(1000);
            	// Hotel Address (adjust the XPath according to actual HTML)
            	String hotelAddress = driver.findElement(By.xpath("//span[@class='hotel-card__body__header_hotel-location'][1]")).getText();
            	System.out.println("Hotel Address: " + hotelAddress);
//            	WebElement slider = driver.findElement(By.xpath("//span[contains(@class,'slider-handle')]"));
//
//            	Actions actions = new Actions(driver);
//            	actions.dragAndDropBy(slider, 100, 0).perform();


            	// Supplier
            	String supplier = driver.findElement(By.xpath("//span[@class='fs-12 hotel-card__body__header_hotel-suppliername'][1]")).getText();
            	System.out.println("Supplier: " + supplier);
            	 String parentWindow = driver.getWindowHandle();
            	Thread.sleep(3000);
            	try {
           driver.findElement(By.xpath("//button[@class='app-btn-transparent '][1]")).click();
           driver.findElement(By.xpath("//input[@name='whatsappNumber']")).sendKeys("8431657889");             
            driver.findElement(By.xpath("//button[text()='Send WhatsApp']")).click();
            	}
            	catch(Exception e)
            	{
            		System.out.println("stale "+e.getMessage());
            		
            		  driver.findElement(By.xpath("//button[@class='app-btn-transparent '][1]")).click();
                      driver.findElement(By.xpath("//input[@name='whatsappNumber']")).sendKeys("8431657889");             
                       driver.findElement(By.xpath("//button[text()='Send WhatsApp']")).click();
            	}
            	// Store the current window handle
         // Store parent window
          //  String parentWindow = driver.getWindowHandle();
            Thread.sleep(3000);

            // Click Send WhatsApp
          //  driver.findElement(By.xpath("//button[text()='Send WhatsApp']")).click();
            System.out.println("Handles count: " + driver.getWindowHandles().size());
            System.out.println(driver.getWindowHandles());

            // Wait until new tab opens
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Get all windows
            Set<String> windows = driver.getWindowHandles();
            String whatsappWindow = null;

            for (String win : windows) {
            	System.out.println("win"+win);
                if (!win.equals(parentWindow)) {
                    whatsappWindow = win;
                    break;
                }
            }

            // Switch to WhatsApp tab
            driver.switchTo().window(whatsappWindow);

            // Verify
            System.out.println("WhatsApp URL: " + driver.getCurrentUrl());

            // Close ONLY WhatsApp tab
            driver.close();

            // Switch back to parent window
            driver.switchTo().window(parentWindow);

//            	String hotelName = driver.findElement(By.xpath("//span[@class='hotel-card__body__header_hotel-name'][1]")).getText();
//            	System.out.println(hotelName);
//            	String hoteladdress = driver.findElement(By.xpath("String hotelName = driver.findElement(By.xpath(\"//span[@class='hotel-card__body__header_hotel-name'][1]\")).getText();\r\n"
//            			+ "            	System.out.println(hotelName);")).getText();
//            	System.out.println(hoteladdress);
//            	String supplier = driver.findElement(By.xpath("//span[@class='fs-12 hotel-card__body__header_hotel-suppliername'][1]")).getText();
//            	
//            	System.out.println(supplier);
//            	 driver.findElement(By.xpath("//button[text( )='Select Room']")).click();

            // ================= APPLY FILTER =================
            Thread.sleep(2000);
//            	driver.findElement(By.xpath("//button[text( )='Select Room']")).click();
//            Thread.sleep(2000);
         // Store parent window
            String parentWindow1 = driver.getWindowHandle();

            // Click Select Room FIRST
            List<WebElement> selectRooms =
                    driver.findElements(By.xpath("//button[normalize-space()='Select Room']"));

            for (WebElement btn : selectRooms) {
                if (btn.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                    break;
                }
            }

            // Wait for new tab
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Switch to new tab
            for (String win : driver.getWindowHandles()) {
                if (!win.equals(parentWindow1)) {
                    driver.switchTo().window(win);
                    break;
                }
            }

            System.out.println("✅ In booking tab");

            // Wait until Book Now EXISTS in NEW TAB
            WebElement bookNowBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[normalize-space()='Book Now']")));

            // Click Book Now
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", bookNowBtn);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", bookNowBtn);

            System.out.println("✅ Book Now clicked successfully");

         // Switch iframe if present
            System.out.println("Windows count: " + driver.getWindowHandles().size());
            System.out.println("Current title: " + driver.getTitle());
         Thread.sleep(2000);
         // Click Book Now again
//         WebElement bookNowFinal = wait.until(ExpectedConditions.presenceOfElementLocated(
//                 By.xpath("//div[normalize-space()='Book Now']")));
      // Switch iframe if exists
      // Switch to first iframe if present
         List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
         if (!iframes.isEmpty()) {
             driver.switchTo().frame(iframes.get(0));
         }

         // Wait for email input and scroll to it
         WebElement email = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']"))
         );

         ((JavascriptExecutor) driver)
                 .executeScript("arguments[0].scrollIntoView({block:'center'});", email);
         email.sendKeys("test@gmail.com");
         Thread.sleep(1000);

         // Scroll to the end of the page
         ((JavascriptExecutor) driver)
                 .executeScript("window.scrollTo(0, document.body.scrollHeight);");
         Thread.sleep(1000); // optional wait to see scrolling

         WebElement phone=driver.findElement(By.xpath("//input[@name='phoneNumber']"));
         phone.sendKeys("8907654789");
         Thread.sleep(1000);
//         WebElement terms1=driver.findElement(By.xpath("(//button[@type=\"button\"])[2]"));
//         terms1.click();
//         Thread.sleep(1000);
         
         WebElement salutation = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("(//input[@role='combobox'])[2]")));
        	salutation.click();
        	salutation.sendKeys("Mr");
        	salutation.sendKeys(Keys.ENTER);

        		 
         Thread.sleep(2000);
         WebElement firstName=driver.findElement(By.xpath("(//input[@name='firstName'])[1]"));
         firstName.sendKeys("Test");
         Thread.sleep(2000);
         WebElement lastName=driver.findElement(By.xpath("(//input[@name='lastName'])[1]"));
         lastName.sendKeys("WE");
         Thread.sleep(3000);
      // 1️⃣ Click the combobox to open the dropdown
         WebElement salutationInput = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("(//input[@role='combobox'])[3]")));
         salutationInput.click();

         // 2️⃣ Wait for the option to be visible
         WebElement salutation1 = wait.until(
        	        ExpectedConditions.elementToBeClickable(
        	                By.xpath("(//input[@role='combobox'])[3]")
        	        ));
        	salutation1.sendKeys("Mr");
        	salutation1.sendKeys(Keys.ENTER);


         Thread.sleep(2000);
         WebElement firstName1=driver.findElement(By.xpath("(//input[@name='firstName'])[2]"));
         firstName1.sendKeys("RER");
         Thread.sleep(2000);
         WebElement lastName1=driver.findElement(By.xpath("(//input[@name='lastName'])[2]"));
         lastName1.sendKeys("WEW");


         WebElement element = driver.findElement(By.xpath("//div[text()='Payment options']/parent::div//div/div[6]"));

         JavascriptExecutor js = (JavascriptExecutor) driver;
         js.executeScript("arguments[0].scrollIntoView(true);", element);
         js.executeScript("arguments[0].click();", element);
         WebElement acceptTerms = driver.findElement(By.xpath("//input[@id='acceptTerms']"));
         acceptTerms.click();
         WebElement button = driver.findElement(By.xpath("(//button[@type='button'])[5]"));
         button.click();

//         JavascriptExecutor js1 = (JavascriptExecutor) driver;
//
//      // wait for booking page to fully load
//      wait.until(ExpectedConditions.presenceOfElementLocated(
//              By.xpath("//div[text()='Payment options']/parent::div//div/div[6]")
//      ));
//
//      // HARD WAIT FOR API (needed for this site)
//      Thread.sleep(5000);
//
//      // scroll to bottom (PG loads late)
//      js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//
//      Thread.sleep(2000);
//
//      // click PG button
//      WebElement pgBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
//              By.xpath("//button[contains(text(),'Proceed')]")
//      ));
//
//      js.executeScript("arguments[0].click();", pgBtn);
//
//      System.out.println("✅ PG CLICKED");

         
//         WebElement element = wait.until(
//                 ExpectedConditions.visibilityOfElementLocated(
//                         By.xpath("YOUR_XPATH")));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           
        }
    }

    // ================= SELECT DATE =================
    public static void selectDate(int dateFieldIndex, String day, String monthAndYear) {
        driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[" + dateFieldIndex + "]")).click();

        while (true) {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'react-datepicker__current-month')]")));

            if (header.getText().equals(monthAndYear)) {
                WebElement date = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'react-datepicker__day') " +
                                "and not(contains(@class,'react-datepicker__day--disabled')) " +
                                "and text()='" + day + "']")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", date);
                break;
            }

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]"))).click();
            //driver.findElement(By.xpath("//div[@class='fs-12 fw-500 me-2 hotels-found-text']"));
        }
        
    }
    
}
    
