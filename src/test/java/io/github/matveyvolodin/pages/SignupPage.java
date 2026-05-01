package io.github.matveyvolodin.pages;

import io.github.matveyvolodin.model.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    private final By nameField = By.cssSelector("[data-qa='name']");
    private final By emailField = By.cssSelector("[data-qa='email']");
    private final By passwordField = By.cssSelector("[data-qa='password']");
    private final By mrTitle = By.cssSelector("#id_gender1");
    private final By mrsTitle = By.cssSelector("#id_gender2");
    private final By newsletterCheckbox = By.cssSelector("#newsletter");
    private final By specialOffersCheckbox = By.cssSelector("#optin");
    private final By dayOfBirthDropdown = By.cssSelector("#days");
    private final By monthOfBirthDropdown = By.cssSelector("#months");
    private final By yearOfBirthDropdown = By.cssSelector("#years");
    private final By firstNameField = By.cssSelector("#first_name");
    private final By lastNameField = By.cssSelector("#last_name");
    private final By companyField = By.cssSelector("#company");
    private final By addressField = By.cssSelector("[data-qa='address']");
    private final By address2Field = By.cssSelector("[data-qa='address2']");
    private final By countryDropdown = By.cssSelector("#country");
    private final By stateField = By.cssSelector("[data-qa='state']");
    private final By cityField = By.cssSelector("[data-qa='city']");
    private final By zipcodeField = By.cssSelector("[data-qa='zipcode']");
    private final By mobileNumberField = By.cssSelector("[data-qa='mobile_number']");
    private final By createAccountButton = By.cssSelector("[data-qa='create-account']");


    public SignupPage(WebDriver driver) {
        super(driver);
    }

    @Step("Select the 'Title' section radiobutton")
    public SignupPage selectTitle(String title) {
        if (title.equals("Mr")) {
            click(mrTitle);
        } else {
            click(mrsTitle);
        }
        return this;
    }

    @Step("Fill the 'Password' text field")
    public SignupPage fillPassword(String password) {
        fill(passwordField, password);
        return this;
    }

    @Step("Select the 'Day of birth' dropdown option")
    public SignupPage selectDayOfBirth(String day) {
        selectByVisibleText(dayOfBirthDropdown, day);
        return this;
    }

    @Step("Select the 'Month of birth' dropdown option")
    public SignupPage selectMonthOfBirth(String month) {
        selectByVisibleText(monthOfBirthDropdown, month);
        return this;
    }

    @Step("Select the 'Year of birth' dropdown option")
    public SignupPage selectYearOfBirth(String year) {
        selectByVisibleText(yearOfBirthDropdown, year);
        return this;
    }
    
    @Step("Handle newsletter checkbox")
    public SignupPage handleNewsletter(boolean check) {
        if (check) click(newsletterCheckbox);
        return this;
    }

    @Step("Handle special offers checkbox")
    public SignupPage handleSpecialOffers(boolean check) {
        if (check) click(specialOffersCheckbox);
        return this;
    }

    @Step("Fill the 'First name' text field")
    public SignupPage fillFirstName(String firstName) {
        fill(firstNameField, firstName);
        return this;
    }

    @Step("Fill the 'Last name' text field")
    public SignupPage fillLastName(String lastName) {
        fill(lastNameField, lastName);
        return this;
    }

    @Step("Fill the 'Address' text field")
    public SignupPage fillAddress(String address) {
        fill(addressField, address);
        return this;
    }

    @Step("Select the 'Country' dropdown option")
    public SignupPage selectCountry(String country) {
        selectByVisibleText(countryDropdown, country);
        return this;
    }

    @Step("Select the 'State' text field")
    public SignupPage fillState(String state) {
        fill(stateField, state);
        return this;
    }

    @Step("Select the 'City' text field")
    public SignupPage fillCity(String city) {
        fill(cityField, city);
        return this;
    }

    @Step("Select the 'Zipcode' text field")
    public SignupPage fillZipcode(String zipcode) {
        fill(zipcodeField, zipcode);
        return this;
    }

    @Step("Select the 'Mobile number' text field")
    public SignupPage fillMobileNumber(String mobileNumber) {
        fill(mobileNumberField, mobileNumber);
        return this;
    }

    @Step("Fill all mandatory fields in the signup form")
    public SignupPage fillAllMandatoryFields(User user) {
        fillPassword(user.getPassword());
        fillFirstName(user.getFirstName());
        fillLastName(user.getLastName());
        fillAddress(user.getAddress());
        selectCountry(user.getCountry());
        fillState(user.getState());
        fillCity(user.getCity());
        fillZipcode(user.getZipCode());
        fillMobileNumber(user.getMobileNumber());

        return this;
    }

    @Step("Click the 'Create Account' button")
    public AccountCreatedPage clickCreateAccountButton() {
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }

}
