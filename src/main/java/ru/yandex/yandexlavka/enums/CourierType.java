package ru.yandex.yandexlavka.enums;

import lombok.Getter;

public enum CourierType {

    FOOT(2, 3),
    BIKE(3, 2),
    AUTO(4, 1);

    @Getter
    private final Integer earningsCoefficient;

    @Getter
    private final Integer ratingCoefficient;

    CourierType(Integer earningsCoefficient, Integer ratingCoefficient) {
        this.earningsCoefficient = earningsCoefficient;
        this.ratingCoefficient = ratingCoefficient;
    }
}
