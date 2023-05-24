package ru.yandex.yandexlavka.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.dtos.CourierDTO;
import ru.yandex.yandexlavka.ratelimiter.RateLimiterConfig;
import ru.yandex.yandexlavka.requests.CreateCourierRequest;
import ru.yandex.yandexlavka.responses.CreateCouriersResponse;
import ru.yandex.yandexlavka.responses.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.responses.GetCourierResponse;
import ru.yandex.yandexlavka.services.CourierService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    private final CourierService courierService;
    private final RateLimiterConfig rateLimiterConfig;

    @Autowired
    public CourierController(CourierService courierService, RateLimiterConfig rateLimiterConfig) {
        this.courierService = courierService;
        this.rateLimiterConfig = rateLimiterConfig;
    }

    @GetMapping("")
    public ResponseEntity<List<GetCourierResponse>> getCouriers(@RequestParam(required = false, defaultValue = "1") Integer limit,
                                                                @RequestParam(required = false, defaultValue = "0") Integer offset) {
        if (rateLimiterConfig.getBuckets()[0].tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(courierService.getCouriers(limit, offset));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @PostMapping("")
    public ResponseEntity<List<CreateCouriersResponse>> createCourier(@Valid @RequestBody CreateCourierRequest createCourierRequest) {
        if (rateLimiterConfig.getBuckets()[1].tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.OK).body(courierService.createCourier(createCourierRequest));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/{courier_id}")
    public ResponseEntity<List<CourierDTO>> getCourierById(@PathVariable(name = "courier_id") Long courierId) {
        if (rateLimiterConfig.getBuckets()[2].tryConsume(1)) {
            return courierService.getCourierById(courierId);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping("/meta-info/{courier_id}")
    public ResponseEntity<List<GetCourierMetaInfoResponse>> getCourierMetaInfo(@PathVariable(name = "courier_id") Long courierId,
                                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        if (rateLimiterConfig.getBuckets()[3].tryConsume(1)) {
            return courierService.getCourierMetaInfo(courierId, startDate, endDate);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
