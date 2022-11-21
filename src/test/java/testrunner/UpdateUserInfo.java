package testrunner;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.EmployeePage;
import pages.LoginAdmin;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class UpdateUserInfo extends Setup {
    @BeforeTest(description = "Login with last created credential")
    public void doLogin() throws IOException, ParseException, InterruptedException {
        List data = Utils.readJSONArray("./src/test/resources/EmployeeCred.json");
        LoginAdmin loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        JSONObject obj = (JSONObject) data.get(data.size() - 1);
        String userName = (String) obj.get("userName");
        String password = (String) obj.get("password");
        loginPage.doLogin(userName, password);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "index";
        Assert.assertTrue(urlActual.contains(urlExpected));

        if(urlActual.contains(urlExpected))
        {
          //  Dashboard dashboard = new Dashboard(driver);

            driver.findElement(By.partialLinkText("My Info")).click();
            Thread.sleep(5000);

        }

    }
    @Test
    public void updateUserInfo() throws InterruptedException {
        List<WebElement>headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Utils.waitForElement(driver, headerTitle.get(0),100);
        if(headerTitle.get(0).isDisplayed())
        {
            EmployeePage employeePage = new EmployeePage(driver);
           // Utils.scrollDown(driver);
            employeePage.dropdown.get(0).click();
            employeePage.dropdown.get(0).sendKeys("b");
            employeePage.dropdown.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdown.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdown.get(0).sendKeys(Keys.ENTER);
            Utils.scrollDown(driver);
            List<WebElement>buttons = driver.findElements(By.cssSelector("[type=submit]"));
            buttons.get(1).click();

            Thread.sleep(5000);
            String updatedInfo=   employeePage.dropdown.get(0).getText();
            Assert.assertTrue(updatedInfo.equals("Bangladeshi"));


        }
    }
    @AfterTest
    public void logout()
    {
        Dashboard dashboard = new Dashboard(driver);
        dashboard.btnProfileIcon.click();
        driver.findElement(By.partialLinkText("Logout")).click();//exact same spelling
    }
}
