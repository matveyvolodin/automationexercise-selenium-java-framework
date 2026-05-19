package io.github.matveyvolodin.api.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountResponse {

    private int responseCode;
    private String message;

    @Override
    public String toString() {
        return "AccountResponse{responseCode=" + responseCode + ", message='" + message + "'}";
    }
}
