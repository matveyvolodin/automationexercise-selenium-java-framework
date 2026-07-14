package io.github.matveyvolodin.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage{

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    private final By continueButton = By.cssSelector("[data-qa='continue-button']");
    private final By successMessage = By.cssSelector("[data-qa='account-created']");

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    @Step("Click the Continue button on the Account Created page")
    public MainPage clickContinueButton() {
        click(continueButton);
        return new MainPage(driver);
    }
}
