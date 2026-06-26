package io.github.matveyvolodin.api.tests;

import io.github.matveyvolodin.api.model.AccountResponse;
import io.github.matveyvolodin.api.tests.data.AccountApiTestData;
import io.github.matveyvolodin.model.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AccountApiTest extends BaseApiTest{

    @AfterClass
    public void tearDown() {
        accountApiClient.deleteAccount(AccountApiTestData.TEST_USER);
    }

    @Test(dataProvider = "registerUserData", dataProviderClass = AccountApiTestData.class)
    @Description("Verify POST /createAccount endpoint responses for different input scenarios")
    public void testCreateAccount(User user, String method, int expectedCode, String expectedMessage) {
        AccountResponse response = switch (method) {
            case "createAccountWithoutEmail"    -> accountApiClient.createAccountWithoutEmail(user);
            case "createAccountWithoutPassword" -> accountApiClient.createAccountWithoutPassword(user);
            case "createAccountWithoutName"     -> accountApiClient.createAccountWithoutName(user);
            default                             -> accountApiClient.createAccount(user);
        };

        Allure.addAttachment("Test User", user.getEmail() + " / " + user.getPassword());

        Allure.step("Verify the response code '" + expectedCode + "'", () ->
                Assert.assertEquals(response.getResponseCode(), expectedCode)
        );
        Allure.step("Verify the response message '" + expectedMessage + "'", () ->
                Assert.assertEquals(response.getMessage(), expectedMessage)
        );
    }

    @Test
    @Description("Verify that PUT method is not allowed for POST /createAccount endpoint")
    public void testCreateAccountUnsupportedMethod() {
        AccountResponse response = accountApiClient.createAccountWithPut(AccountApiTestData.TEST_USER);

        Allure.step("Verify the response code '405'", () ->
                Assert.assertEquals(response.getResponseCode(), 405)
        );
        Allure.step("Verify the response message", () ->
                Assert.assertEquals(response.getMessage(), "Method \"PUT\" not allowed.")
        );
    }

    @Test
    @Description("Verify that an existing user can be deleted successfully via the API")
    public void testDeleteAccount() {
        AccountResponse response = accountApiClient.deleteAccount(AccountApiTestData.TEST_USER);

        //Service returns 200 if deleted successfully, but it should be 204.
        Allure.step("Verify the response code '200'", () ->
        Assert.assertEquals(response.getResponseCode(),200)
        );
        Allure.step("Verify the response message 'Account deleted!'", () ->
        Assert.assertEquals(response.getMessage(), "Account deleted!")
        );
    }

}
