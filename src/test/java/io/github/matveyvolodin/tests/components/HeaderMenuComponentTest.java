package io.github.matveyvolodin.tests.components;

import io.github.matveyvolodin.pages.components.HeaderMenuComponent;
import io.github.matveyvolodin.tests.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HeaderMenuComponentTest extends BaseTest {

    private static final String PRODUCTS_URL = "/products";
    private static final String CART_URL = "/view_cart";
    private static final String HOME_URL = "/";
    public static final String SIGNUP_LOGIN_URL = "/login";
    public static final String TEST_CASES_URL = "/test_cases";
    public static final String API_TESTING_URL = "/api_list";
    public static final String VIDEO_TUTORIALS_URL = "https://www.youtube.com/c/AutomationExercise";
    public static final String CONTACT_US_URL = "/contact_us";

    @Test
    @Description("Verify that header tabs lead to the correct web pages")
    public void testHeaderTabsRedirections() {
        HeaderMenuComponent header = new HeaderMenuComponent(driver);

        header.clickProductsTab();
        Allure.step("Verify that user was redirected to the Products page after clicking Products tab");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + PRODUCTS_URL);

        header.clickLogo();
        Allure.step("Verify that user was redirected to the main page after clicking the logo");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + HOME_URL);

        header.clickCartTab();
        Allure.step("Verify that user was redirected to the Cart page after clicking Cart tab");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + CART_URL);

        header.clickHomeTab();
        Allure.step("Verify that user was redirected to the main page after clicking Home tab");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + HOME_URL);

        header.clickSignupLoginTab();
        Allure.step("Verify that user was redirected to the Signup/Login page after clicking Signup/Login tab");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + SIGNUP_LOGIN_URL);

        header.clickTestCasesTab();
        Allure.step("Verify that user was redirected to the Test Cases page after clicking Test Cases tab");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + TEST_CASES_URL);

        header.clickApiTestingTab();
        Allure.step("Verify that user was redirected to the API Testing page after clicking API Testing tab");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + API_TESTING_URL);

        header.clickContactUsTab();
        Allure.step("Verify that user was redirected to the Contact Us page after clicking Contact Us tab");
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + CONTACT_US_URL);

        header.clickVideoTutorialsTab();
        Allure.step("Verify that user was redirected to the Video Tutorials page after clicking Video Tutorials tab");
        Assert.assertEquals(driver.getCurrentUrl(), VIDEO_TUTORIALS_URL);
    }
}
