package io.github.matveyvolodin.api.tests;

import io.github.matveyvolodin.api.model.AccountResponse;
import io.github.matveyvolodin.api.tests.data.AccountApiTestData;
import io.github.matveyvolodin.model.User;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountApiTest extends BaseApiTest{

    @BeforeClass
    public void setUp() {
        super.setUp();
        accountApiClient.createAccount(AccountApiTestData.DELETE_TEST_USER);
        accountApiClient.createAccount(AccountApiTestData.UPDATE_TEST_USER);
    }

    @AfterClass
    public void tearDown() {
        safeDeleteAccount(AccountApiTestData.TEST_USER);
        safeDeleteAccount(AccountApiTestData.DELETE_TEST_USER);
        safeDeleteAccount(AccountApiTestData.UPDATE_TEST_USER);
    }

    @Test(dataProvider = "registerUserData", dataProviderClass = AccountApiTestData.class)
    @Description("Verify POST /createAccount endpoint responses for different input scenarios")
    public void testCreateAccount(String scenarioName, User user, String method, int expectedCode, String expectedMessage) {
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(scenarioName));

        AccountResponse response = switch (method) {
            case "createAccountWithoutEmail" -> accountApiClient.createAccountWithoutEmail(user);
            case "createAccountWithoutPassword" -> accountApiClient.createAccountWithoutPassword(user);
            case "createAccountWithoutName" -> accountApiClient.createAccountWithoutName(user);
            default -> accountApiClient.createAccount(user);
        };

        Allure.addAttachment("Test User", user.getEmail() + " / " + user.getPassword());

        Allure.step("Verify the response code '" + expectedCode + "'", () ->
                Assert.assertEquals(response.getResponseCode(), expectedCode)
        );
        Allure.step("Verify the response message '" + expectedMessage + "'", () ->
                Assert.assertEquals(response.getMessage(), expectedMessage)
        );
    }

    // FIXME: API returns {"detail": "Method \"PUT\" not allowed."} instead of the standard
    //        {"responseCode": 405, "message": "..."} format used by other endpoints (e.g. /verifyLogin).
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

    @Test(dataProvider = "deleteUserData", dataProviderClass = AccountApiTestData.class)
    @Description("Verify DELETE /deleteAccount endpoint responses for different input scenarios")
    public void testDeleteAccount(String scenarioName, User user, String method, int expectedCode, String expectedMessage) {
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(scenarioName));

        AccountResponse response = switch (method) {
            case "deleteAccountWithoutEmail" -> accountApiClient.deleteAccountWithoutEmail(user);
            case "deleteAccountWithoutPassword" -> accountApiClient.deleteAccountWithoutPassword(user);
            default -> accountApiClient.deleteAccount(user);
        };

        Allure.step("Verify the response code '" + expectedCode + "'", () ->
        Assert.assertEquals(response.getResponseCode(),expectedCode)
        );
        Allure.step("Verify the response message '" + expectedMessage + "'", () ->
        Assert.assertEquals(response.getMessage(), expectedMessage)
        );
    }

    // FIXME: API returns {"detail": "Method \"POST\" not allowed."} instead of the standard
    //        {"responseCode": 405, "message": "..."} format used by other endpoints (e.g. /verifyLogin).
    @Test
    @Description("Verify that POST method is not allowed for DELETE /deleteAccount endpoint")
    public void testDeleteAccountUnsupportedMethod() {
        AccountResponse response = accountApiClient.deleteAccountWithPost(AccountApiTestData.DELETE_TEST_USER);

        Allure.step("Verify the response code '405'", () ->
                Assert.assertEquals(response.getResponseCode(), 405)
        );
        Allure.step("Verify the response message", () ->
                Assert.assertEquals(response.getMessage(), "Method \"POST\" not allowed.")
        );
    }

    @Test(dataProvider = "updateUserData", dataProviderClass = AccountApiTestData.class)
    @Description("Verify PUT /updateAccount endpoint responses for different input scenarios")
    public void testUpdateAccount(String scenarioName, User user, String method, int expectedCode,
                                  String expectedMessage) {
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(scenarioName));

        AccountResponse response = switch (method) {
            case "updateAccountWithoutEmail" -> accountApiClient.updateAccountWithoutEmail(user);
            case "updateAccountWithoutPassword" -> accountApiClient.updateAccountWithoutPassword(user);
            default -> accountApiClient.updateAccount(user);
        };

        Allure.step("Verify the response code '" + expectedCode + "'", () ->
                Assert.assertEquals(response.getResponseCode(), expectedCode)
        );
        Allure.step("Verify the response message '" + expectedMessage + "'", () ->
                Assert.assertEquals(response.getMessage(), expectedMessage)
        );
    }

    // FIXME: API returns {"detail": "Method \"POST\" not allowed."} instead of the standard
    //        {"responseCode": 405, "message": "..."} format used by other endpoints (e.g. /verifyLogin).
    @Test
    @Description("Verify that POST method is not allowed for PUT /updateAccount endpoint")
    public void testUpdateAccountUnsupportedMethod() {
        AccountResponse response = accountApiClient.updateAccountWithPost(AccountApiTestData.UPDATE_TEST_USER);

        Allure.step("Verify the response code '405'", () ->
                Assert.assertEquals(response.getResponseCode(), 405)
        );

        Allure.step("Verify the response message", () ->
                Assert.assertEquals(response.getMessage(), "Method \"POST\" not allowed.")
        );
    }

    @Test(dataProvider = "verifyLoginData", dataProviderClass = AccountApiTestData.class)
    @Description("Verify POST /verifyLogin endpoint responses for different input scenarios")
    public void testVerifyLogin(String scenarioName, User user, String method, int expectedCode,
                                String expectedMessage) {
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(scenarioName));

        AccountResponse response = switch (method) {
            case "verifyLoginWithoutEmail" -> accountApiClient.verifyLoginWithoutEmail(user);
            case "verifyLoginWithoutPassword" -> accountApiClient.verifyLoginWithoutPassword(user);
            default -> accountApiClient.verifyLogin(user);
        };

        Allure.step("Verify the response code '" + expectedCode + "'", () ->
                Assert.assertEquals(response.getResponseCode(), expectedCode)
        );

        Allure.step("Verify the response message '" + expectedMessage + "'", () ->
                Assert.assertEquals(response.getMessage(), expectedMessage)
        );
    }

    @Test
    @Description("Verify that PUT method is not allowed for POST /verifyLogin endpoint")
    public void testVerifyLoginUnsupportedMethod() {
        AccountResponse response = accountApiClient.verifyLoginWithPut(AccountApiTestData.UPDATE_TEST_USER);

        Allure.step("Verify the response code '405'", () ->
                Assert.assertEquals(response.getResponseCode(), 405)
        );

        Allure.step("Verify the response message", () ->
                Assert.assertEquals(response.getMessage(), "This request method is not supported.")
        );
    }

    @Test(dataProvider = "getUserData", dataProviderClass = AccountApiTestData.class)
    @Description("Verify GET /getUserDetailByEmail endpoint responses for different input scenarios")
    public void testGetUser(String scenarioName, User user, String method, int expectedCode,
                            String expectedMessage) {
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName(scenarioName));

        AccountResponse response = switch (method) {
            case "getUserWithoutEmail" -> accountApiClient.getUserWithoutEmail();
            default -> accountApiClient.getUser(user);
        };

        Allure.step("Verify the response code '" + expectedCode + "'", () ->
                Assert.assertEquals(response.getResponseCode(), expectedCode)
        );

        if (expectedMessage != null) {
            Allure.step("Verify the response message '" + expectedMessage + "'", () ->
                    Assert.assertEquals(response.getMessage(), expectedMessage)
            );
        }
    }

    @Test
    @Description("Verify that POST method is not allowed for GET /getUserDetailByEmail endpoint")
    public void testGetUserUnsupportedMethod() {
        AccountResponse response = accountApiClient.getUserWithPost(AccountApiTestData.UPDATE_TEST_USER);

        Allure.step("Verify the response code '405'", () ->
                Assert.assertEquals(response.getResponseCode(), 405)
        );
        Allure.step("Verify the response message", () ->
                Assert.assertEquals(response.getMessage(), "This request method is not supported.")
        );
    }
}
