package io.github.matveyvolodin.api.tests;

import io.github.matveyvolodin.api.model.AccountResponse;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountApiTest extends BaseApiTest{


    @Test
    @Description("Verify that a new user can be created successfully via the API")
    public void testCreateAccount() {
        AccountResponse response = accountApiClient.createAccount(testUser);
        Allure.addAttachment("Test User", testUser.getEmail() + " / " + testUser.getPassword());

        Allure.step("Verify the response code '201'", () ->
        Assert.assertEquals(response.getResponseCode(),201)
        );
        Allure.step("Verify the response message 'User created!'", () ->
        Assert.assertEquals(response.getMessage(), "User created!")
        );
    }

    @Test(dependsOnMethods = "testCreateAccount")
    @Description("Verify that an existing user can be deleted successfully via the API")
    public void testDeleteAccount() {
        AccountResponse response = accountApiClient.deleteAccount(testUser);

        //Service returns 200 if deleted successfully, but it should be 204.
        Allure.step("Verify the response code '200'", () ->
        Assert.assertEquals(response.getResponseCode(),200)
        );
        Allure.step("Verify the response message 'Account deleted!'", () ->
        Assert.assertEquals(response.getMessage(), "Account deleted!")
        );
    }

}
