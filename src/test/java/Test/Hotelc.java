package Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hotelc {

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

            driver.findElement(By.id("agent_password"))
                    .sendKeys("Rashmi@#$333");

             driver.findElement(By.xpath("//button[@type='submit']")).click();
      Thread.sleep(2000);
      driver.findElement(By.xpath("//button[normalize-space()='Close']")).click();
      Thread.sleep(3000);

            // ================= HOTEL TAB =================
      driver.findElement(By.xpath("//div[contains(@class,'service-tab')][2]")).click();
      Thread.sleep(3000); 
                   
            Thread.sleep(1000);

            // Close popup if present
            
            
            // ================= CITY =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("react-select-5-input"))).sendKeys("DELHI");

            List<WebElement> cityList =
                    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.xpath("//div[@class='d-flex']//span")));
            cityList.get(0).click();

            // ================= DATE SELECTION =================
            //openCheckIn();
            selectDate("30", "December 2025");

            //openCheckOut();
            selectDate("31", "December 2025"); // Check-out

            // ================= SEARCH =================
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Search Hotels')]")));

            searchBtn.click();

            Thread.sleep(3000);
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    


    // ================= SELECT DATE =================
    public static void selectDate(String day, String monthAndYear) {
driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[1]")).click();
        while (true) {

            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'react-datepicker__current-month')]")));

            if (header.getText().equals(monthAndYear)) {

                WebElement date = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'react-datepicker__day') " +
                                        "and not(contains(@class,'react-datepicker__day--disabled')) " +
                                        "and text()='" + day + "']")));

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", date);
                break;
            }

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]")))
                    .click();
        }
    }


public static void selectDate2(String day, String monthAndYear) {
driver.findElement(By.xpath("(//div[@class='react-datepicker__input-container'])[2]")).click();
       while (true) {

           WebElement header = wait.until(
                   ExpectedConditions.visibilityOfElementLocated(
                           By.xpath("//div[contains(@class,'react-datepicker__current-month')]")));

           if (header.getText().equals(monthAndYear)) {

               WebElement date = wait.until(
                       ExpectedConditions.elementToBeClickable(
                               By.xpath("//div[contains(@class,'react-datepicker__day') " +
                                       "and not(contains(@class,'react-datepicker__day--disabled')) " +
                                       "and text()='" + day + "']")));

               ((JavascriptExecutor) driver)
                       .executeScript("arguments[0].click();", date);
               break;
           }

           wait.until(ExpectedConditions.elementToBeClickable(
                   By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]")))
                   .click();
       }
   }



{
driver.findElement(By.xpath("(//button[text()='Search Hotels']")).click();
}
}


