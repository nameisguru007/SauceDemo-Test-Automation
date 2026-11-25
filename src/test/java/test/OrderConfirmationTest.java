
package test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CheckoutOverviewPage;
import pages.OrderConfirmationPage;

@Listeners(listeners.ExtentTestListener.class)
public class OrderConfirmationTest extends BaseTest {

    public OrderConfirmationPage completeOrder() {

        // Login
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        // Add product
        ProductPage product = new ProductPage(driver);
        product.addBackpackToCart();
        product.clickOnCart();

        // Checkout
        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.enterFirstName("Gurus");
        checkout.enterLastName("QA");
        checkout.enterPostalCode("560001");
        checkout.submitCheckout();

        // Finish order
        CheckoutOverviewPage overview =
                new CheckoutOverviewPage(driver);

        overview.clickFinish();

        return new OrderConfirmationPage(driver);
    }

    @Test(priority = 1)
    public void verifyOrderConfirmationTitle() {

        OrderConfirmationPage confirmation = completeOrder();

        Assert.assertEquals(
            confirmation.getConfirmationTitle(),
            "Thank you for your order!"
        );
    }

    @Test(priority = 2)
    public void verifyOrderConfirmationMessage() {

        OrderConfirmationPage confirmation = completeOrder();

        Assert.assertTrue(
            confirmation.getConfirmationMessage()
                .contains("Your order has been dispatched")
        );
    }

    @Test(priority = 3)
    public void verifyBackHomeButton() {

        OrderConfirmationPage confirmation = completeOrder();

        confirmation.clickBackHome();

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory.html"),
            "User should navigate back to products page"
        );
    }
}
