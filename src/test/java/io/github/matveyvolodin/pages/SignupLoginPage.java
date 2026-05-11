package io.github.matveyvolodin.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SignupLoginPage  extends BasePage {

    private final By nameField = By.cssSelector("[data-qa='signup-name']");
    private final By emailAddressField = By.cssSelector("[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("[data-qa='signup-button']");
    private final By loginEmailAddressField = By.cssSelector("[data-qa='login-email']");
    private final By loginPasswordField = By.cssSelector("[data-qa='login-password']");
    private final By loginButton = By.cssSelector("[data-qa='login-button']");

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill the 'Name' text field")
    public SignupLoginPage fillNameInSignupForm(String name) {
        fill(nameField, name);
        return this;
    }

    @Step("Fill the 'Email Address' text field")
    public SignupLoginPage fillEmailAddressInSignupForm(String emailAddress) {
        fill(emailAddressField, emailAddress);
        return this;
    }

    @Step("Click the 'Signup' button")
    public SignupPage clickSignupButton() {
        click(signupButton);
        return new SignupPage(driver);
    }

    @Step
    public SignupLoginPage fillEmailAddressInLoginForm(String emailAddress) {
        fill(loginEmailAddressField, emailAddress);
        return this;
    }
    @Step
    public SignupLoginPage fillPasswordInLoginForm(String password) {
        fill(loginPasswordField, password);
        return this;
    }

    @Step("Click the 'Login' button")
    public MainPage clickLoginButton() {
        click(loginButton);
        return new MainPage(driver);
    }

}
