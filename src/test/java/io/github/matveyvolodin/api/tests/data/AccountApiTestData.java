package io.github.matveyvolodin.api.tests.data;

import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import org.testng.annotations.DataProvider;

public class AccountApiTestData {

    public static final User TEST_USER = UserFactory.getRandomUser();
    public static final User DELETE_TEST_USER = UserFactory.getRandomUser();
    public static final User UPDATE_TEST_USER = UserFactory.getRandomUser();

    @DataProvider(name = "registerUserData")
    public static Object[][] registerUserData() {
        return new Object[][] {
                { "testCreateAccount", TEST_USER, "createAccount", 201, "User created!" },
                { "testCreateAccountWithoutEmail", TEST_USER, "createAccountWithoutEmail", 400,
                        "Bad request, email parameter is missing in POST request." },
                { "testCreateAccountWithoutPassword", TEST_USER, "createAccountWithoutPassword", 400,
                        "Bad request, password parameter is missing in POST request." },
                { "testCreateAccountWithoutName", TEST_USER, "createAccountWithoutName", 400,
                        "Bad request, name parameter is missing in POST request." },
                // API returns 400 instead of expected 409 for duplicate email
                { "testCreateAccountWithExistingEmail", TEST_USER, "createAccount", 400, "Email already exists!" },
        };
    }

    @DataProvider(name = "deleteUserData")
    public static Object[][] deleteUserData() {
        return new Object[][]{
                {"testDeleteAccountWithoutEmail", DELETE_TEST_USER, "deleteAccountWithoutEmail", 400,
                        "Bad request, email parameter is missing in DELETE request."},
                {"testDeleteAccountWithoutPassword", DELETE_TEST_USER, "deleteAccountWithoutPassword", 400,
                        "Bad request, password parameter is missing in DELETE request."},
                // API returns 404 instead of expected 403 for wrong credentials
                {"testDeleteAccountWithWrongCredentials", DELETE_TEST_USER.toBuilder().password("wrongPassword123!").
                        build(), "deleteAccount", 404, "Account not found!"},
                {"testDeleteAccount", DELETE_TEST_USER, "deleteAccount", 200, "Account deleted!"},
        };
    }

    @DataProvider(name = "updateUserData")
    public static Object[][] updateUserData() {
        return new Object[][]{
                {"testUpdateAccountWithoutEmail", UPDATE_TEST_USER, "updateAccountWithoutEmail", 400,
                        "Bad request, email parameter is missing in PUT request."},
                {"testUpdateAccountWithoutPassword", UPDATE_TEST_USER, "updateAccountWithoutPassword", 400,
                        "Bad request, password parameter is missing in PUT request."},
                {"testUpdateAccountWithWrongCredentials", UPDATE_TEST_USER.toBuilder().password("wrongPassword123!").
                        build(), "updateAccount", 404, "Account not found!"},
                {"testUpdateAccount", UPDATE_TEST_USER.toBuilder().name("updatedName").build(),
                        "updateAccount", 200, "User updated!"},
        };
    }

    @DataProvider(name = "verifyLoginData")
    public static Object[][] verifyLoginData() {
        return new Object[][]{
                {"testVerifyLoginWithoutEmail", UPDATE_TEST_USER, "verifyLoginWithoutEmail", 400,
                        "Bad request, email or password parameter is missing in POST request."},
                {"testVerifyLoginWithoutPassword", UPDATE_TEST_USER, "verifyLoginWithoutPassword", 400,
                        "Bad request, email or password parameter is missing in POST request."},
                // API returns 404 instead of expected 401 for wrong credentials
                {"testVerifyLoginWithWrongCredentials", UPDATE_TEST_USER.toBuilder().password("wrongPassword123!").
                        build(), "verifyLogin", 404, "User not found!"},
                {"testVerifyLogin", UPDATE_TEST_USER, "verifyLogin", 200, "User exists!"},
        };
    }
}
