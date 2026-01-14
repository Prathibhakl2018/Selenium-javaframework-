package Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Hoteldesc {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.manage().window().maximize();
            driver.get("https://www.travelsupermall.com/login");

            // LOGIN
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("agent_userid")))
                    .sendKeys("rashmitha@tripgain.com");
            driver.findElement(By.id("agent_password"))
                    .sendKeys("Rashmi@#$333");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Close']"))).click();

            // HOTEL TAB
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[contains(@class,'service-tab')])[2]"))).click();

            // CITY
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("react-select-5-input"))).sendKeys("DELHI");
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='d-flex']//span"))).click();

            // DATE
            selectDate(1, "30", "January 2026");
            selectDate(2, "31", "January 2026");

            // SEARCH
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Search Hotels')]"))).click();

            // SELECT ROOM (WORKING)
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[normalize-space()='Select Room']")));
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll(\"button\").forEach(b=>{if(b.innerText==='Select Room')b.click();});"
            );

            // 🚀 DIRECT BOOKING NAVIGATION (NO CLICK)
            openBookingPage();

            System.out.println("✅ Booking page opened successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // OPEN BOOKING PAGE DIRECTLY
    private static void openBookingPage() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String bookingUrl = (String) js.executeScript("""
            let state = window.__REACT_DEVTOOLS_GLOBAL_HOOK__;
            let a = document.querySelector("a[href*='booking']");
            return a ? a.href : null;
        """);

        if (bookingUrl == null) {
            throw new RuntimeException("Booking URL not available – UI click required by app logic");
        }

        driver.get(bookingUrl);
    }

    // DATE PICKER
    private static void selectDate(int index, String day, String monthYear) {

        driver.findElement(By.xpath(
                "(//div[@class='react-datepicker__input-container'])[" + index + "]")).click();

        while (true) {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'react-datepicker__current-month')]")));

            if (header.getText().equals(monthYear)) {
                WebElement date = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", date);
                break;
            }
            driver.findElement(By.xpath(
                    "//button[contains(@class,'react-datepicker__navigation--next')]")).click();
        }
    }
}
