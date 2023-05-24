package ru.yandex.yandexlavka.auxiliary_entities;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.yandexlavka.dtos.OrderDTO;

import java.util.List;

@Getter
@Setter
public class GroupOrders {

    private Long groupOrderId;

    private List<OrderDTO> orders;
}
