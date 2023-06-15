package page_objects;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPage extends YourStoragePage {

    @FindBy(id = "username-2377")
    private WebElement loginInput;

    @FindBy(id = "user_password-2377")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit'][@value='Zaloguj się']")
    private WebElement submitLoginButton;

    public void loginWithCredentials(String login, String password) {
        LocalDriverManager.waitForElement(loginInput, 3);
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        LocalDriverManager.waitUntilClickable(submitLoginButton, 3);
        submitLoginButton.click();
        LocalDriverManager.waitForElement(new FileManagerPage().getActionToolbar(), 10);
    }
}
