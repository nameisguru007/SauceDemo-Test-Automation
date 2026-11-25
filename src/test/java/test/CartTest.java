
package test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;

@Listeners(listeners.ExtentTestListener.class)
public class CartTest extends BaseTest {

    @Test
    public void verifyCartFunctionality() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Add product
        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();

        // Open cart
        productPage.clickOnCart();

        // Cart page
        CartPage cartPage = new CartPage(driver);

        // Verify product count
        Assert.assertEquals(
                cartPage.getCartProductCount(),
                1,
                "Cart should contain one product"
        );

        // Verify product name
        Assert.assertEquals(
                cartPage.getProductName(),
                "Sauce Labs Backpack"
        );

        // Verify product price
        Assert.assertEquals(
                cartPage.getProductPrice(),
                "$29.99"
        );

        // Remove product
        cartPage.removeBackpack();

        // Verify cart is empty
        Assert.assertEquals(
                cartPage.getCartProductCount(),
                0,
                "Cart should be empty after removing product"
        );
    }
}
