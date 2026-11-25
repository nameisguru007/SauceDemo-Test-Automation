
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    WebDriver driver;

    // Constructor
    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By productName = By.className("inventory_item_name");
    By productPrice = By.className("inventory_item_price");
    By quantity = By.className("cart_quantity");

    By paymentInfo = By.className("summary_value_label");
    By itemTotal = By.className("summary_subtotal_label");
    By tax = By.className("summary_tax_label");
    By total = By.className("summary_total_label");

    By finishButton = By.id("finish");
    By cancelButton = By.id("cancel");

    // Get product details
    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public String getQuantity() {
        return driver.findElement(quantity).getText();
    }

    // Get payment information
    public String getPaymentInfo() {
        return driver.findElement(paymentInfo).getText();
    }

    // Get price details
    public String getItemTotal() {
        return driver.findElement(itemTotal).getText();
    }

    public String getTax() {
        return driver.findElement(tax).getText();
    }

    public String getTotal() {
        return driver.findElement(total).getText();
    }

    // Finish order
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    // Cancel order
    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }
}
