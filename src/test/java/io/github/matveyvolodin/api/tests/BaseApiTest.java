package io.github.matveyvolodin.api.tests;

import io.github.matveyvolodin.api.client.AccountApiClient;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected AccountApiClient accountApiClient;

    @BeforeClass
    public void setUp() {
        accountApiClient = new AccountApiClient();
    }
}
