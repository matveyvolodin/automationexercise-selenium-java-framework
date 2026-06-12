package io.github.matveyvolodin.tests.components;

import io.github.matveyvolodin.pages.MainPage;
import io.github.matveyvolodin.tests.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FooterComponentTest extends BaseTest {

    private final String expectedSuccessMessage = "You have been successfully subscribed!";

    @Test
    @Description("Verify that users can subscribe to the newsletter with a valid email address")
    public void testSubscribeToNewsletter() {
        String userEmail = "test@test.com";

        String successMessage = new MainPage(driver).footer()
                .fillEmailInputField(userEmail)
                .clickSubmitButton()
                .getSuccessMessage();

        Allure.step("Verify the success message 'You have been successfully subscribed!' is displayed", () ->
                Assert.assertEquals(successMessage, expectedSuccessMessage));
    }
}
