package ru.yandex.yandexlavka.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.dtos.CourierDTO;
import ru.yandex.yandexlavka.repos.OrdersRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class CourierRatingCalculator implements CourierMetaInfoCalculator {

    private final OrdersRepository ordersRepository;

    @Autowired
    public CourierRatingCalculator(OrdersRepository repository) {
        this.ordersRepository = repository;
    }

    @Override
    public Integer calculate(CourierDTO courierDTO, Instant startDate, Instant endDate) {
        Integer ordersCount = ordersRepository.getCountOfCompletedOrdersBetweenDates(courierDTO.getCourierId(),
                                                                                    startDate, endDate);
        int countOfHours = (int) startDate.until(endDate, ChronoUnit.HOURS);
        return ordersCount / countOfHours * courierDTO.getCourierType().getEarningsCoefficient();
    }
}
