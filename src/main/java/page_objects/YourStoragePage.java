package page_objects;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

import static page_objects.LocalDriverManager.getDriver;

public class YourStoragePage {

    public YourStoragePage() {
        PageFactory.initElements(
                new DefaultFieldDecorator(
                        new DefaultElementLocatorFactory(getDriver())), this);
    }

    @Getter
    @FindBy(xpath = "//nav[@id='site-navigation']//span[text()='Konto']")
    private WebElement accountDropdown;

    @Getter
    @FindBy(xpath = "//nav[@id='site-navigation']//a[text()='Wyloguj się']")
    private WebElement logoutButton;

    public void logOut(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(accountDropdown);
        logoutButton.click();
    }
}
