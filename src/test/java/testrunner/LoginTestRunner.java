package testrunner;

import pages.Dashboard;
import pages.LoginAdmin;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.Utils;

import java.util.List;

public class LoginTestRunner extends Setup {
    LoginAdmin loginPage;
    Dashboard dashboardPage;

    @Test(priority = 1)
    public void doLogin() {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin("admin", "admin123");
        String cur_url = driver.getCurrentUrl();
        String exp_url = "index";
        Assert.assertTrue(cur_url.contains(exp_url));
    }

    @Test(description = "login with invalid username",enabled = false)
    public void doLoginInvalidUsername() {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin("admin23", "admin123");
      String act_msg= loginPage.errorTexts.get(1).getText();
      Assert.assertTrue(act_msg.equals("Invalid credentials"));
    }
    @Test(description = "login with invalid password",enabled = false)
    public void doLoginInvalidPassword() {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin("admin", "admin12453");
        String act_msg= loginPage.errorTexts.get(1).getText();
        Assert.assertTrue(act_msg.equals("Invalid credentials"));
    }
    @Test(description = "login with invalid username and password",enabled = false)
    public void doLoginInvalidUnamePass() {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin("admin2", "admin12333");
        String act_msg= loginPage.errorTexts.get(1).getText();
        Assert.assertTrue(act_msg.equals("Invalid credentials"));
    }
    @Test(description = "login with empty username",enabled = false)
    public void doLoginEmptyUsername() {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin("", "admin123");
        String act_msg= loginPage.errorTexts.get(4).getText();
        Assert.assertTrue(act_msg.equals("Required"));
    }
    @Test(description = "login with empty password",enabled = false)
    public void doLoginEmptyPassword() {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin("admin", "");
        String act_msg= loginPage.errorTexts.get(5).getText();
        Assert.assertTrue(act_msg.equals("Required"));
    }
    @Test(description = "login with empty username and password",enabled = false)
    public void doLoginEmptyUsernamePass() {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.doLogin("", "");
        String act_msg= loginPage.errorTexts.get(5).getText();
        Assert.assertTrue(act_msg.equals("Required"));
        String act_msg2= loginPage.errorTexts.get(4).getText();
        Assert.assertTrue(act_msg.equals("Required"));
    }

    @Test(priority = 2)
    public void imageExistanceTest() {
        boolean image_status = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(image_status);
    }

//    @Test(priority = 3)
//    public void selectEmployeeStatus() {
//        List<WebElement> dropdown = driver.findElements(By.className("oxd-select-text-input"));
//        dropdown.get(0).click();
//        for (int i = 0; i < 4; i++) {
//            dropdown.get(0).sendKeys(Keys.ARROW_DOWN);
//        }
//        dropdown.get(0).sendKeys(Keys.ENTER);
//        driver.findElement(By.cssSelector("[type=submit]")).click();
//    }
//
//    @Test(priority = 4)
//    public void getEmployeeStatus() {
//        Utils.scrollDown(driver);
//
//        WebElement table = driver.findElement(By.className("oxd-table-card"));
//        List<WebElement> allrows = table.findElements(By.cssSelector("[role=row]"));
//        for (WebElement row : allrows) {
//            List<WebElement> cells = row.findElements(By.cssSelector("[role=cell]"));
//
//            String act_text = cells.get(5).getText();
//            Assert.assertTrue(act_text.contains("Full-Time Probation"));
//
//        }
//
//
//    }
//    @Test(priority = 5)
//    public void doLogout() {
//        dashboardPage = new Dashboard(driver);
//        dashboardPage.btnProfileImg.click();
//        dashboardPage.linkLogout.click();
//    }

}
