package io.github.matveyvolodin.tests;

import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import io.github.matveyvolodin.pages.AccountCreatedPage;
import io.github.matveyvolodin.pages.components.HeaderMenuComponent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupPageTest extends BaseTest {

    private final User user = UserFactory.getRandomUser();

    @Test
    @Description("Verify that user account was created successfully and user was redirected to the main page")
    public void testCreateUserAccount() {
        AccountCreatedPage accountCreatedPage = new HeaderMenuComponent(driver)
                .clickSignupLoginTab()
                .fillNameInSignupForm(user.getName())
                .fillEmailAddressInSignupForm(user.getEmail())
                .clickSignupButton()
                .fillAllMandatoryFields(user)
                .clickCreateAccountButton();

        Allure.step("Verify that account was created successfully");
        Assert.assertEquals(accountCreatedPage.getSuccessMessage(), "ACCOUNT CREATED!");

        accountCreatedPage.clickContinueButton();

        Allure.step("Verify that user was redirected to the main page");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
    }
}

