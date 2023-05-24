package ru.yandex.yandexlavka.util;

import ru.yandex.yandexlavka.dtos.CourierDTO;

import java.time.Instant;

public interface CourierMetaInfoCalculator {

    Integer calculate(CourierDTO courierDTO, Instant startDate, Instant endDate);

}
