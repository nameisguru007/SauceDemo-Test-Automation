
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    WebDriver driver;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueButton = By.id("continue");
    By cancelButton = By.id("cancel");

    // Error message locator
    By errorMessage = By.cssSelector("[data-test='error']");

    // Enter first name
    public void enterFirstName(String fname) {
        driver.findElement(firstName).sendKeys(fname);
    }

    // Enter last name
    public void enterLastName(String lname) {
        driver.findElement(lastName).sendKeys(lname);
    }

    // Enter postal code
    public void enterPostalCode(String zip) {
        driver.findElement(postalCode).sendKeys(zip);
    }

    // Click Continue
    public void submitCheckout() {
        driver.findElement(continueButton).click();
    }

    // Get validation error
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    // Click Cancel
    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }
}
