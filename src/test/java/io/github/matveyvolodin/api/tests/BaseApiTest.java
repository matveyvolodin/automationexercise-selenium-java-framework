package io.github.matveyvolodin.api.tests;

import io.github.matveyvolodin.api.client.AccountApiClient;
import io.github.matveyvolodin.model.User;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected AccountApiClient accountApiClient;

    @BeforeClass
    public void setUp() {
        accountApiClient = new AccountApiClient();
    }

    protected void safeDeleteAccount(User user) {
        try {
            accountApiClient.deleteAccount(user);
        } catch (Exception e) {
            // Account may have already been deleted or not created — ignoring
        }
    }
}
