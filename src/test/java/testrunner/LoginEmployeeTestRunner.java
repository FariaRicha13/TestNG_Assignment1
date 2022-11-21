package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginAdmin;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class LoginEmployeeTestRunner extends Setup {
    LoginAdmin loginPage;

    @Test(priority = 1)
    public void loginWithLastEmpCred() throws IOException, ParseException {
        loginPage = new LoginAdmin(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        List data = Utils.readJSONArray("./src/test/resources/EmployeeCred.json");
        JSONObject obj = (JSONObject) data.get(data.size() - 1);
        String userName = (String) obj.get("userName");
        String password = (String) obj.get("password");
        loginPage.doLogin(userName, password);
        String cur_url = driver.getCurrentUrl();
        String exp_url = "index";
        Assert.assertTrue(cur_url.contains(exp_url));

    }

    @Test(priority = 2)
    public void imageExistanceTest() {
        boolean image_status = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(image_status);
    }
}
