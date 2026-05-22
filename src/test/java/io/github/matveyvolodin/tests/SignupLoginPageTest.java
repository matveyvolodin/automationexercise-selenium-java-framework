package io.github.matveyvolodin.tests;

import io.github.matveyvolodin.api.client.AccountApiClient;
import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import io.github.matveyvolodin.pages.SignupLoginPage;
import io.github.matveyvolodin.pages.component.HeaderMenuComponent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class SignupLoginPageTest extends BaseTest {

    private User existingUser;
    private User secondUser;
    private final AccountApiClient accountApiClient = new AccountApiClient();

    @BeforeClass
    public void createExistingUser() {
        existingUser = UserFactory.getRandomUser();
        accountApiClient.createAccount(existingUser);
        secondUser = UserFactory.getSecondUser();
        accountApiClient.createAccount(secondUser);
    }

    @AfterClass
    public void deleteExistingUser() {
        accountApiClient.deleteAccount(existingUser);
        accountApiClient.deleteAccount(secondUser);
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"Login with registered email and wrong password", existingUser.getEmail(), "WrongPassword123!",
                        "Your email or password is incorrect!"},
                {"Login with non-registered email and valid password", "nonexistingemail@gmail.com",
                        existingUser.getPassword(), "Your email or password is incorrect!"},
                {"Login with email and password from different registered accounts", existingUser.getEmail(),
                        secondUser.getPassword(), "Your email or password is incorrect!"}
        };
    }

    @Test
    @Description("Verify that placeholders are presented on the Signup/Login and Signup pages")
    public void testPlaceholders() {
        SignupLoginPage signupLoginPage = new HeaderMenuComponent(driver)
                .clickSignupLoginButton();

        Map<String, String> expected = Map.of(
                "login-email", "Email Address",
                "login-password", "Password",
                "signup-name", "Name",
                "signup-email", "Email Address",
                "susbscribe_email", "Your email address"
        );

        Allure.step("Verify that placeholders are presented on the Signup/Login page");
        Assert.assertEquals(signupLoginPage.getPlaceholders(), expected);
    }

    @Test(dataProvider = "invalidCredentials")
    @Description("Verify that user cannot login with invalid credentials")
    public void testLoginWithInvalidCredentials(String description, String email, String password, String expectedMessage) {
        SignupLoginPage signupLoginPage = new HeaderMenuComponent(driver)
                .clickSignupLoginButton()
                .fillEmailAddressInLoginForm(email)
                .fillPasswordInLoginForm(password)
                .clickLoginButton();

        Allure.step("Verify that user cannot login with invalid credentials: " + description, () ->
                Assert.assertEquals(signupLoginPage.getLoginErrorMessage(), expectedMessage));
    }
}
