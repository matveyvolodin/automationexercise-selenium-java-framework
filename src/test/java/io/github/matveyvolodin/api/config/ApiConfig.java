package io.github.matveyvolodin.api.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiConfig {

    public static final String BASE_URL = "https://automationexercise.com";

    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .addHeader("Accept", "application/json")
                .build();
    }
}
