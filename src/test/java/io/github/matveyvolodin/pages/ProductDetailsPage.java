package io.github.matveyvolodin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(By.cssSelector(".product-information h2")).getText();
    }

}
