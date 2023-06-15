import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import page_objects.LocalDriverManager;

import static page_objects.LocalDriverManager.*;

@Log
public class BaseTest {

    private static final String userLogin = "testUser_performance";
    private static final String userPassword = "1ol71B8sZvL2r^%an*#3Q0wC";

    @BeforeClass
    public void openPageAndLogIn() {
        System.out.println("begin");
        WebDriver driver = createDriverInstance();
        setWebDriver(driver);
        openGivenUrl("https://yourstorage.cloud/logowanie");

//        new LoginPage().loginWithCredentials(userLogin, userPassword);
        log.info("User " + userLogin + " logged in successfully");
    }

    @AfterClass
    public void teardown() {
//        WebElement logOutButton = driver.findElement(By.xpath(
//                "//nav[@id='site-navigation']//a[text()='Wyloguj się']"));
//        logOutButton.click();
//        new YourStoragePage(driver).logOut(driver);
        LocalDriverManager.quitDriver();
    }
}
