package Databind;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Logindata {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Launch browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    
String path="src\\test\\resources\\testData\\Binding.xlsx";
        // Read Excel
      //  FileInputStream file =new FileInputStream(System.getProperty("user.dir") + "src\\test\\resources\\testData\\Binding.xlsx");
        FileInputStream file =new FileInputStream(path);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rows = sheet.getLastRowNum();
        System.out.println(rows);

        // Loop data (skip header)
        for (int r = 0; r <= rows; r++) {

            XSSFRow row = sheet.getRow(r);
            

            String url = row.getCell(0).toString();
            System.out.println(url);
            String userId = row.getCell(1).toString();
            String password = row.getCell(2).toString();
            String city = row.getCell(3).toString();
//            String phone = row.getCell(4).toString();
//            System.out.println(phone);
            DataFormatter formatter = new DataFormatter();
            String phone = formatter.formatCellValue(row.getCell(4));
            System.out.println(phone);

            String firstname = row.getCell(5).toString();
            String lastname = row.getCell(6).toString();
            String firstname1 = row.getCell(7).toString();
            String lastname1 = row.getCell(8).toString();
            
            
            System.out.println(url);
            
            System.out.println(userId  + "      "+password+ "  " +city+ "  "+firstname+" "+lastname+" "+firstname1+" "+lastname1);
            
            //enter url
            driver.get(url);
            Thread.sleep(1000);

            // Enter Agent User ID
         driver.findElement(By.id("agent_userid")).clear();
            driver.findElement(By.id("agent_userid")).sendKeys(userId);

            // Enter Agent Password
            driver.findElement(By.id("agent_password")).clear();
            driver.findElement(By.id("agent_password")).sendKeys(password);

            // Click Login
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            Thread.sleep(4000);
            driver.findElement(By.xpath("//div[text()='Message From Admin']/parent::div/parent::div//button")).click();
            Thread.sleep(3000);
            // selecting hotel button
            
            driver.findElement(By.xpath("//div[contains(@class,'service-tab')][2]")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("react-select-5-input")).clear();
           driver.findElement(By.id("react-select-5-input")).sendKeys(city);
           List<WebElement> cityList = driver.findElements(By.xpath("//div[@class='d-flex']//span"));

           cityList.get(0).click();
           Thread.sleep(2000);
           selectDate(1, "30", "January 2026", driver); // Check-in
           selectDate(2, "31", "January 2026", driver); // Check-out
           
           WebElement searchBtn = driver.findElement( By.xpath("//button[contains(text(),'Search Hotels')]"));
           searchBtn.click();
           Thread.sleep(5000);
           try {
               WebElement hotelResult =driver.findElement( By.xpath("//div[contains(@class,'hotels-found-text')]"));

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

           WebElement hotelOption = driver.findElement(
                           By.xpath("//div[@role='option']"));
           hotelOption.click();

           driver.findElement(By.xpath("(//div[@class='css-19bb58m'])[5]")).click();

           WebElement addressOption = driver.findElement( By.xpath("//div[@role='option']"));
           addressOption.click();
           Thread.sleep(3000);
           WebElement Map=  driver.findElement(By.xpath("//span[contains(text(),'MAP VIEW')]"));
           Map.click();
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
        	// Supplier
        	String supplier = driver.findElement(By.xpath("//span[@class='fs-12 hotel-card__body__header_hotel-suppliername'][1]")).getText();
        	System.out.println("Supplier: " + supplier);
        	 String parentWindow = driver.getWindowHandle();
        	Thread.sleep(3000);
        	try {
       driver.findElement(By.xpath("//button[@class='app-btn-transparent '][1]")).click();
       driver.findElement(By.xpath("//input[@name='whatsappNumber']")).clear();
       driver.findElement(By.xpath("//input[@name='whatsappNumber']")).sendKeys(phone);
      
                    
        driver.findElement(By.xpath("//button[text()='Send WhatsApp']")).click();
        	}
        	catch(Exception e)
        	{
        		System.out.println("stale "+e.getMessage());
        		
        		  driver.findElement(By.xpath("//button[@class='app-btn-transparent '][1]")).click();
                  driver.findElement(By.xpath("//input[@name='whatsappNumber']")).sendKeys(phone);             
                   driver.findElement(By.xpath("//button[text()='Send WhatsApp']")).click();
        	}
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

            driver.switchTo().window(whatsappWindow);

            // Verify
            System.out.println("WhatsApp URL: " + driver.getCurrentUrl());

            // Close ONLY WhatsApp tab
            driver.close();

            // Switch back to parent window
            driver.switchTo().window(parentWindow);
            Thread.sleep(2000);
//        	driver.findElement(By.xpath("//button[text( )='Select Room']")).click();
//        Thread.sleep(2000);
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

        System.out.println("In booking tab");

        // Wait until Book Now EXISTS in NEW TAB
        WebElement bookNowBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[normalize-space()='Book Now']")));

        // Click Book Now
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", bookNowBtn);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", bookNowBtn);

        System.out.println(" Book Now clicked successfully");

     // Switch iframe if present
        System.out.println("Windows count: " + driver.getWindowHandles().size());
        System.out.println("Current title: " + driver.getTitle());
     Thread.sleep(2000);
     // Click Book Now again
//     WebElement bookNowFinal = wait.until(ExpectedConditions.presenceOfElementLocated(
//             By.xpath("//div[normalize-space()='Book Now']")));
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

     WebElement phone1=driver.findElement(By.xpath("//input[@name='phoneNumber']"));
     phone1.sendKeys(phone);
     Thread.sleep(1000);
//     WebElement terms1=driver.findElement(By.xpath("(//button[@type=\"button\"])[2]"));
//     terms1.click();
//     Thread.sleep(1000);
     
     WebElement salutation = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("(//input[@role='combobox'])[2]")));
    	salutation.click();
    	salutation.sendKeys("Mr");
    	salutation.sendKeys(Keys.ENTER);

    		 
     Thread.sleep(2000);
     WebElement firstName=driver.findElement(By.xpath("(//input[@name='firstName'])[1]"));
     firstName.sendKeys(firstname);
     Thread.sleep(2000);
     WebElement lastName=driver.findElement(By.xpath("(//input[@name='lastName'])[1]"));
     lastName.sendKeys(lastname);
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
     firstName1.sendKeys(firstname1);
     Thread.sleep(2000);
     WebElement lastName1=driver.findElement(By.xpath("(//input[@name='lastName'])[2]"));
     lastName1.sendKeys(lastname1);


     WebElement element = driver.findElement(By.xpath("//div[text()='Payment options']/parent::div//div/div[6]"));

     JavascriptExecutor js = (JavascriptExecutor) driver;
     js.executeScript("arguments[0].scrollIntoView(true);", element);
     js.executeScript("arguments[0].click();", element);
   
     
     WebElement acceptTerms = driver.findElement(By.xpath("//input[@id='acceptTerms']"));
     acceptTerms.click();
     WebElement button = driver.findElement(By.xpath("(//button[@type='button'])[5]"));
     button.click();
        }
    }



