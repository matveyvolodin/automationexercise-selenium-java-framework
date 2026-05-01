package io.github.matveyvolodin.model;

public class UserFactory {

    private static final String RUN_ID = String.valueOf(System.currentTimeMillis());

    private UserFactory() {}

    public static User getRandomUser() {
        return User.builder()
                .title("Mr")
                .name("User_%s".formatted(RUN_ID))
                .email("user_%s@gmail.com".formatted(RUN_ID))
                .password("Password123!")
                .dayOfBirth("15")
                .monthOfBirth("January")
                .yearOfBirth("1990")
                .newsletter(true)
                .specialOffers(false)
                .firstName("John")
                .lastName("Doe")
                .company("Test Company")
                .address("123 Main Street")
                .address2("Apt 4B")
                .country("United States")
                .state("New York")
                .city("New York")
                .zipCode("10001")
                .mobileNumber("1234567890")
                .build();
    }
}
