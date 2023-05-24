package ru.yandex.yandexlavka.auxiliary_entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CouriersGroupOrders {

    private Long courierId;

    private List<GroupOrders> orders;
}
