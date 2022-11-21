package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginAdmin {
    @FindBy(name = "username")
    WebElement textUsername;
    @FindBy(className = "oxd-text")
    public List<WebElement> errorTexts;
    @FindBy(name = "password")
    WebElement textPassword;

    @FindBy(css = "[type=submit]")
    WebElement textSubmit;

    public LoginAdmin(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public void doLogin(String username, String password) {
        textUsername.sendKeys(username);
        textPassword.sendKeys(password);
        textSubmit.click();
    }


}
