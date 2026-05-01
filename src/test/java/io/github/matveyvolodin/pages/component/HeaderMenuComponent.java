package io.github.matveyvolodin.pages.component;

import io.github.matveyvolodin.pages.AccountDeletedPage;
import io.github.matveyvolodin.pages.BasePage;
import io.github.matveyvolodin.pages.SignupLoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderMenuComponent extends BasePage {

    private final By signupLoginButton = By.xpath("//a[@href='/login']");
    private final By deleteAccountButton = By.xpath("//a[@href='/delete_account']");


    public HeaderMenuComponent(WebDriver driver) {
        super(driver);
    }

    @Step("Click the 'Signup/Login' button")
    public SignupLoginPage clickSignupLoginButton() {
        click(signupLoginButton);
        return new SignupLoginPage(driver);
    }

    @Step("Click the 'Delete Account' button")
    public AccountDeletedPage clickDeleteAccountButton() {
        click(deleteAccountButton);
        return new AccountDeletedPage(driver);
    }


}
