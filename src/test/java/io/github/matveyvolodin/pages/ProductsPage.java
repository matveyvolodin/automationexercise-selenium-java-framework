package io.github.matveyvolodin.pages;

import io.github.matveyvolodin.pages.components.AddToCartModalComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage  extends BasePage{
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Add to Cart' button for product: {productName}")
    public AddToCartModalComponent clickAddToCartButtonForProduct(String productName) {
        driver.findElement(By.xpath("//div[@class='productinfo text-center']" +
                         "/p[text()='" + productName + "']/following-sibling::a[contains(text(),'Add to cart')]"))
                         .click();
        return new AddToCartModalComponent(driver);
    }

    @Step("Click 'View Product' button for product: {productName}")
    public ProductDetailsPage clickViewProductButtonForProduct(String productName) {
        driver.findElement(By.xpath("//p[text()='" + productName + "']" +
                        "/ancestor::div[contains(@class,'single-products')]" +
                        "/following-sibling::div[@class='choose']" +
                        "//a[contains(@href,'product_details')]"))
                        .click();
        return new ProductDetailsPage(driver);
    }


}
