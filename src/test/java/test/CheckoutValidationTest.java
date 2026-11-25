
package test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;

@Listeners(listeners.ExtentTestListener.class)
public class CheckoutValidationTest extends BaseTest {

    // Navigate to checkout information page
    public CheckoutPage openCheckout() {

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        ProductPage product = new ProductPage(driver);
        product.addBackpackToCart();
        product.clickOnCart();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        return new CheckoutPage(driver);
    }

    // Test 1: Verify empty checkout
    @Test(priority = 1)
    public void verifyEmptyCheckout() {

        CheckoutPage checkout = openCheckout();

        checkout.submitCheckout();

        Assert.assertEquals(
            checkout.getErrorMessage(),
            "Error: First Name is required"
        );
    }

    // Test 2: Verify missing last name
    @Test(priority = 2)
    public void verifyMissingLastName() {

        CheckoutPage checkout = openCheckout();

        checkout.enterFirstName("Gurus");
        checkout.submitCheckout();

        Assert.assertEquals(
            checkout.getErrorMessage(),
            "Error: Last Name is required"
        );
    }

    // Test 3: Verify missing postal code
    @Test(priority = 3)
    public void verifyMissingPostalCode() {

        CheckoutPage checkout = openCheckout();

        checkout.enterFirstName("Gurus");
        checkout.enterLastName("QA");

        checkout.submitCheckout();

        Assert.assertEquals(
            checkout.getErrorMessage(),
            "Error: Postal Code is required"
        );
    }

    // Test 4: Verify successful checkout information submission
    @Test(priority = 4)
    public void verifySuccessfulCheckout() {

        CheckoutPage checkout = openCheckout();

        checkout.enterFirstName("Gurus");
        checkout.enterLastName("QA");
        checkout.enterPostalCode("560001");

        checkout.submitCheckout();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("checkout-step-two"),
            "User should navigate to Checkout Overview page"
        );
    }
}
