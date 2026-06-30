package io.github.matveyvolodin.api.tests.data;

import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import org.testng.annotations.DataProvider;

public class AccountApiTestData {

    public static final User TEST_USER = UserFactory.getRandomUser();
    public static final User DELETE_TEST_USER = UserFactory.getRandomUser();

    @DataProvider(name = "registerUserData")
    public static Object[][] registerUserData() {
        return new Object[][] {
                { TEST_USER, "createAccount",              201, "User created!"                                                },
                { TEST_USER, "createAccountWithoutEmail",  400, "Bad request, email parameter is missing in POST request."    },
                { TEST_USER, "createAccountWithoutPassword", 400, "Bad request, password parameter is missing in POST request." },
                { TEST_USER, "createAccountWithoutName",   400, "Bad request, name parameter is missing in POST request."     },
                // API returns 400 instead of expected 409 for duplicate email
                { TEST_USER, "createAccount",              400, "Email already exists!"                                       },
        };
    }

    @DataProvider(name = "deleteUserData")
    public static Object[][] deleteUserData() {
        return new Object[][]{
                {DELETE_TEST_USER, "deleteAccountWithoutEmail", 400, "Bad request, email parameter is missing in DELETE request."},
                {DELETE_TEST_USER, "deleteAccountWithoutPassword", 400, "Bad request, password parameter is missing in DELETE request."},
                // API returns 404 instead of expected 403 for wrong credentials
                {DELETE_TEST_USER, "deleteAccountWrongPassword", 404, "Account not found!"},
                {DELETE_TEST_USER, "deleteAccount", 200, "Account deleted!" },
        };
    }
}
