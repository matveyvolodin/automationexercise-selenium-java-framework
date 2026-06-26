package io.github.matveyvolodin.api.tests.data;

import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import org.testng.annotations.DataProvider;

public class AccountApiTestData {

    public static final User TEST_USER = UserFactory.getRandomUser();

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
}
