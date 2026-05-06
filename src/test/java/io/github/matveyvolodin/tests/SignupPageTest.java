package io.github.matveyvolodin.tests;

import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import io.github.matveyvolodin.pages.AccountCreatedPage;
import io.github.matveyvolodin.pages.AccountDeletedPage;
import io.github.matveyvolodin.pages.component.HeaderMenuComponent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignupPageTest extends BaseTest {

    private final User user = UserFactory.getRandomUser();

    @Test
    @Description("Create new User account, delete it and verify that account was created and deleted successfully")
    public void testCreateDeleteUserAccount() {
        AccountCreatedPage accountCreatedPage = new HeaderMenuComponent(driver)
                .clickSignupLoginButton()
                .fillNameInSignupForm(user.getName())
                .fillEmailAddressInSignupForm(user.getEmail())
                .clickSignupButton()
                .fillAllMandatoryFields(user)
                .clickCreateAccountButton();

        Allure.step("Verify that account was created successfully");
        Assert.assertEquals(accountCreatedPage.getSuccessMessage(), "ACCOUNT CREATED!");

        accountCreatedPage.clickContinueButton();

        AccountDeletedPage accountDeletedPage = new HeaderMenuComponent(driver)
                .clickDeleteAccountButton();

        Allure.step("Verify that account was deleted successfully");
        Assert.assertEquals(accountDeletedPage.getSuccessMessage(), "ACCOUNT DELETED!");
    }
}

