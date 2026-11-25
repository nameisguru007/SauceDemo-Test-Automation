
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderConfirmationPage {

    WebDriver driver;

    // Constructor
    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By confirmationTitle = By.className("complete-header");
    By confirmationMessage = By.className("complete-text");
    By backHomeButton = By.id("back-to-products");

    // Get confirmation title
    public String getConfirmationTitle() {
        return driver.findElement(confirmationTitle).getText();
    }

    // Get confirmation message
    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    // Click Back Home
    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
}
