package io.github.matveyvolodin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage{

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    private final By successMessage = By.cssSelector("[data-qa='account-deleted']");

    public String getSuccessMessage() {
        return getText(successMessage);
    }

}
