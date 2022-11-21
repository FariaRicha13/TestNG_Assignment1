package testrunner;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeTest;
import pages.EmployeeInfoPage;
import pages.EmployeePage;
import org.apache.commons.lang3.exception.ExceptionContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginAdmin;
import setup.Setup;
import utils.Utils;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EmployeeTestRunner extends Setup {
    @BeforeTest
    public void doLogin() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        LoginAdmin loginPage = new LoginAdmin(driver);
        String adminUser = "admin";
        String adminPass = "admin123";
        loginPage.doLogin(adminUser, adminPass);
        //Thread.sleep(1000);
    }

    @Test(priority = 1, description = "check if user exists", enabled = false)
    public void checkIfUserExists() throws IOException, ParseException {

        EmployeePage employeePage = new EmployeePage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
//        employeePage.addBtn.get(2).click();
//        employeePage.toggleBtn.click();
        List data = Utils.readJSONArray("./src/test/resources/EmployeeCred.json");
        JSONObject obj = (JSONObject) data.get(data.size() - 1);
        String existingUserName = (String) obj.get("userName");
        // employeePage.checkIfUserExists()
        String validationMessageActual = employeePage.checkIfUserExists(existingUserName);
        String validationMessageExpected = "Username already exists";

        Assert.assertTrue(validationMessageActual.contains(validationMessageExpected));

    }

    @Test(priority = 2, description = "create user1")
    public void createEmployee() throws IOException, ParseException, InterruptedException, UnsupportedFlavorException {
        EmployeePage employeePage = new EmployeePage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        employeePage.addBtn.get(2).click();
        Thread.sleep(5000);

        List<WebElement> elements = driver.findElements(By.className("oxd-input"));
        elements.get(4).sendKeys(Keys.CONTROL + "a");
        elements.get(4).sendKeys(Keys.CONTROL + "c");
        employeePage.toggleBtn.click();
        Utils utils = new Utils();
        utils.geneateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        String id = Utils.pasteValue();
        String userName = utils.getUsername();
        String password = "P@ssword"+Utils.generateRandomNumber(1000,9000);
        String confirmPassword = password;
       // employeePage.txtUserCreds.get(5).clear();
        employeePage.createEmployee(firstName, lastName, userName, password, confirmPassword);


        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());

        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            utils.writeJSON(userName,password,id,firstName,lastName);

        }
    }
    @Test(priority = 3, description = "create user2")
    public void createEmployee2() throws IOException, ParseException, InterruptedException, UnsupportedFlavorException {
        EmployeePage employeePage = new EmployeePage(driver);
        driver.findElement(By.partialLinkText("Add Employee")).click();
      //  employeePage.addBtn.get(2).click();
        Thread.sleep(5000);

        List<WebElement> elements = driver.findElements(By.className("oxd-input"));
        elements.get(4).sendKeys(Keys.CONTROL + "a");
        elements.get(4).sendKeys(Keys.CONTROL + "c");
        employeePage.toggleBtn.click();
        Utils utils = new Utils();
        utils.geneateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        String id = Utils.pasteValue();

        String userName = utils.getUsername();
        String password = "P@ssword"+Utils.generateRandomNumber(1000,9000);
        String confirmPassword = password;
        // employeePage.txtUserCreds.get(5).clear();
        employeePage.createEmployee(firstName, lastName, userName, password, confirmPassword);


        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());

        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            utils.writeJSON(userName,password,id,firstName,lastName);

        }
    }
    @Test(priority = 4,description = "search first user")
    public void findEmployee1() throws IOException, ParseException, InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        driver.findElement(By.partialLinkText("Employee List")).click();
        List data = Utils.readJSONArray("./src/test/resources/EmployeeCred.json");
        JSONObject obj = (JSONObject) data.get(data.size() - 2);
        String empId1=(String) obj.get("id");
        String fName=(String) obj.get("firstName");
        String lName=(String) obj.get("lastName");
        List<WebElement> elements = driver.findElements(By.className("oxd-input"));
        elements.get(1).sendKeys(empId1);
        employeePage.addBtn.get(1).click();
        Thread.sleep(6000);
        Utils.scrollDown(driver);
        String act_fName=employeePage.data.get(2).getText();
        String act_lName=employeePage.data.get(3).getText();
        Assert.assertTrue(fName.equals(act_fName));
        Assert.assertTrue(lName.equals(act_lName));
Thread.sleep(5000);

    }
    @Test(priority = 5,description = "search second user")
    public void findEmployee2() throws IOException, ParseException, InterruptedException {
        EmployeePage employeePage = new EmployeePage(driver);
        driver.findElement(By.partialLinkText("Employee List")).click();
        List data = Utils.readJSONArray("./src/test/resources/EmployeeCred.json");
        JSONObject obj = (JSONObject) data.get(data.size() - 1);
        String empId1=(String) obj.get("id");
        String fName=(String) obj.get("firstName");
        String lName=(String) obj.get("lastName");
        List<WebElement> elements = driver.findElements(By.className("oxd-input"));
        elements.get(1).sendKeys(empId1);
        Thread.sleep(100);
        employeePage.addBtn.get(1).click();
        Thread.sleep(6000);
        Utils.scrollDown(driver);
        String act_fName=employeePage.data.get(2).getText();
        String act_lName=employeePage.data.get(3).getText();
        Assert.assertTrue(fName.equals(act_fName));
        Assert.assertTrue(lName.equals(act_lName));
        Thread.sleep(5000);



    }

}
