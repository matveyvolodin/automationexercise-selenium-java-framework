package io.github.matveyvolodin.pages.components;

import io.github.matveyvolodin.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderMenuComponent extends BasePage {

    private final By logo = By.xpath("(//a[@href='/'])[1]");
    private final By homeButton = By.xpath("(//a[@href='/'])[2]");
    private final By productsButton = By.xpath("//a[@href='/products']");
    private final By cartButton = By.xpath("//a[@href='/view_cart']");
    private final By logoutButton = By.xpath("//a[@href='/logout']");
    private final By signupLoginButton = By.xpath("//a[@href='/login']");
    private final By deleteAccountButton = By.xpath("//a[@href='/delete_account']");
    private final By testsCasesButton = By.xpath("//a[@href='/test_cases']");
    private final By apiTestingButton = By.xpath("//a[@href='/api_list']");
    private final By videoTutorialsButton = By.
            xpath("//a[@href='https://www.youtube.com/c/AutomationExercise']");
    private final By contactUsButton = By.xpath("//a[@href='/contact_us']");

    private final By loginMessage = By.xpath("//a[contains(.,'Logged in as')]");


    public HeaderMenuComponent(WebDriver driver) {
        super(driver);
    }


    @Step("Click the Logo button")
    public MainPage clickLogo() {
        click(logo);
        return new MainPage(driver);
    }

    @Step("Click the 'Home' tab")
    public MainPage clickHomeTab() {
        click(homeButton);
        return new MainPage(driver);
    }

    @Step("Click the 'Products' tab")
    public ProductsPage clickProductsTab() {
        click(productsButton);
        return new ProductsPage(driver);
    }

    @Step("Click the 'Cart' tab")
    public CartPage clickCartTab() {
        click(cartButton);
        return new CartPage(driver);
    }

    @Step("Click the Logout button")
    public SignupLoginPage clickLogoutButton() {
        click(logoutButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click the 'Signup/Login' tab")
    public SignupLoginPage clickSignupLoginTab() {
        click(signupLoginButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click the 'Delete Account' tab")
    public AccountDeletedPage clickDeleteAccountTab() {
        click(deleteAccountButton);
        return new AccountDeletedPage(driver);
    }

    @Step("Click the 'Test Cases' tab")
    public TestCasesPage clickTestCasesTab() {
        click(testsCasesButton);
        return new TestCasesPage(driver);
    }

    @Step("Click the 'API Testing' tab")
    public ApiTestingPage clickApiTestingTab() {
        click(apiTestingButton);
        return new ApiTestingPage(driver);
    }

    @Step("Click the 'Video Tutorials' tab")
    public void clickVideoTutorialsTab() {
        click(videoTutorialsButton);
    }

    @Step("Click the 'Contact Us' tab")
    public ContactUsPage clickContactUsTab() {
        click(contactUsButton);
        return new ContactUsPage(driver);
    }

    public boolean isSignupLoginButtonDisplayed() {
        return isElementDisplayed(signupLoginButton);
    }

    public String getLoginMessage() {
        return driver.findElement(loginMessage).getText();
    }

}
