package ru.yandex.yandexlavka.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

public abstract class AbstractErrorResponse {

    @JsonIgnore // по спецификации API в ответ должен приходить объект без тела, поэтому данное поле служит для будущего расширения
    @Getter
    private final String message;

    public AbstractErrorResponse(String message) {
        this.message = message;
    }

}
