package Test;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import java.time.Duration;
	import java.util.List;
	import java.util.Set;

	public class Hotelemail {

	    static WebDriver driver;
	    static WebDriverWait wait;

	    public static void main(String[] args) {
	        try {
	            driver = new ChromeDriver();
	            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

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
//	            driver.findElement(By.xpath("//button[normalize-space()='Close']")).click();
//	            Thread.sleep(3000);
	            List<WebElement> closeBtns =
	                    driver.findElements(By.xpath("//button[normalize-space()='Close']"));

	            if (!closeBtns.isEmpty()) {
	                ((JavascriptExecutor) driver)
	                        .executeScript("arguments[0].click();", closeBtns.get(0));
	                System.out.println("Popup closed");
	            } else {
	                System.out.println("No popup displayed");
	            }

	            // ================= HOTEL TAB =================
	            driver.findElement(By.xpath("//div[contains(@class,'service-tab')][2]")).click();
	            Thread.sleep(3000);

	            // ================= CITY SELECTION =================
	            wait.until(ExpectedConditions.elementToBeClickable(By.id("react-select-5-input")))
	                    .sendKeys("DELHI");

	            List<WebElement> cityList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[@class='d-flex']//span")));
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
	            
	        	// driver.findElement(By.xpath("//button[text( )='Select Room']")).click();
	         // Open Map View
	            Thread.sleep(2000);
	           driver.findElement(By.xpath("//span[contains(text(),'MAP VIEW')]")).click();
//	            WebElement mapView = driver.findElement(By.xpath("//span[contains(text(),'MAP VIEW')]"));
	//
//	            
//	            JavascriptExecutor js = (JavascriptExecutor) driver;
//	            js.executeScript("arguments[0].click();", mapView);
	            Thread.sleep(3000);
	            // Verify map visible
	            boolean mapVisible = driver.findElement(
	                By.xpath("//div[contains(@class,'map')]")
	            ).isDisplayed();

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
	            	Thread.sleep(1000);
	            	// Hotel Name
	            	String hotelName = driver.findElement(By.xpath("//span[@class='hotel-card__body__header_hotel-name'][1]")).getText();
	            	System.out.println("Hotel Name: " + hotelName);

	            	// Hotel Address (adjust the XPath according to actual HTML)
	            	String hotelAddress = driver.findElement(By.xpath("//span[@class='hotel-card__body__header_hotel-location'][1]")).getText();
	            	System.out.println("Hotel Address: " + hotelAddress);

	            	// Supplier
	            	String supplier = driver.findElement(By.xpath("//span[@class='fs-12 hotel-card__body__header_hotel-suppliername'][1]")).getText();
	            	System.out.println("Supplier: " + supplier);
	            	Thread.sleep(3000);
	            	try {
	           driver.findElement(By.xpath("//button[@class='app-btn-transparent '][2]")).click();
	           driver.findElement(By.xpath("//input[@name=\"toEmail\"]")).sendKeys("prathibha@iweensoft.com");             
	            driver.findElement(By.xpath("//button[text()='Send Email']")).click();
	            	}
	            	catch(Exception e)
	            	{
	            		System.out.println("stale "+e.getMessage());
	            		
	            		driver.findElement(By.xpath("//button[@class='app-btn-transparent '][2]")).click();
	     	           driver.findElement(By.xpath("//input[@name=\"toEmail\"]")).sendKeys("prathibha@iweensoft.com");             
	     	            driver.findElement(By.xpath("//button[text()='Send Email']")).click();
	            	}
	            	// Store the current window handle
	         // Store parent window
	            String parentWindow = driver.getWindowHandle();
	            Thread.sleep(3000);

	            // Click Send WhatsApp
	            driver.findElement(By.xpath("//button[text()='Send Email']")).click();
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

//	            	String hotelName = driver.findElement(By.xpath("//span[@class='hotel-card__body__header_hotel-name'][1]")).getText();
//	            	System.out.println(hotelName);
//	            	String hoteladdress = driver.findElement(By.xpath("String hotelName = driver.findElement(By.xpath(\"//span[@class='hotel-card__body__header_hotel-name'][1]\")).getText();\r\n"
//	            			+ "            	System.out.println(hotelName);")).getText();
//	            	System.out.println(hoteladdress);
//	            	String supplier = driver.findElement(By.xpath("//span[@class='fs-12 hotel-card__body__header_hotel-suppliername'][1]")).getText();
//	            	
//	            	System.out.println(supplier);
//	            	 driver.findElement(By.xpath("//button[text( )='Select Room']")).click();

	            // ================= APPLY FILTER =================
	            	driver.findElement(By.xpath("//button[text( )='Select Room']")).click();

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
	    


