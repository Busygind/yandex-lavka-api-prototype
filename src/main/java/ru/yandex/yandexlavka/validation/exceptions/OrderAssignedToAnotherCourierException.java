package ru.yandex.yandexlavka.validation.exceptions;

public class OrderAssignedToAnotherCourierException extends RuntimeException {
    public OrderAssignedToAnotherCourierException(String message) {
        super(message);
    }
}
