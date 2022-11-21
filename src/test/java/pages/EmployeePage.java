package pages;

import org.bouncycastle.est.LimitedSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeePage {
    @FindBy(className = "oxd-table-cell")
    public List<WebElement>data;
    @FindBy(className = "oxd-input")
    public  List<WebElement> empId;
    @FindBy(className = "oxd-button")
    public List<WebElement> addBtn;
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdown;
    @FindBy(css = "[type=submit]")
    public WebElement txtSubmit;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name = "lastName")
    WebElement txtLastName;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtUserCreds;
    @FindBy(className = "oxd-switch-input")
    public WebElement toggleBtn;
    @FindBy(className = "oxd-input-field-error-message")
    public WebElement lblError;

    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String checkIfUserExists(String username) {
        txtUserCreds.get(5).sendKeys(username);
        return lblError.getText();
    }

    public void createEmployee(String fName, String lName, String uName, String pass, String confPass) throws InterruptedException {


        txtFirstName.sendKeys(fName);
        txtLastName.sendKeys(lName);


        txtUserCreds.get(5).clear();
        txtUserCreds.get(6).clear();
        txtUserCreds.get(5).sendKeys(uName);
        txtUserCreds.get(6).sendKeys(pass);
        txtUserCreds.get(7).sendKeys(confPass);
        txtSubmit.click();
        Thread.sleep(5000);


    }




}
