package ru.yandex.yandexlavka.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.dtos.CompleteOrderRequestDTO;
import ru.yandex.yandexlavka.ratelimiter.RateLimiterConfig;
import ru.yandex.yandexlavka.requests.CreateOrderRequest;
import ru.yandex.yandexlavka.dtos.OrderDTO;
import ru.yandex.yandexlavka.responses.OrderAssignResponse;
import ru.yandex.yandexlavka.services.OrdersService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrdersService service;
    private final RateLimiterConfig rateLimiterConfig;

    @Autowired
    public OrderController(OrdersService service, RateLimiterConfig rateLimiterConfig) {
        this.service = service;
        this.rateLimiterConfig = rateLimiterConfig;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestParam(required = false, defaultValue = "1") Integer limit,
                                                    @RequestParam(required = false, defaultValue = "0") Integer offset) {
        if (rateLimiterConfig.getBuckets()[4].tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(service.getOrders(limit, offset));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        if (rateLimiterConfig.getBuckets()[5].tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(service.createOrders(createOrderRequest));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @PostMapping(value = "/complete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> completeOrder(@RequestBody CompleteOrderRequestDTO dto) {
        if (rateLimiterConfig.getBuckets()[6].tryConsume(1)) {
            return service.completeOrder(dto);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @PostMapping("/assign")
    public ResponseEntity<List<OrderAssignResponse>> orderAssign(@RequestParam(required = false) Date date) {
        if (rateLimiterConfig.getBuckets()[7].tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<List<OrderDTO>> getOrder(@PathVariable(name = "order_id") Long orderId) {
        if (rateLimiterConfig.getBuckets()[8].tryConsume(1)) {
            return service.getOrder(orderId);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
