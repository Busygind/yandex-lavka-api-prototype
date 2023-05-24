package ru.yandex.yandexlavka.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class BadRequestResponse extends AbstractErrorResponse {

    public BadRequestResponse() {
        super("Request is not valid");
    }

    public BadRequestResponse(String message) {
        super(message);
    }
}
