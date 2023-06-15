package page_objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class FileManagerPage extends YourStoragePage {

    @FindBy(xpath = "//div[@class='ui-helper-clearfix ui-widget-header elfinder-toolbar']")
    private WebElement actionToolbar;

    @FindBy(xpath = "//div[@class='elfinder-tree']")
    private WebElement directoryTree;

    @FindBy(xpath = "//div[contains(@class, 'view-list ui')]/table")
    private WebElement fileManagerTable;

    @FindBy(xpath = "//span[text()='Wybierz pliki']/following-sibling::form/input[@type='file']")
    private WebElement fileUploadInput;

    public void selectFolderByName(String folderName) throws InterruptedException {
        Thread.sleep(2000);
        WebElement folderElement = directoryTree.findElement(By.xpath(".//span[@id][@title='drive/"+folderName+"']"));
        LocalDriverManager.waitUntilClickable(folderElement, 5);
        folderElement.click();
    }

    public WebElement getActionByName(String actionName) {
        LocalDriverManager.waitUntilClickable(actionToolbar, 3);
        return actionToolbar.findElement(By.xpath(".//div[@title='"+actionName+"']"));
    }

    public String inputAndStoreNewFolderName() {
        String timestamp = "test_" + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH-mm-ss-SSS_"+Thread.currentThread().getId()));

        getActionByName("Nowy katalog").click();
        WebElement newFolderNameInput = fileManagerTable.findElement(By.xpath(
                "//div[contains(@class, 'view-list ui')]/table//tr//div/span/textarea"));
        LocalDriverManager.waitUntilClickable(newFolderNameInput, 5);

        newFolderNameInput.sendKeys(timestamp);
        newFolderNameInput.sendKeys(Keys.ENTER);
        return timestamp;
    }

    public void uploadFileFromPath(String filePath) {
        getActionByName("Wyślij pliki").click();
        fileUploadInput.sendKeys(filePath);
        LocalDriverManager.waitForElement(actionToolbar, 100);
    }
}
