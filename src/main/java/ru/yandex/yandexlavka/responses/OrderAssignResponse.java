package ru.yandex.yandexlavka.responses;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.yandexlavka.auxiliary_entities.CouriersGroupOrders;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderAssignResponse {

    private Date date;

    private List<CouriersGroupOrders> couriers;
}
