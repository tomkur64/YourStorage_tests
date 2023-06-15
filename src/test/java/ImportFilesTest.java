import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import page_objects.FileManagerPage;
import page_objects.LocalDriverManager;
import page_objects.LoginPage;

import static page_objects.LocalDriverManager.*;

@Log
//@Test(invocationCount = 4, threadPoolSize = 2)
public class ImportFilesTest  {

    private static final String userLogin = "testUser_performance";
    private static final String userPassword = "1ol71B8sZvL2r^%an*#3Q0wC";

    @Test(invocationCount = 2, threadPoolSize = 2)
//    @Test
    public void createNewFolder() throws InterruptedException {
        System.out.println(Thread.currentThread().getId());
        WebDriver driver = createDriverInstance();
        setWebDriver(driver);
        openGivenUrl("https://yourstorage.cloud/logowanie/");

        new LoginPage().loginWithCredentials(userLogin, userPassword);
        log.info("User " + userLogin + " logged in successfully");

        FileManagerPage page = new FileManagerPage();
        String baseFolder = "performance testing";
        page.selectFolderByName(baseFolder);

        String newFolderName = page.inputAndStoreNewFolderName();

        page.selectFolderByName(baseFolder +"/"+ newFolderName);
        page.uploadFileFromPath("/Users/Tomek/Downloads/1925_Golf_GTI_K11_W1.pdf");
//        page.uploadFileFromPath("/Applications/MySQLWorkbench.app");

        Thread.sleep(3000);
        LocalDriverManager.quitDriver();
    }

//        WebElement logOutButton = driver.findElement(By.xpath(
//                "//nav[@id='site-navigation']//a[text()='Wyloguj się']"));
//        logOutButton.click();
//        new YourStoragePage(driver).logOut(driver);
//    }
}
