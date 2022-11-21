package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Dashboard {
    @FindBy(className = "oxd-userdropdown-img")
    public WebElement btnProfileImg;
    @FindBy(className = "oxd-userdropdown-name")
    public WebElement btnProfileIcon;
    @FindBy(partialLinkText = "Logout")
    public WebElement linkLogout;
    @FindBy(className = "oxd-text")
    public List<WebElement> sidebars;
    @FindBy(className = "oxd-grid-3")
public  List<WebElement>grid;
    @FindBy(id = "rh2csxbA")
    public WebElement graph1;
    public Dashboard(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
