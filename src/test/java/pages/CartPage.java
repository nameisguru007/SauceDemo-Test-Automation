
package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    WebDriver driver;

    // Locators
    By cartProducts = By.className("inventory_item_name");
    By cartPrices = By.className("inventory_item_price");
    By removeButton = By.id("remove-sauce-labs-backpack");
    By continueShoppingButton = By.id("continue-shopping");
    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get product names
    public List<WebElement> getCartProducts() {
        return driver.findElements(cartProducts);
    }

    // Get product count
    public int getCartProductCount() {
        return getCartProducts().size();
    }

    // Get first product name
    public String getProductName() {
        return driver.findElement(cartProducts).getText();
    }

    // Get first product price
    public String getProductPrice() {
        return driver.findElement(cartPrices).getText();
    }

    // Remove backpack
    public void removeBackpack() {
        driver.findElement(removeButton).click();
    }

    // Continue shopping
    public void continueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    // Checkout
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
