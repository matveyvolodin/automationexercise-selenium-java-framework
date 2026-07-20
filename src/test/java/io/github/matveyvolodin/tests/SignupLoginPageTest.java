package io.github.matveyvolodin.tests;

import io.github.matveyvolodin.api.client.AccountApiClient;
import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import io.github.matveyvolodin.pages.MainPage;
import io.github.matveyvolodin.pages.SignupLoginPage;
import io.github.matveyvolodin.pages.components.HeaderMenuComponent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Map;

public class SignupLoginPageTest extends BaseTest {

    private User existingUser;
    private User secondUser;
    private final AccountApiClient accountApiClient = new AccountApiClient();
    private static final String INVALID_CREDENTIALS_MESSAGE =
            "Your email or password is incorrect!";
    private static final String EMAIL_ALREADY_EXIST = "Email Address already exist!";

    @BeforeClass
    public void createUser() {
        existingUser = UserFactory.getRandomUser();
        accountApiClient.createAccount(existingUser);
        secondUser = UserFactory.getSecondUser();
        accountApiClient.createAccount(secondUser);
    }

    @AfterClass
    public void deleteUser() {
        accountApiClient.deleteAccount(existingUser);
        accountApiClient.deleteAccount(secondUser);
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"testLoginWithWrongPassvord", existingUser.getEmail(), "WrongPassword123!",
                        "Your email or password is incorrect!"},
                {"testLoginWithWrongEmail", "nonexistingemail@gmail.com",
                        existingUser.getPassword(), "Your email or password is incorrect!"},
                {"testLoginWithCredsOfDifferentAccounts", existingUser.getEmail(),
                        secondUser.getPassword(), "Your email or password is incorrect!"}
        };
    }

    @Test
    @Description("Verify that placeholders are presented on the Signup/Login and Signup pages")
    public void testPlaceholders() {
        SignupLoginPage signupLoginPage = new HeaderMenuComponent(driver)
                .clickSignupLoginTab();

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

    @Test
    @Description("Verify that user can login with valid credentials")
    public void testLoginWithValidCredentials() {

        MainPage mainPage = new HeaderMenuComponent(driver)
                .clickSignupLoginTab()
                .fillEmailAddressInLoginForm(existingUser.getEmail())
                .fillPasswordInLoginForm(existingUser.getPassword())
                .clickLoginButtonExpectedSuccess();

        Allure.step("Verify that user is logged in and username is displayed", () ->
                Assert.assertEquals(
                        mainPage.header().getLoginMessage(),
                        "Logged in as %s".formatted(existingUser.getName())
                )
        );
    }

    @Test(dataProvider = "invalidCredentials")
    @Description("Verify that user cannot login with invalid credentials")
    public void testLoginWithInvalidCredentials(String scenarioName, String email, String password, String expectedMessage) {
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(scenarioName));

        SignupLoginPage signupLoginPage = new HeaderMenuComponent(driver)
                .clickSignupLoginTab()
                .fillEmailAddressInLoginForm(email)
                .fillPasswordInLoginForm(password)
                .clickLoginButtonExpectedFailure();

        Allure.step("Verify that user cannot login with invalid credentials: " + scenarioName, () ->
                Assert.assertEquals(signupLoginPage.getErrorMessage(), expectedMessage));
    }

    @Test
    @Description("Verify that the user cannot log in with a deleted account")
    public void testLoginWithDeletedAccount() {
        User userWithDeletedAccount = UserFactory.getRandomUser();
        accountApiClient.createAccount(userWithDeletedAccount);
        accountApiClient.deleteAccount(userWithDeletedAccount);

        SignupLoginPage signupLoginPage = new HeaderMenuComponent(driver)
                .clickSignupLoginTab()
                .fillEmailAddressInLoginForm(userWithDeletedAccount.getEmail())
                .fillPasswordInLoginForm(userWithDeletedAccount.getPassword())
                .clickLoginButtonExpectedFailure();

        Allure.step("Verify login error message is displayed", () ->
        Assert.assertEquals(signupLoginPage.getErrorMessage(), INVALID_CREDENTIALS_MESSAGE));
    }

    @Test
    @Description("Verify that the user cannot register with already registered email")
    public void testRegisterWithAlreadyRegisteredEmail() {
        SignupLoginPage signupLoginPage = new HeaderMenuComponent(driver)
                .clickSignupLoginTab()
                .fillEmailAddressInSignupForm(existingUser.getEmail())
                .fillNameInSignupForm(existingUser.getName())
                .clickSignupButtonExpectedFailure();

        Allure.step("Verify signup error message is displayed");
        Assert.assertEquals(signupLoginPage.getErrorMessage(), EMAIL_ALREADY_EXIST);
    }
}
