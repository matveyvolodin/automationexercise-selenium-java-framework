package io.github.matveyvolodin.api.client;

import io.github.matveyvolodin.api.config.ApiConfig;
import io.github.matveyvolodin.api.model.AccountResponse;
import io.github.matveyvolodin.model.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AccountApiClient {

    private static final String CREATE_ACCOUNT_ENDPOINT = "/api/createAccount";
    private static final String DELETE_ACCOUNT_ENDPOINT = "/api/deleteAccount";

    @Step("Creating account for user: {user.email}")
    public AccountResponse createAccount(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("name",          user.getName())
                .formParam("email",         user.getEmail())
                .formParam("password",      user.getPassword())
                .formParam("title",         user.getTitle())
                .formParam("birth_date",    user.getDayOfBirth())
                .formParam("birth_month",   user.getMonthOfBirth())
                .formParam("birth_year",    user.getYearOfBirth())
                .formParam("firstname",     user.getFirstName())
                .formParam("lastname",      user.getLastName())
                .formParam("company",       user.getCompany())
                .formParam("address1",      user.getAddress())
                .formParam("address2",      user.getAddress2())
                .formParam("country",       user.getCountry())
                .formParam("zipcode",       user.getZipCode())
                .formParam("state",         user.getState())
                .formParam("city",          user.getCity())
                .formParam("mobile_number", user.getMobileNumber())
                .when()
                .post(CREATE_ACCOUNT_ENDPOINT)
                .then()
                .statusCode(200) // Service returns 200 instead of 201 for successful account creation
                .extract()
                .response();

        AccountResponse result = new AccountResponse();
        result.setResponseCode(response.jsonPath().getInt("responseCode"));
        result.setMessage(response.jsonPath().getString("message"));
        return result;
    }

    @Step("Deleting account for user: {user.email}")
    public AccountResponse deleteAccount(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email",    user.getEmail())
                .formParam("password", user.getPassword())
                .when()
                .delete(DELETE_ACCOUNT_ENDPOINT)
                .then()
                .statusCode(200) // Service returns 200 instead of 204 for successful account deletion
                .extract()
                .response();

        AccountResponse result = new AccountResponse();
        result.setResponseCode(response.jsonPath().getInt("responseCode"));
        result.setMessage(response.jsonPath().getString("message"));
        return result;
    }
}