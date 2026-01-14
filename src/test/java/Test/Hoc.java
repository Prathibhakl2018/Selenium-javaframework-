package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Hoc {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) {

        try {
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            driver.manage().window().maximize();
            driver.get("https://www.travelsupermall.com/login");

            // ================= LOGIN =================
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("agent_userid")))
                    .sendKeys("rashmitha@tripgain.com");

            driver.findElement(By.id("agent_password"))
                    .sendKeys("Rashmi@#$333");

            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // ================= CLOSE POPUP (IF ANY) =================
            try {
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Close']"))).click();
            } catch (Exception e) {
                System.out.println("Popup not displayed");
            }

            // ================= HOTEL TAB =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//div[contains(@class,'service-tab')])[2]"))).click();

            // ================= CITY SELECTION =================
            WebElement cityInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("react-select-5-input")));

            cityInput.sendKeys("DELHI");

            // wait for dropdown to load
            Thread.sleep(1000);

            // press ENTER to select first option
            cityInput.sendKeys(Keys.ENTER);

            // ================= DATE SELECTION =================
            selectDate(1, "30", "January 2026");
            selectDate(2, "31", "January 2026");

            // ================= SEARCH =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Search Hotels')]"))).click();

            // ================= HOTELS FOUND =================
            try {
                WebElement hotelsFound = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[contains(@class,'hotels-found-text')]")));

                System.out.println(hotelsFound.getText());
            } catch (Exception e) {
                System.out.println("No Hotels Found");
            }

            // ================= MAP VIEW =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[contains(text(),'MAP')]"))).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'map')]")));

            System.out.println("Map view opened");

            // ================= GRID VIEW =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[contains(text(),'GRID')]"))).click();

            // ================= NET FARE SVG =================
            List<WebElement> netFareIcons = driver.findElements(
                    By.xpath("//*[name()='svg'][.//*[name()='title' and text()='Net Fare']]"));

            if (!netFareIcons.isEmpty()) {
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", netFareIcons.get(0));
                System.out.println("Net Fare icon clicked");
            } else {
                System.out.println("Net Fare icon not found");
            }

            // ================= SHORTLIST =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[text()='Shortlist'])[1]"))).click();

            // ================= HOTEL DETAILS =================
            String hotelName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//span[contains(@class,'hotel-name')])[1]"))).getText();

            String hotelAddress = driver.findElement(
                    By.xpath("(//span[contains(@class,'hotel-location')])[1]")).getText();

            String supplier = driver.findElement(
                    By.xpath("(//span[contains(@class,'suppliername')])[1]")).getText();

            System.out.println("Hotel Name   : " + hotelName);
            System.out.println("Hotel Address: " + hotelAddress);
            System.out.println("Supplier     : " + supplier);

            // ================= SEND WHATSAPP =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[contains(@class,'app-btn-transparent')])[1]"))).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("whatsappNumber"))).sendKeys("8431657889");

            String parentWindow = driver.getWindowHandle();

            driver.findElement(By.xpath("//button[text()='Send WhatsApp']")).click();

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String win : driver.getWindowHandles()) {
                if (!win.equals(parentWindow)) {
                    driver.switchTo().window(win);
                    break;
                }
            }

            System.out.println("WhatsApp URL: " + driver.getCurrentUrl());

            driver.close();
            driver.switchTo().window(parentWindow);

            // ================= SELECT ROOM =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Select Room']"))).click();

            System.out.println("Select Room clicked successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DATE METHOD =================
    public static void selectDate(int index, String day, String monthYear) {

        driver.findElement(
                By.xpath("(//div[@class='react-datepicker__input-container'])[" + index + "]")).click();

        while (true) {
            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'react-datepicker__current-month')]")));

            if (header.getText().contains(monthYear)) {
                WebElement date = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'react-datepicker__day') " +
                                "and not(contains(@class,'--disabled')) and text()='" + day + "']")));

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", date);
                break;
            }

            driver.findElement(
                    By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]")).click();
        }
    }
}
