
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

@Listeners(listeners.ExtentTestListener.class)
public class CheckoutOverviewTest extends BaseTest {

    public CheckoutOverviewPage openOverview() {

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        ProductPage product = new ProductPage(driver);
        product.addBackpackToCart();
        product.clickOnCart();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);

        checkout.enterFirstName("Gurus");
        checkout.enterLastName("QA");
        checkout.enterPostalCode("560001");
        checkout.submitCheckout();

        return new CheckoutOverviewPage(driver);
    }

    @Test
    public void verifyProductName() {

        CheckoutOverviewPage overview = openOverview();

        Assert.assertEquals(
            overview.getProductName(),
            "Sauce Labs Backpack"
        );
    }

    @Test
    public void verifyProductPrice() {

        CheckoutOverviewPage overview = openOverview();

        Assert.assertEquals(
            overview.getProductPrice(),
            "$29.99"
        );
    }

    @Test
    public void verifyQuantity() {

        CheckoutOverviewPage overview = openOverview();

        Assert.assertEquals(
            overview.getQuantity(),
            "1"
        );
    }

    @Test
    public void verifyPriceSummary() {

        CheckoutOverviewPage overview = openOverview();

        Assert.assertEquals(
            overview.getItemTotal(),
            "Item total: $29.99"
        );

        Assert.assertEquals(
            overview.getTax(),
            "Tax: $2.40"
        );

        Assert.assertEquals(
            overview.getTotal(),
            "Total: $32.39"
        );
    }
}
