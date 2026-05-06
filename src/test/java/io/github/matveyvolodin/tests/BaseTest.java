package io.github.matveyvolodin.tests;

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

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
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
}