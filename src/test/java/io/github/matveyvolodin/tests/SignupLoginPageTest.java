package io.github.matveyvolodin.tests;

import io.github.matveyvolodin.pages.SignupLoginPage;
import io.github.matveyvolodin.pages.component.HeaderMenuComponent;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class SignupLoginPageTest extends BaseTest {


    @Test
    @Description("Verify that placeholders are presented on the Signup/Login and Signup pages")
    public void testPlaceholders() {
        SignupLoginPage signupLoginPage = new HeaderMenuComponent(driver)
                .clickSignupLoginButton();

        Map<String, String> expected = Map.of(
                "login-email", "Email Address",
                "login-password", "Password",
                "signup-name", "Name",
                "signup-email", "Email Address",
                "susbscribe_email", "Your email address"
        );

        Allure.step("Verify that placeholders are presented on the Signup/Login page");
        Assert.assertEquals(signupLoginPage.getPlaceholders(), expected);
    }
}
