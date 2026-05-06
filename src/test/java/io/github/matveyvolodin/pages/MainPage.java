package io.github.matveyvolodin.pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver) {
        super(driver);
        closeGoogleSurveyIfPresent();
    }


}
