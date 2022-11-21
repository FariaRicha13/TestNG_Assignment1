package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmpoyeeInfoPage {
    @FindBy(className = "")
    public List<WebElement>empId;
    public EmpoyeeInfoPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
}