//     JavascriptExecutor js1 = (JavascriptExecutor) driver;
//
//  // wait for booking page to fully load
//  wait.until(ExpectedConditions.presenceOfElementLocated(
//          By.xpath("//div[text()='Payment options']/parent::div//div/div[6]")
//  ));
//
//  // HARD WAIT FOR API (needed for this site)
//  Thread.sleep(5000);
//
//  // scroll to bottom (PG loads late)
//  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//
//  Thread.sleep(2000);
//
//  // click PG button
//  WebElement pgBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
//          By.xpath("//button[contains(text(),'Proceed')]")
//  ));
//
//  js.executeScript("arguments[0].click();", pgBtn);
//
//  System.out.println("✅ PG CLICKED");

     
//     WebElement element = wait.until(
//             ExpectedConditions.visibilityOfElementLocated(
//                     By.xpath("YOUR_XPATH")));
       	
       	
           
       	// driver.findElement(By.xpath("//button[text( )='Select Room']")).click();
        // Open Map View
           
        
        // ================= SELECT DATE =================
           public static void selectDate(int dateFieldIndex, String day, String monthAndYear,WebDriver driver) {
        	   WebDriverWait wait;
               wait = new WebDriverWait(driver, Duration.ofSeconds(20));
               //JavascriptExecutor driver;
               
			   driver.findElement(
                       By.xpath("(//div[@class='react-datepicker__input-container'])[" + dateFieldIndex + "]")
               ).click();

               while (true) {

                   
				   WebElement header = wait.until(
                           ExpectedConditions.visibilityOfElementLocated(
                                   By.xpath("//div[contains(@class,'react-datepicker__current-month')]")
                           )
                   );

                   if (header.getText().equals(monthAndYear)) {

                       WebElement date = wait.until(
                               ExpectedConditions.elementToBeClickable(
                                       By.xpath("//div[contains(@class,'react-datepicker__day') " +
                                               "and not(contains(@class,'react-datepicker__day--disabled')) " +
                                               "and text()='" + day + "']")
                               )
                       );

                       ((JavascriptExecutor) driver).executeScript("arguments[0].click();", date);
                       break;
                   }

                   wait.until(
                           ExpectedConditions.elementToBeClickable(
                                   By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]")
                           )
                   ).click();
               }
           }
}