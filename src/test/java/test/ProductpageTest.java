
package test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;

@Listeners(listeners.ExtentTestListener.class)
public class ProductpageTest extends BaseTest {

    @Test
    public void addBackpackToCart() {

        // Login
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        // Product page
        ProductPage prod = new ProductPage(driver);

        // Add backpack to cart
        prod.addBackpackToCart();

        // Verify cart badge
        Assert.assertEquals(
                prod.getCartCount(),
                "1",
                "Cart count should be 1"
        );

        // Open cart
        prod.clickOnCart();

        // Verify cart URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("cart.html"),
                "User should navigate to cart page"
        );
    }
}
