package io.github.matveyvolodin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductNamesInCart() {
        return driver.findElements(By.cssSelector(".cart_description h4"))
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }
}
