package io.github.matveyvolodin.model;

import java.util.UUID;

public class UserFactory {

    private UserFactory() {}

    public static User getRandomUser() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);

        return User.builder()
                .title("Mr")
                .name("User_%s".formatted(uniqueId))
                .email("user_%s@gmail.com".formatted(uniqueId))
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

    public static User getSecondUser() {
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        return getRandomUser().toBuilder()
                .email("second_user_%s@gmail.com".formatted(uniqueId))
                .password("Password124!")
                .build();
    }
}
