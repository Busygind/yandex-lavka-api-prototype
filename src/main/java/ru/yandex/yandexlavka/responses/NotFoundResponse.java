package ru.yandex.yandexlavka.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class NotFoundResponse extends AbstractErrorResponse {

    public NotFoundResponse() {
        super("Entity not found");
    }

    public NotFoundResponse(String message) {
        super(message);
    }
}
