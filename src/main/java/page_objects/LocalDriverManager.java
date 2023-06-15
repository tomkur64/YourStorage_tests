package page_objects;

import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log
public class LocalDriverManager {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = getDriver();
        driver.close();
        driver.quit();
        log.info("Driver quit successfully");
    }

    public static WebDriver createDriverInstance() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        return new ChromeDriver(chromeOptions);
    }

    public static void openGivenUrl(String url) {
        getDriver().get(url);
    }

    public static void waitForElement(WebElement element, int timeout) {
        (new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(WebElement element, int timeout) {
        (new WebDriverWait(getDriver(), Duration.ofSeconds(timeout))).until(ExpectedConditions.elementToBeClickable(element));
    }
}
