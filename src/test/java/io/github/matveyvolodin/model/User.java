package io.github.matveyvolodin.model;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class User {
    private final String title;
    private final String name;
    private final String email;
    private final String password;
    private final String dayOfBirth;
    private final String monthOfBirth;
    private final String yearOfBirth;
    private final boolean newsletter;
    private final boolean specialOffers;
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String address;
    private final String address2;
    private final String country;
    private final String state;
    private final String city;
    private final String zipCode;
    private final String mobileNumber;
}
