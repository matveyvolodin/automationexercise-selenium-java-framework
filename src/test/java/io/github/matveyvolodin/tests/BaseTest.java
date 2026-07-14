package io.github.matveyvolodin.tests;

import io.github.matveyvolodin.model.User;
import io.github.matveyvolodin.pages.MainPage;
import io.github.matveyvolodin.pages.components.HeaderMenuComponent;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;


public abstract class BaseTest {

    protected WebDriver driver;
    protected String baseUrl = "https://automationexercise.com";

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--host-rules=MAP pagead2.googlesyndication.com 127.0.0.1");

        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Allure.addAttachment(
                    "Screenshot on failure",
                    new ByteArrayInputStream(
                            ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
                    )
            );
        }
        if (driver != null) {
            driver.quit();
        }
    }

    protected MainPage loginAs(User user) {
        return new HeaderMenuComponent(driver)
                .clickSignupLoginTab()
                .fillEmailAddressInLoginForm(user.getEmail())
                .fillPasswordInLoginForm(user.getPassword())
                .clickLoginButtonExpectedSuccess();
    }
}