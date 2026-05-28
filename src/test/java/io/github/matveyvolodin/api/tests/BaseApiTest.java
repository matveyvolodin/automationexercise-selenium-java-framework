package io.github.matveyvolodin.api.tests;

import io.github.matveyvolodin.api.client.AccountApiClient;
import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.model.UserFactory;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected AccountApiClient accountApiClient;
    protected User testUser;
    protected User secondTestUser;

    @BeforeClass
    public void setUp() {
        accountApiClient = new AccountApiClient();
        testUser = UserFactory.getRandomUser();
        secondTestUser = UserFactory.getSecondUser();
    }
}
