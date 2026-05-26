package io.github.matveyvolodin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    private final By loginMessage = By.xpath("//a[contains(.,'Logged in as')]");

    public MainPage(WebDriver driver) {
        super(driver);
        closeGoogleSurveyIfPresent();
    }

    public String getLoginMessage() {
        return driver.findElement(loginMessage).getText();
    }
}
