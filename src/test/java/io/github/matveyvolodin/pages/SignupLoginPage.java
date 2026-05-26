package io.github.matveyvolodin.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;


public class SignupLoginPage  extends BasePage {

    private final By nameField = By.cssSelector("[data-qa='signup-name']");
    private final By emailAddressField = By.cssSelector("[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("[data-qa='signup-button']");
    private final By loginEmailAddressField = By.cssSelector("[data-qa='login-email']");
    private final By loginPasswordField = By.cssSelector("[data-qa='login-password']");
    private final By loginButton = By.cssSelector("[data-qa='login-button']");
    private final By subscribeEmail = By.id("susbscribe_email");
    private final By loginErrorMessage = By.cssSelector("p[style='color: red;']");

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
    public SignupLoginPage clickLoginButtonExpectedFailure() {
        click(loginButton);
        return this;
    }

    @Step("Click the 'Login' button")
    public MainPage clickLoginButtonExpectedSuccess() {
        click(loginButton);
        return new MainPage(driver);
    }

    public Map<String, String> getPlaceholders() {

        Map<String, String> map = new HashMap<>();

        map.put("login-email", getPlaceholder(loginEmailAddressField));
        map.put("login-password", getPlaceholder(loginPasswordField));
        map.put("signup-name", getPlaceholder(nameField));
        map.put("signup-email", getPlaceholder(emailAddressField));
        map.put("susbscribe_email", getPlaceholder(subscribeEmail));

        return map;
    }

    @Step("Get login error message")
    public String getLoginErrorMessage() {
        return driver.findElement(loginErrorMessage).getText();
    }
}
