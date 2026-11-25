
package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import base.BaseTest;
import pages.LoginPage;
import dataproviders.LoginDataProvider;

import org.openqa.selenium.By;

@Listeners(listeners.ExtentTestListener.class)
public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData",
          dataProviderClass = LoginDataProvider.class)

    public void loginTest(String username,
                          String password,
                          String expected) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        if (expected.equalsIgnoreCase("SUCCESS")) {

            Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Login should be successful for: " + username
            );

        } else {

            String actualError = driver.findElement(
                By.cssSelector("[data-test='error']")
            ).getText();

            Assert.assertFalse(
                actualError.isEmpty(),
                "Error message should be displayed for: " + username
            );
        }
        //Assert.fail("Testing automatic screenshot capture");
    }
}
