package io.github.matveyvolodin.api.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    public static final String BASE_URL = "https://automationexercise.com";

    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                .build();
    }
}
