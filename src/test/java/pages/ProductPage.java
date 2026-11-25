
package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    WebDriver driver;

    // Locators
    By backpack = By.id("add-to-cart-sauce-labs-backpack");

    By cartButton = By.className("shopping_cart_link");

    By productTitle = By.className("inventory_item_name");

    By productPrice = By.className("inventory_item_price");

    By sortDropdown = By.className("product_sort_container");

    By cartBadge = By.className("shopping_cart_badge");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

   
   
    // Click cart
    public void clickOnCart() {
        driver.findElement(cartButton).click();
    }

    // Get product names
    public List<String> getProductNames() {

        List<String> names = new ArrayList<>();

        List<WebElement> products =
                driver.findElements(productTitle);

        for (WebElement product : products) {
            names.add(product.getText());
        }

        return names;
    }

    // Get product prices
    public List<Double> getProductPrices() {

        List<Double> prices = new ArrayList<>();

        List<WebElement> elements =
                driver.findElements(productPrice);

        for (WebElement element : elements) {

            String price = element.getText().replace("$", "");

            prices.add(Double.parseDouble(price));
        }

        return prices;
    }

    // Sort products by price low to high
    public void sortPriceLowToHigh() {

        Select select = new Select(
                driver.findElement(sortDropdown));

        select.selectByValue("lohi");
    }

    // Verify prices are sorted
    public boolean isPriceSortedLowToHigh() {

        List<Double> actualPrices = getProductPrices();

        List<Double> sortedPrices =
                new ArrayList<>(actualPrices);

        Collections.sort(sortedPrices);

        return actualPrices.equals(sortedPrices);
    }

    // Get cart count
    public String getCartCount() {

        List<WebElement> badge =
                driver.findElements(cartBadge);

        if (badge.isEmpty()) {
            return "0";
        }

        return badge.get(0).getText();
    }

	public void addBackpackToCart() {
		// TODO Auto-generated method stub
		 driver.findElement(backpack).click();
    
	}
}
