package ru.yandex.yandexlavka.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.yandexlavka.controllers.CourierController;
import ru.yandex.yandexlavka.controllers.OrderController;
import ru.yandex.yandexlavka.responses.AbstractErrorResponse;
import ru.yandex.yandexlavka.responses.BadRequestResponse;
import ru.yandex.yandexlavka.responses.NotFoundResponse;
import ru.yandex.yandexlavka.validation.exceptions.EntityNotFoundException;
import ru.yandex.yandexlavka.validation.exceptions.IncorrectDatesIntervalException;
import ru.yandex.yandexlavka.validation.exceptions.OrderAssignedToAnotherCourierException;

@RestControllerAdvice(assignableTypes = {CourierController.class, OrderController.class})
public class ApiExceptionsHandler {

    static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionsHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AbstractErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        LOGGER.info("Request to get a non-existent entity received");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse(e.getMessage()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
            OrderAssignedToAnotherCourierException.class, IncorrectDatesIntervalException.class})
    public ResponseEntity<AbstractErrorResponse> handleInvalidRequestBodyException(Exception e) {
        LOGGER.info("Bad request received");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BadRequestResponse(e.getMessage()));
    }
}
