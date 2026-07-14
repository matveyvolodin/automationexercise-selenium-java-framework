package io.github.matveyvolodin.pages.components;

import io.github.matveyvolodin.pages.BasePage;
import io.github.matveyvolodin.pages.CartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartModalComponent extends BasePage {

    private final By successMessage = By.cssSelector("#cartModal .modal-body p");
    private final By viewCartButton = By.cssSelector("#cartModal a[href='/view_cart']");
    private final By continueShoppingButton = By.cssSelector("#cartModal .close-modal");

    public AddToCartModalComponent(WebDriver driver) {
        super(driver);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    @Step("Click the View Cart button in the Add to Cart modal")
    public CartPage clickViewCart() {
        click(viewCartButton);
        return new CartPage(driver);
    }
}