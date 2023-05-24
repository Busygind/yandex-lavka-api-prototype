package ru.yandex.yandexlavka.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.dtos.OrderDTO;

import java.time.Instant;

@Repository
public interface OrdersRepository extends JpaRepository<OrderDTO, Long> {
    @Query("SELECT sum (o.cost) from OrderDTO o " +
            "where (o.courierId = ?1 and (o.completedTime between ?2 and ?3))")
    Integer getCostsSumByCourierIdPerPeriod(Long courierId, Instant startDate, Instant endDate);

    @Query("select count (o.completedTime) from OrderDTO o where (o.courierId = ?1 and (o.completedTime between ?2 and ?3))")
    Integer getCountOfCompletedOrdersBetweenDates(Long courierId, Instant startDate, Instant endDate);
}
