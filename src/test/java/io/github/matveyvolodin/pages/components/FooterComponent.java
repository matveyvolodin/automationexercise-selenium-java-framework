package io.github.matveyvolodin.pages.components;

import io.github.matveyvolodin.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterComponent extends BasePage {

    private final By emailInputField = By.cssSelector("#susbscribe_email");
    private final By submitButton = By.cssSelector("#subscribe");
    private final By successMessage = By.cssSelector(".alert-success.alert");


    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public FooterComponent fillEmailInputField(String email) {
        fill(emailInputField, email);
        return this;
    }

    public FooterComponent clickSubmitButton() {
        click(submitButton);
        return this;
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}
