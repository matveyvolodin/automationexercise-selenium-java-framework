package io.github.matveyvolodin.pages;

import io.github.matveyvolodin.pages.components.FooterComponent;
import io.github.matveyvolodin.pages.components.HeaderMenuComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static boolean surveyDismissed = false;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Default wait of 10 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        handleConsentPopup();
        handleAdPopupIfPresent();
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void fill(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    protected void selectByVisibleText(By locator, String text) {
        new Select(driver.findElement(locator)).selectByVisibleText(text);
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected void closeGoogleSurveyIfPresent() {
        if (surveyDismissed) return;
        try {
            WebElement close = driver.findElement(By.cssSelector("#dismiss-button"));
            if (close.isDisplayed()) {
                close.click();
                surveyDismissed = true;
            }
        } catch (NoSuchElementException ignored) {
        }
    }

    private void handleConsentPopup() {
        try {
            WebElement consentBtn = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//p[text()='Consent']")
                    ));
            consentBtn.click();
        } catch (TimeoutException ignored) {
        }
    }

    private void handleAdPopupIfPresent() {
        try {
            WebElement close = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.cssSelector("#dismiss-button")
                    ));
            close.click();
        } catch (TimeoutException ignored) {
            System.out.println(driver.getPageSource().substring(0, 2000));
        }
    }

    protected String getPlaceholder(By locator) {
        return driver.findElement(locator).getAttribute("placeholder");
    }

    protected boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public HeaderMenuComponent header() {
        return new HeaderMenuComponent(driver);
    }

    public FooterComponent footer() {
        return new FooterComponent(driver);
    }
}
