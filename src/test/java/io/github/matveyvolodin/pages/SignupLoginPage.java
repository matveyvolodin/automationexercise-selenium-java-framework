package io.github.matveyvolodin.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SignupLoginPage  extends BasePage {

    private final By nameField = By.xpath("//input[@data-qa='signup-name']");
    private final By emailAddressField = By.xpath("//input[@data-qa='signup-email']");
    private final By signupButton = By.xpath("//button[@data-qa='signup-button']");

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





}
