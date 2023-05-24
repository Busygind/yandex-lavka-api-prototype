package ru.yandex.yandexlavka.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.dtos.CourierDTO;
import ru.yandex.yandexlavka.repos.OrdersRepository;

import java.time.Instant;

@Component
public class CourierEarningsCalculator implements CourierMetaInfoCalculator {

    private final OrdersRepository ordersRepository;

    @Autowired
    public CourierEarningsCalculator(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Integer calculate(CourierDTO courierDTO, Instant startDate, Instant endDate) {
        Integer costSum = ordersRepository.getCostsSumByCourierIdPerPeriod(courierDTO.getCourierId(), startDate, endDate);
        if (costSum == null) return 0;
        return costSum * courierDTO.getCourierType().getEarningsCoefficient();
    }
}
