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
    private static final String UPDATE_ACCOUNT_ENDPOINT = "/api/updateAccount";
    private static final String VERIFY_LOGIN_ENDPOINT = "/api/verifyLogin";

    @Step("Creating account for user: {user.email}")
    public AccountResponse createAccount(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("name", user.getName())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getDayOfBirth())
                .formParam("birth_month", user.getMonthOfBirth())
                .formParam("birth_year", user.getYearOfBirth())
                .formParam("firstname", user.getFirstName())
                .formParam("lastname", user.getLastName())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipCode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobileNumber())
                .when()
                .post(CREATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Creating account without email field for user: {user.name}")
    public AccountResponse createAccountWithoutEmail(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("name", user.getName())
                // email formParam is intentionally omitted to test missing field validation
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getDayOfBirth())
                .formParam("birth_month", user.getMonthOfBirth())
                .formParam("birth_year", user.getYearOfBirth())
                .formParam("firstname", user.getFirstName())
                .formParam("lastname", user.getLastName())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipCode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobileNumber())
                .when()
                .post(CREATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Creating account without password field for user: {user.email}")
    public AccountResponse createAccountWithoutPassword(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("name", user.getName())
                .formParam("email", user.getEmail())
                // password formParam is intentionally omitted to test missing field validation
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getDayOfBirth())
                .formParam("birth_month", user.getMonthOfBirth())
                .formParam("birth_year", user.getYearOfBirth())
                .formParam("firstname", user.getFirstName())
                .formParam("lastname", user.getLastName())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipCode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobileNumber())
                .when()
                .post(CREATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Creating account without name field for user: {user.email}")
    public AccountResponse createAccountWithoutName(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                // name formParam is intentionally omitted to test missing field validation
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("title", user.getTitle())
                .formParam("birth_date", user.getDayOfBirth())
                .formParam("birth_month", user.getMonthOfBirth())
                .formParam("birth_year", user.getYearOfBirth())
                .formParam("firstname", user.getFirstName())
                .formParam("lastname", user.getLastName())
                .formParam("company", user.getCompany())
                .formParam("address1", user.getAddress())
                .formParam("address2", user.getAddress2())
                .formParam("country", user.getCountry())
                .formParam("zipcode", user.getZipCode())
                .formParam("state", user.getState())
                .formParam("city", user.getCity())
                .formParam("mobile_number", user.getMobileNumber())
                .when()
                .post(CREATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Attempting to create account with unsupported HTTP method PUT")
    public AccountResponse createAccountWithPut(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .when()
                .put(CREATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponseFromDetail(response);
    }

    @Step("Deleting account for user: {user.email}")
    public AccountResponse deleteAccount(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .when()
                .delete(DELETE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Deleting account without email field")
    public AccountResponse deleteAccountWithoutEmail(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                // email formParam is intentionally omitted to test missing field validation
                .formParam("password", user.getPassword())
                .when()
                .delete(DELETE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Deleting account without password field for user: {user.email}")
    public AccountResponse deleteAccountWithoutPassword(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                // password formParam is intentionally omitted to test missing field validation
                .when()
                .delete(DELETE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Attempting to delete account with unsupported HTTP method PUT")
    public AccountResponse deleteAccountWithPost(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .when()
                .post(DELETE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponseFromDetail(response);
    }

    @Step("Updating account with correct credentials for user: {user.email}")
    public AccountResponse updateAccount(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("name", user.getName())
                .when()
                .put(UPDATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Updating account without email field")
    public AccountResponse updateAccountWithoutEmail(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                // email formParam is intentionally omitted to test missing field validation
                .formParam("password", user.getPassword())
                .formParam("name", user.getName())
                .when()
                .put(UPDATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Updating account without password field for user: {user.email}")
    public AccountResponse updateAccountWithoutPassword(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                // password formParam is intentionally omitted to test missing field validation
                .formParam("name", user.getName())
                .when()
                .put(UPDATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Updating account with unsupported HTTP method POST for user: {user.email}")
    public AccountResponse updateAccountWithPost(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .formParam("name", user.getName())
                .when()
                .post(UPDATE_ACCOUNT_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponseFromDetail(response);
    }

    @Step("Verifying login for user: {user.email}")
    public AccountResponse verifyLogin(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .when()
                .post(VERIFY_LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Verifying login without email field")
    public AccountResponse verifyLoginWithoutEmail(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                // email formParam is intentionally omitted to test missing field validation
                .formParam("password", user.getPassword())
                .when()
                .post(VERIFY_LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Verifying login without password field for user: {user.email}")
    public AccountResponse verifyLoginWithoutPassword(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                // password formParam is intentionally omitted to test missing field validation
                .when()
                .post(VERIFY_LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    @Step("Verifying login with unsupported HTTP method PUT for user: {user.email}")
    public AccountResponse verifyLoginWithPut(User user) {
        Response response = given()
                .spec(ApiConfig.getBaseSpec())
                .formParam("email", user.getEmail())
                .formParam("password", user.getPassword())
                .when()
                .put(VERIFY_LOGIN_ENDPOINT)
                .then()
                .extract()
                .response();

        return toAccountResponse(response);
    }

    private AccountResponse toAccountResponse(Response response) {
        AccountResponse result = new AccountResponse();
        result.setResponseCode(response.jsonPath().getInt("responseCode"));
        result.setMessage(response.jsonPath().getString("message"));
        return result;
    }

    // Some endpoints return {"detail": "..."} instead of the standard {"responseCode": N, "message": "..."}
    // format when receiving unsupported HTTP methods. This helper handles that inconsistent response format.
    private AccountResponse toAccountResponseFromDetail(Response response) {
        AccountResponse result = new AccountResponse();
        result.setResponseCode(response.statusCode());
        result.setMessage(response.jsonPath().getString("detail"));
        return result;
    }
}