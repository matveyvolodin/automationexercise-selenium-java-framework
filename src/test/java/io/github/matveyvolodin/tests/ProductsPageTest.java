package io.github.matveyvolodin.tests;

import io.github.matveyvolodin.api.client.AccountApiClient;
import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import io.github.matveyvolodin.pages.CartPage;
import io.github.matveyvolodin.pages.components.AddToCartModalComponent;
import io.github.matveyvolodin.pages.components.HeaderMenuComponent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductsPageTest extends BaseTest {

    private User user;
    private final AccountApiClient accountApiClient = new AccountApiClient();
    private final static String productName = "Sleeveless Dress";
    private final static String expectedSuccessMessage = "Your product has been added to cart.";


    @BeforeClass
    public void createUser() {
        user = UserFactory.getRandomUser();
        accountApiClient.createAccount(user);
    }

    @AfterClass
    public void deleteUser() {
        accountApiClient.deleteAccount(user);
    }

    @Test
    @Description("Verify that user can add a product to the cart and view the cart page")
    public void testAddProductToCart() {
        loginAs(user);

        String successMessage = new HeaderMenuComponent(driver)
                .clickProductsTab()
                .clickAddToCartButtonForProduct(productName)
                .getSuccessMessage();

        Allure.step("Verify sucess message 'Product added' is displayed", () ->
                Assert.assertEquals(successMessage, expectedSuccessMessage));

        CartPage cartPage = new AddToCartModalComponent(driver)
                .clickViewCart();

        Allure.step("Verify product is in cart", () ->
                Assert.assertTrue(cartPage.getProductNamesInCart().contains(productName)));

    }

    @Test
    @Description("Verify that user can view the product details page")
    public void testViewProductDetails() {
        String productDetailsName = new HeaderMenuComponent(driver)
                .clickProductsTab()
                .clickViewProductButtonForProduct(productName)
                .getProductName();

        Allure.step("Compare product names to verify that user is redirected to the product details page", () ->
                Assert.assertEquals(productDetailsName, productName));
    }
}
