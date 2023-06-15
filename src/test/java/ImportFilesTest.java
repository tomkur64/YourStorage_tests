import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import page_objects.FileManagerPage;
import page_objects.LocalDriverManager;
import page_objects.LoginPage;

import static page_objects.LocalDriverManager.*;

public class ImportFilesTest  {

    //poniższe dane wymagają wypełnienia po pobraniu projektu
    private static final String userLogin = "testUser_performance";
    private static final String userPassword = "";
    private static final String pathToFile = "";
    private static final String baseFolder = "performance testing";

    @Test(invocationCount = 15, threadPoolSize = 15)
    public void createNewFolder() throws InterruptedException {
        WebDriver driver = createDriverInstance();
        setWebDriver(driver);
        openGivenUrl("https://yourstorage.cloud/logowanie/");

        new LoginPage().loginWithCredentials(userLogin, userPassword);
        FileManagerPage fmPage = new FileManagerPage();

        fmPage.selectFolderByName(baseFolder);
        String newFolderName = fmPage.inputAndStoreNewFolderName();

        fmPage.selectFolderByName(baseFolder + "/" + newFolderName);
        fmPage.uploadFileFromPath(pathToFile);

        LocalDriverManager.quitDriver();
    }
}
